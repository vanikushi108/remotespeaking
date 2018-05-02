package com.ca.rs;

import com.ca.rs.steps.AbstractSeleniumSteps;
import com.ca.rs.models.SeleniumDriverContainer;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks extends AbstractSeleniumSteps {

    private static final Logger LOG = LoggerFactory.getLogger(Hooks.class);

    public Hooks(SeleniumDriverContainer seleniumDriverContainer) {
        super(seleniumDriverContainer);
    }

    @Before
    public void beforeScenario(Scenario scenario) throws Exception {
        LOG.debug("##### Starting Scenario : {} #####", scenario.getName() + "******status******" + scenario.getStatus());
        LOG.debug("******status before******" + scenario.getStatus());
    }

    @After
    public void afterScenario(Scenario scenario) {
        LOG.debug("******status after******" + scenario.getStatus());
        if (scenario.isFailed() && seleniumDriverContainer.isInstantiated()) {
            try {
                scenario.write("Current Page URL is " + webDriver().getCurrentUrl());
                byte[] screenShot;
                screenShot = ((TakesScreenshot) webDriver())
                        .getScreenshotAs(OutputType.BYTES);

                scenario.embed(screenShot, "image/png");
            } catch (WebDriverException platformNotSupportedException) {
                LOG.error(platformNotSupportedException
                        .getMessage(), platformNotSupportedException);
            }
        }
        try {
            seleniumDriverContainer.clear();
            LOG.debug("Web driver connection closed");
        } catch (Exception e) {

            LOG.error("***Driver already closed afterScenario method***", e);
        } finally {
            seleniumDriverContainer.clear();
        }
        LOG.debug("** End Scenario - " + scenario.getName() + " Status - " + scenario.getStatus() + " **");
        LOG.debug("****************************************************************");
    }
}
