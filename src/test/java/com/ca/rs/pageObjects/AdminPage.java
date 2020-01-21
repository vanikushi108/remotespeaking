package com.ca.rs.pageObjects;

import com.ca.rs.models.SeleniumDriverContainer;
import com.ca.rs.steps.AbstractSeleniumSteps;
import io.appium.java_client.MobileBy;
import jdk.nashorn.internal.runtime.regexp.joni.ast.EncloseNode;
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

    @FindBy(id = "button-next")
    private WebElement ProceedButton;

    @FindBy(css = "label[data-option='Interlocutor']")
    private WebElement Interlocutor;

    @FindBy(css = "label[data-option='Remote Assessor']")
    private WebElement RemoteAssessor;

    @FindBy(css = "label[data-option='In-Room Assessor']")
    private WebElement InRoomAssessor;

    @FindBy(css = "label[data-option='A2 Key']")
    private WebElement ExamTypeA2Key;

    @FindBy(css = "#exam-type-dropdown")
    private WebElement ExamTypeDropdown;

    @FindBy(css = "#videorecorder-ribbon >div >div >button")
    private WebElement RecordButton;

    @FindBy(name = "email")
    private WebElement UserName;

    @FindBy(name = "password")
    private WebElement Password;

    @FindBy(css = ".auth0-lock-submit")
    private WebElement LoginButton;

    @FindBy(css = "button[data-candidate='1']")
    private WebElement SelectCandidate1;

    @FindBy(css = "button[data-candidate='2']")
    private WebElement SelectCandidate2;

    @FindBy(css = "#confirm-yes")
    private WebElement ClickOk;

    @FindBy(css = "#button-submit")
    private WebElement SubmitButton;

    @FindBy(css = "#confirm-yes")
    private WebElement ConfirmYesSubmitButton;

    @FindBy(css = "#root > div > div > div > form > div > div > div > div > div:nth-child(1) > div > div > button.play-button")
    private WebElement PlayButton;

    @FindBy(css = "#player-play-toggle")
    private WebElement PlayButtonRecording;

    @FindBy(css = "#player-play-toggle")
    private WebElement PlayBackButtonVedio;

    @FindBy(css = "button[data-candidate='1'][data-category='Grammar & Vocabulary']")
    private WebElement SelectCandidateOneGrammerAndVocabulary;

    @FindBy(css = "button[data-candidate='1'][data-category='Pronunciation']")
    private WebElement SelectCandidateOnePronunciation;

    @FindBy(css = "button[data-candidate='1'][data-category='Interactive Communication']")
    private WebElement SelectCandidateOneInteractiveCommunication;

    @FindBy(css = "button[data-candidate='2'][data-category='Grammar & Vocabulary']")
    private WebElement SelectCandidateTwoGrammerAndVocabulary;

    @FindBy(css = "button[data-candidate='2'][data-category='Pronunciation']")
    private WebElement SelectCandidateTwoPronunciation;

    @FindBy(css = "button[data-candidate='2'][data-category='Interactive Communication']")
    private WebElement SelectCandidateTwoInteractiveCommunication;

    @FindBy(id = "candidate_1_name")
    private WebElement EnterCandidate1Name;

    @FindBy(id = "candidate_2_name")
    private WebElement EnterCandidate2Name;

    @FindBy(css = "test-material-input-2")
    private WebElement SelectCandidate1TestMaterial;

    @FindBy(id = "candidate_1_no")
    private WebElement EnterCandidate1Number;

    @FindBy(id = "candidate_2_no")
    private WebElement EnterCandidate2Number;

    @FindBy(id = "candidate_1_material_2")
    private WebElement Part2InputBox1;

    @FindBy(id = "candidate_2_material_2")
    private WebElement Part2InputBox2;

    @FindBy(id = "candidate_1_material_3")
    private WebElement Part3InputBox1;

    @FindBy(id = "candidate_2_material_3")
    private WebElement part3InputBox2;

    @FindBy(id = "center-number-input")
    private WebElement CenterNumber;


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
        try {
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", Interlocutor);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(Interlocutor).perform();
            Interlocutor.click();
        }
    }

    public void clickOnRemoteAssessor() throws Exception {
        waitUntilVisible(webDriver(), RemoteAssessor);
        try {
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", RemoteAssessor);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(RemoteAssessor).perform();
            RemoteAssessor.click();
        }
    }

    public void clickOnInRoomAssessor() throws Exception {
        waitUntilVisible(webDriver(), InRoomAssessor);
        try {
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", InRoomAssessor);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(InRoomAssessor).perform();
            InRoomAssessor.click();
        }
    }

    public void clickOnExamTypeA2Key() throws Exception {
        customWait(webDriver(), ExamTypeA2Key);
        try {
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", ExamTypeA2Key);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(ExamTypeA2Key).perform();
            ExamTypeA2Key.click();
        }
    }

    public void clickOnExamTypeDropdown() throws Exception {
        customWait(webDriver(), ExamTypeDropdown);
        try {
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", ExamTypeDropdown);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(ExamTypeDropdown).perform();
            ExamTypeDropdown.click();
        }
    }

    public void selectExamType(String examType) throws Exception {
        Thread.sleep(500);
        By locator = By.cssSelector("li[data-exam-type='" + examType + "']");
        WebElement element = webDriver().findElement(locator);
        customWait(webDriver(), element);
        try {
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", element);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(element).perform();
            element.click();
        }
    }

    public void clickOnRecordButton() throws Exception {
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

        customWait(webDriver(), SelectCandidate1);
        try {
            scrollIntoView(webDriver(), SelectCandidate1);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", SelectCandidate1);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(SelectCandidate1).perform();
            SelectCandidate1.click();
        }
    }

    public void selectValueFromDropdown(String marks) throws Exception {
        By locator = By.cssSelector("ul>li:nth-child(4)");
        WebElement element = webDriver().findElement(locator);
        try {
            scrollIntoView(webDriver(), element);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", element);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(element).perform();
            element.click();
        }
    }

    public void selectCandidateTwoMarks() throws Exception {

        customWait(webDriver(), SelectCandidate2);
        try {
            scrollIntoView(webDriver(), SelectCandidate2);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", SelectCandidate2);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(SelectCandidate2).perform();
            SelectCandidate2.click();
        }
    }

    public void clickOk() throws Exception {
        try {
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

    public void mclickOnPlayButton() {
        waitUntilVisible(webDriver(), PlayButton);
        PlayButton.click();
    }

    public void clickOnPlayButtonForRecording() {
        waitUntilVisible(webDriver(), PlayButtonRecording);
        PlayButtonRecording.click();
        webDriver().manage().window().maximize();
    }

    public void clickOnPlayBackButtonForVedio() {
        waitUntilVisible(webDriver(), PlayBackButtonVedio);
        PlayBackButtonVedio.click();
    }

    public void clickOnPlayButton() {
        waitUntilVisible(webDriver(), PlayButton);
        PlayButton.click();
    }

    public void selectCandidateOneGrammerAndVocabulary() throws Exception {

        customWait(webDriver(), SelectCandidateOneGrammerAndVocabulary);
        try {
            scrollIntoView(webDriver(), SelectCandidateOneGrammerAndVocabulary);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", SelectCandidateOneGrammerAndVocabulary);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(SelectCandidateOneGrammerAndVocabulary).perform();
            SelectCandidateOneGrammerAndVocabulary.click();
        }
    }

    public void selectCandidateOnePronunciation() throws Exception {

        customWait(webDriver(), SelectCandidateOnePronunciation);
        try {
            scrollIntoView(webDriver(), SelectCandidateOnePronunciation);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", SelectCandidateOnePronunciation);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(SelectCandidateOnePronunciation).perform();
            SelectCandidateOnePronunciation.click();
        }
    }

    public void selectCandidateOneInteractiveCommunication() throws Exception {

        customWait(webDriver(), SelectCandidateOneInteractiveCommunication);
        try {
            scrollIntoView(webDriver(), SelectCandidateOneInteractiveCommunication);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", SelectCandidateOneInteractiveCommunication);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(SelectCandidateOneInteractiveCommunication).perform();
            SelectCandidateOneInteractiveCommunication.click();
        }
    }

    public void selectCandidateTwoGrammerAndVocabulary() throws Exception {

        customWait(webDriver(), SelectCandidateTwoGrammerAndVocabulary);
        try {
            scrollIntoView(webDriver(), SelectCandidateTwoGrammerAndVocabulary);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", SelectCandidateTwoGrammerAndVocabulary);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(SelectCandidateTwoGrammerAndVocabulary).perform();
            SelectCandidateTwoGrammerAndVocabulary.click();
        }
    }

    public void selectCandidateTwoPronunciation() throws Exception {

        customWait(webDriver(), SelectCandidateTwoPronunciation);
        try {
            scrollIntoView(webDriver(), SelectCandidateTwoPronunciation);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", SelectCandidateTwoPronunciation);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(SelectCandidateTwoPronunciation).perform();
            SelectCandidateTwoPronunciation.click();
        }
    }

    public void selectCandidateTwoInteractiveCommunication() throws Exception {

        customWait(webDriver(), SelectCandidateTwoInteractiveCommunication);
        try {
            scrollIntoView(webDriver(), SelectCandidateTwoInteractiveCommunication);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", SelectCandidateTwoInteractiveCommunication);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(SelectCandidateTwoInteractiveCommunication).perform();
            SelectCandidateTwoInteractiveCommunication.click();
        }
    }

    public void selectDataValueFromMarkDropdown(String marks) throws Exception {
        Thread.sleep(400);
        By locator = By.cssSelector("li[role='menuitem'][data-mark-value='" + marks + "']");
        //By locator = By.cssSelector("ul[role='menu'] > li::nth-of-type(2)");
        //By locator = By.cssSelector("ul[role='menu'] > li::nth-child(2)");
        WebElement element = webDriver().findElement(locator);
        try {
            scrollIntoView(webDriver(), element);
            ((JavascriptExecutor) webDriver()).executeScript("arguments[0].click();", element);
        } catch (Exception ex) {
            new Actions(webDriver()).moveToElement(element).perform();
            element.click();
        }
        Thread.sleep(400);
    }

    public void selectPart2TestMaterial(int materialnum) {
        waitUntilVisible(webDriver(), SelectCandidate1TestMaterial);
        SelectCandidate1TestMaterial.sendKeys(Integer.toString(materialnum));
    }

    public void enterCandidate1Name(String name) throws InterruptedException {
        Thread.sleep(400);
        EnterCandidate1Name.click();
        EnterCandidate1Name.sendKeys(name);
    }

    public void enterCandidate2Name(String name) throws InterruptedException {
        Thread.sleep(400);
        EnterCandidate2Name.click();
        EnterCandidate2Name.sendKeys(name);
    }

    public void enterCandidate1Number(String num) throws InterruptedException {
        Thread.sleep(400);
        EnterCandidate1Number.click();
        EnterCandidate1Number.sendKeys(num);
    }

    public void enterCandidate2Number(String num) throws InterruptedException {
        Thread.sleep(400);
        EnterCandidate2Number.click();
        EnterCandidate2Number.sendKeys(num);
    }

    public void enterPart2InputBox1Material(String num) throws InterruptedException {
        Thread.sleep(400);
        Part2InputBox1.click();
        Part2InputBox1.sendKeys(num);
    }

    public void enterPart2InputBox2Material(String num) throws InterruptedException {
        Thread.sleep(400);
        Part2InputBox2.click();
        Part2InputBox2.sendKeys(num);
    }

    public void enterPart3InputBox1Material(String num) throws InterruptedException {
        Thread.sleep(400);
        Part3InputBox1.click();
        Part3InputBox1.sendKeys(num);
    }

    public void enterPart3InputBox2Material(String num) throws InterruptedException {
        Thread.sleep(400);
        part3InputBox2.click();
        part3InputBox2.sendKeys(num);
    }

    public void enterCenterNumber(String centerNo) throws Exception {
        waitUntilVisible(webDriver(), CenterNumber);
        CenterNumber.click();
        CenterNumber.sendKeys(centerNo);
    }

}