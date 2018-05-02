package com.ca.rs.pageObjects;

import com.ca.rs.models.SeleniumDriverContainer;
import com.ca.rs.steps.AbstractSeleniumSteps;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static com.ca.rs.SeleniumUtils.*;

@Component
@Lazy
public class AdminPage extends AbstractSeleniumSteps {

    public AdminPage(SeleniumDriverContainer seleniumDriverContainer) {
        super(seleniumDriverContainer);
    }

    @FindBy(css = ".dialog-button.dialog-button-update")
    private WebElement CloneButton;

    @FindBy(css = "input[placeholder='Examiner no.']")
    private WebElement ExaminerNo;

    @FindBy(css = "button[type='button'] > span:nth-child(1)")
    private WebElement ProceedButton;

    @FindBy(css = "input[value='interlocutor']")
    private WebElement Interlocutor;

    @FindBy(css = "input[value='remoteAssessor']")
    private WebElement RemoteAssessor;

    @FindBy(css = "input[value='inRoomAssessor']")
    private WebElement InRoomAssessor;

    @FindBy(css = "input[value='1']")
    private WebElement ExamTypeA2Key;

    public void enterExaminerNo(int ExaminerNumber) throws Exception {
        waitUntilVisible(webDriver(), ExaminerNo);
        ExaminerNo.sendKeys(Integer.toString(ExaminerNumber));
    }

    public void clickOnProceedButton() throws Exception {
        Thread.sleep(500);
        waitUntilVisible(webDriver(), ProceedButton);
        ProceedButton.click();
        Thread.sleep(500);
    }

    public void clickOnInterlocutor() throws Exception {
         customWait(webDriver(), Interlocutor);
            try{
                ((JavascriptExecutor)webDriver()).executeScript("arguments[0].scrollIntoView(true);", Interlocutor);
                ((JavascriptExecutor)webDriver()).executeScript("arguments[0].click();", Interlocutor);
            }
            catch (Exception ex)
            {
                ((JavascriptExecutor)webDriver()).executeScript("arguments[0].click();", Interlocutor);
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
        waitUntilVisible(webDriver(), ExamTypeA2Key);
        ExamTypeA2Key.click();
    }
}