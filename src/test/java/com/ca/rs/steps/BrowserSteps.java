package com.ca.rs.steps;

import com.ca.rs.models.ModelContainer;
import com.ca.rs.models.SeleniumDriverContainer;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static java.awt.Toolkit.getDefaultToolkit;
import static java.awt.datatransfer.DataFlavor.stringFlavor;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.skyscreamer.jsonassert.JSONParser.parseJSON;

/**
 * A suite of Steps to help manage common browser functions.
 */
public class BrowserSteps extends AbstractSeleniumSteps {

  private static final int SLEEP_INCREMENT = 500;
  private static final int DOWNLOAD_TIMEOUT = 300000; // Five minutes

  private final ModelContainer modelContainer;

  @Autowired
  public BrowserSteps(SeleniumDriverContainer seleniumDriverContainer,
                      ModelContainer modelContainer) {
    super(seleniumDriverContainer);
    this.modelContainer = checkNotNull(modelContainer, "modelContainer must not be null");
  }

  @Given("^I set my browser window to " + StepFields.INTEGER + " by " + StepFields.INTEGER + "$")
  public void iSetMyBrowserWindowTo(Integer width, Integer height) throws Throwable {
    seleniumDriverContainer.get().manage().window().setSize(new Dimension(width, height));
  }

  @When("^I refresh the browser$")
  public void refresh() throws Throwable {
    webDriver().navigate().refresh();
  }

  @And("^I go back on browser window$")
  public void goback() throws Throwable {
    webDriver().navigate().back();
  }

  @When("^I clear my local storage$")
  public void clearLocalStorage() throws Throwable {
    LocalStorage localStorage = ((WebStorage) webDriver()).getLocalStorage();
    localStorage.clear();
  }

  @Then("^the local storage key " + StepFields.LITERAL + " should be set$")
  public void checkLocalStorage(String localStorageKey) throws Throwable {
    String item = localStorage().getItem(localStorageKey);

    assertThat(item).isNotNull();
    modelContainer.put(ModelContainer.UNNAMED_LOCAL_STORAGE, parseJSON(item));
  }

  @When("^I update the value of the local storage key " + StepFields.LITERAL + "$")
  public void updateValueOfLocalStorageKey(String localStorageKey) throws Throwable {
    // This method assumes the key has already been retrieved from local storage
    checkNotNull(modelContainer.get(ModelContainer.UNNAMED_LOCAL_STORAGE), "Local storage Item must be set");

    JSONObject jsonValue = modelContainer.get(ModelContainer.UNNAMED_LOCAL_STORAGE);

    localStorage().setItem(localStorageKey, jsonValue.toString());
  }

  // This is an OS feature rather than a Browser feature, but realistically, the only tests that use an
  // OS UI will be because we're testing a Browser
  @Then("^my clipboard should contain the text " + StepFields.LITERAL + "$")
  public void myClipboardShouldContainText(String expectedCopyText) throws Throwable {
    String actualCopyText = (String) getDefaultToolkit().getSystemClipboard().getData(stringFlavor);

    assertThat(actualCopyText)
        .isEqualTo(expectedCopyText);
  }

  private LocalStorage localStorage() {
    return ((WebStorage) webDriver()).getLocalStorage();
  }

  @Then("^the file " + StepFields.LITERAL + " should be downloaded$")
  public void checkFileIsDownloaded(String fileName) throws Throwable {
    fileName = processFilename(fileName);

    int secondsSleptFor = 0;
    boolean awaitDownload = true;
    while (awaitDownload) {
      sleepUninterruptibly(SLEEP_INCREMENT, MILLISECONDS);
      secondsSleptFor += SLEEP_INCREMENT;

      if (isFileDownloaded(fileName, seleniumDriverContainer.downloadFolderLocation())
          || secondsSleptFor > DOWNLOAD_TIMEOUT) {
        awaitDownload = false;
      }
    }

    assertTrue(isFileDownloaded(fileName, seleniumDriverContainer.downloadFolderLocation()));
  }

  @Given("^I maximise the browser$")
  public void iMaximiseTheBrowser()  {
    try {
      webDriver().manage().window().setSize(new Dimension(1920,1080));
    }catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }

  private String processFilename(String fileName) {
    if (fileName.contains("${uri}")) {
      assertThat(modelContainer.contains(ModelContainer.UNNAMED_URI_ID)).isTrue();
      fileName = fileName.replace("${uri}", modelContainer.get(ModelContainer.UNNAMED_URI_ID));
    }
    return fileName;
  }

  private boolean isFileDownloaded(String fileName, String downloadPath) {
    File dir = new File(downloadPath);
    File[] dirContents = dir.listFiles();
    if (dirContents != null) {
      for (File dirContent : dirContents) {
        if (dirContent.getName().equals(fileName)) {
          modelContainer.put(ModelContainer.UNNAMED_DOWNLOAD, dirContent);
          return true;
        }
      }
    }

    return false;
  }
}
