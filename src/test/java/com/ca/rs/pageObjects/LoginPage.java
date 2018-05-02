package com.ca.rs.pageObjects;

import com.ca.rs.steps.AbstractSeleniumSteps;
import com.ca.rs.models.SeleniumDriverContainer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static com.ca.rs.SeleniumUtils.waitUntilVisible;

@Component
@Lazy
public class LoginPage extends AbstractSeleniumSteps {

    public LoginPage(SeleniumDriverContainer seleniumDriverContainer) {
        super(seleniumDriverContainer);
    }

    private static final String TAAS_ADMIN_USERNAME = System.getProperty("TAAS_ADMIN_USERNAME");
    private static final String TAAS_ADMIN_PASSWORD = System.getProperty("TAAS_ADMIN_PASSWORD");

    @FindBy(css = ".login-username")
    private WebElement UserId;

    public void enterUserId() throws Exception {
        waitUntilVisible(webDriver(), UserId);
        UserId.sendKeys(TAAS_ADMIN_USERNAME);
    }
}
