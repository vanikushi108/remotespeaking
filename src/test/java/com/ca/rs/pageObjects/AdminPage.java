package com.ca.rs.pageObjects;

import com.ca.rs.models.SeleniumDriverContainer;
import com.ca.rs.steps.AbstractSeleniumSteps;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import static com.ca.rs.SeleniumUtils.*;
import static com.codeborne.selenide.Configuration.browser;

@Component
@Lazy
public class AdminPage extends AbstractSeleniumSteps {

    public AdminPage(SeleniumDriverContainer seleniumDriverContainer) {
        super(seleniumDriverContainer);
    }

    @FindBy(css = ".dialog-button.dialog-button-update")
    private WebElement CloneButton;

    @FindBy(css = "input")
    private WebElement ExaminerNo;

    @FindBy(css = "button[type='button'] > span:nth-child(2)")
    private WebElement ProceedButton;

    @FindBy(xpath = "//input[@value='interlocutor']")
    private WebElement Interlocutor;

    @FindBy(css = "//input[@value='remoteAssessor']")
    private WebElement RemoteAssessor;

    @FindBy(css = "//input[@value='inRoomAssessor']")
    private WebElement InRoomAssessor;

    @FindBy(css = "input[value='1']")
    private WebElement ExamTypeA2Key;

    @FindBy(css = "#videorecorder-ribbon >div >div >button")
    private WebElement RecordButton;

    @FindBy(name = "email")
    private WebElement UserName;

    @FindBy(name = "password")
    private WebElement Password;

    @FindBy(css = ".auth0-lock-submit")
    private WebElement LoginButton;

//    @FindBy(css = ".mark-button:nth-child(1)")
//    private WebElement selectCanditate1;
//
//    @FindBy(css = ".mark-button:nth-child(2)")
//    private WebElement selectCanditate2;

    @FindBy(css = "button[data-candidate='1']")
    private WebElement selectCanditate1;

    @FindBy(css = "button[data-candidate='2']")
    private WebElement selectCanditate2;

    @FindBy(css = "#confirm-yes")
    private WebElement ClickOk;

    @FindBy(css = "#button-submit")
    private WebElement SubmitButton;

    @FindBy(css = "#confirm-yes")
    private WebElement ConfirmYesSubmitButton;


    public void enterExaminerNo(int ExaminerNumber) throws Exception {
        waitUntilVisible(webDriver(), ExaminerNo);
        ExaminerNo.sendKeys(Integer.toString(ExaminerNumber));
    }

    public void clickOnProceedButton() throws Exception {
        waitUntilVisible(webDriver(), ProceedButton);
        try {
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", ProceedButton);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(ProceedButton).perform();
            ProceedButton.click();
        }
    }

    public void clickOnInterlocutor() throws Exception {
        Thread.sleep(1000);
        try {
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", Interlocutor);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(Interlocutor).perform();
            Interlocutor.click();
        }
    }

    public void clickOnRemoteAssessor() throws Exception {
        waitUntilVisible(webDriver(), RemoteAssessor);
        RemoteAssessor.click();
    }

    public void clickOnInRoomAssessor() throws Exception {
        waitUntilVisible(webDriver(), InRoomAssessor);
        InRoomAssessor.click();
    }

    public void clickOnExamTypeA2Key() throws Exception {
        Thread.sleep(1000);
        try {
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", ExamTypeA2Key);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(ExamTypeA2Key).perform();
            ExamTypeA2Key.click();
        }
    }

    public void clickOnRecordButton() throws Exception {
        Thread.sleep(1000);
        try {
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", RecordButton);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(RecordButton).perform();
            RecordButton.click();
        }
    }

    public void enterUserName(String txtUserName) throws Exception {
        waitUntilVisible(webDriver(), UserName);
        UserName.sendKeys(txtUserName);
    }

    public void enterPassword(String txtPassword) throws Exception {
        waitUntilVisible(webDriver(), Password);
        Password.sendKeys(txtPassword);
    }

    public void clickOnLoginButton() throws Exception {
        waitUntilVisible(webDriver(), LoginButton);
        LoginButton.click();
    }

    public void selectCandidateOneMarks() throws Exception {

        customWait(webDriver(), selectCanditate1);
        try {
            Thread.sleep(1000);
            scrollIntoView(webDriver(), selectCanditate1);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", selectCanditate1);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(selectCanditate1).perform();
            selectCanditate1.click();
        }
    }

    public void selectValueFromDropdown(String marks) throws Exception {
        By locator = By.cssSelector("ul>li:nth-child(4)");

        WebElement element = webDriver().findElement(locator);

        try {
            Thread.sleep(1000);
            scrollIntoView(webDriver(), element);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", element);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(element).perform();
            element.click();
        }
        Thread.sleep(1000);
    }

    public void selectCandidateTwoMarks() throws Exception {

        customWait(webDriver(), selectCanditate2);
        try {
            Thread.sleep(1000);
            scrollIntoView(webDriver(), selectCanditate2);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", selectCanditate2);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(selectCanditate2).perform();
            selectCanditate2.click();
        }
    }

    public void clickOk() throws Exception {
        try {
            Thread.sleep(1000);
            webDriver().switchTo().alert().accept();
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", ClickOk);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(ClickOk).perform();
            ClickOk.click();
        }
    }

    public void clickSubmitButton() {
        waitUntilVisible(webDriver(), SubmitButton);
        SubmitButton.click();
    }

    public void clickConfirmYesSubmitButton() {
        waitUntilVisible(webDriver(), ConfirmYesSubmitButton);
        ConfirmYesSubmitButton.click();
    }
}