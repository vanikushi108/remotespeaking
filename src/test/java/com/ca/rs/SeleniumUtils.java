package com.ca.rs;

import com.google.common.base.Function;
import org.json.JSONException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 * A collection of useful methods for using with Selenium
 */
public class SeleniumUtils {

  private static int NORMAL_TIMEOUT = 60;
  private static int LONG_TIMEOUT = 180;

  public static void waitForPresence(WebDriver webDriver, By locator) {
    WebDriverWait driverWait = new WebDriverWait(webDriver, NORMAL_TIMEOUT);
    driverWait.until(presenceOfElementLocated(locator));
  }

  public static void waitUntilVisible(WebDriver webDriver, By locator) {
    WebDriverWait driverWait = new WebDriverWait(webDriver, NORMAL_TIMEOUT);
    driverWait.until(visibilityOfElementLocated(locator));
  }

  public static void waitUntilVisible(WebDriver webDriver, WebElement element) {
    WebDriverWait driverWait = new WebDriverWait(webDriver, NORMAL_TIMEOUT);
    driverWait.until(ExpectedConditions.visibilityOf(element));
  }

  public static void waitUntilTextPresent(WebDriver webDriver, By locator, String expectedText) {
    WebDriverWait driverWait = new WebDriverWait(webDriver, NORMAL_TIMEOUT);
    driverWait.until(ExpectedConditions.textToBe(locator, expectedText));
  }

  public static WebElement customWait(WebDriver webDriver, WebElement element){
    Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
            .withTimeout(LONG_TIMEOUT, TimeUnit.SECONDS)
            .pollingEvery(10, TimeUnit.MILLISECONDS)
            .ignoring(NoSuchElementException.class);

    final Wait<WebDriver> w = wait;

    return w.until(new Function<WebDriver, WebElement>() {
      public WebElement apply(WebDriver driver) {
        w.until(ExpectedConditions.visibilityOf(element));
        return element;
      }
    });
  }

  public static void waitUntilInvisible(WebDriver webDriver, By locator) {
    WebDriverWait driverWait = new WebDriverWait(webDriver, NORMAL_TIMEOUT);
    driverWait.until(invisibilityOfElementLocated(locator));
  }

  public static void waitUntilAllInvisible(WebDriver webDriver, List<WebElement> elements) {
    WebDriverWait driverWait = new WebDriverWait(webDriver, NORMAL_TIMEOUT);
    driverWait.until(invisibilityOfAllElements(elements));
  }


  public static void waitUntilPresent(WebDriver webDriver, By locator) {
    WebDriverWait driverWait = new WebDriverWait(webDriver, NORMAL_TIMEOUT);
    driverWait.until(presenceOfElementLocated(locator));
  }

  public static void waitUntilNotPresent(WebDriver webDriver, By locator) {
    WebDriverWait driverWait = new WebDriverWait(webDriver, NORMAL_TIMEOUT);
    driverWait.until(numberOfElementsToBe(locator, 0));
  }

  public static void waitUntilClickable(WebDriver webDriver, By locator) {
    WebDriverWait driverWait = new WebDriverWait(webDriver, NORMAL_TIMEOUT);
    driverWait.until(ExpectedConditions.elementToBeClickable(locator));
  }

  public static void waitUntilElementClickable(WebDriver webDriver,WebElement element) {
    WebDriverWait driverWait = new WebDriverWait(webDriver, NORMAL_TIMEOUT);
    driverWait.until(ExpectedConditions.elementToBeClickable(element));
  }



  public static void waitForClass(WebDriver webDriver, By locator, String className) {
    WebDriverWait driverWait = new WebDriverWait(webDriver, NORMAL_TIMEOUT);
    driverWait.until(attributeContains(locator, "class", className));
  }

  public static void waitForImage(WebDriver webDriver, By locator) {
    WebDriverWait driverWait = new WebDriverWait(webDriver, NORMAL_TIMEOUT);
    driverWait.until(attributeContains(locator, "complete", "true"));
  }

  public static void scrollIntoView(WebDriver webDriver, WebElement element) {
    ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
  }

  public static void waitForPageLoad(WebDriver webDriver) {
    Wait<WebDriver> wait = new WebDriverWait(webDriver, LONG_TIMEOUT);
    wait.until(driver -> String
        .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
        .equals("complete"));
  }

  public static void assertInnerHTMLOfElement(WebDriver webDriver, String cssSelector,
                                              String expectedValue, boolean expected) throws JSONException {
    By locator = cssSelector(cssSelector);

    if (expected) {
      waitUntilVisible(webDriver, locator);
      assertThat(webDriver.findElement(locator).getAttribute("innerHTML")).isEqualTo(expectedValue);
    } else {
      try {
        assertThat(webDriver.findElement(locator).getAttribute("innerHTML")).isNotEqualTo(expectedValue);
      } catch (NoSuchElementException e) {
        // No-op. If it doesn't exist then we can assert that it doesnt contain text equalling the given
      }
    }
  }

  public static void assertTextOfElement(WebDriver webDriver, String cssSelector,
                                         String expectedValue, boolean expected)
      throws JSONException {
    By locator = cssSelector(cssSelector);

    if (expected) {
      waitUntilTextPresent(webDriver, locator, expectedValue);
      assertThat(webDriver.findElement(locator).getText()).isEqualTo(expectedValue);
    } else {
      try {
        assertThat(webDriver.findElement(locator).getText()).isNotEqualTo(expectedValue);
      } catch (NoSuchElementException e) {
        // No-op. If it doesn't exist then we can assert that it doesnt contain text equalling the given
      }
    }
  }
}
