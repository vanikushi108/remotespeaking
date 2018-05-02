package com.ca.rs.pageObjects;

import com.ca.rs.steps.AbstractSeleniumSteps;
import com.ca.rs.models.SeleniumDriverContainer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static com.ca.rs.SeleniumUtils.*;


@Component
@Lazy
public class Common extends AbstractSeleniumSteps {

    @Autowired
    public Common(SeleniumDriverContainer seleniumDriverContainer) {
        super(seleniumDriverContainer);
    }

    @FindBy(css = ".login-username")
    private WebElement UserId;

    public void navigateToURL(String url) {
        webDriver().navigate().to(url);
        waitForPageLoad(webDriver());
    }

    public void scrollAndWait(WebElement element) {
        scrollIntoView(webDriver(), element);
        waitUntilVisible(webDriver(), element);
    }

    public String getCurrentUrl() {
        return webDriver().getCurrentUrl();
    }

}
