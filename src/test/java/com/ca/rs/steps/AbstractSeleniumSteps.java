package com.ca.rs.steps;

import com.ca.rs.models.SeleniumDriverContainer;
import org.openqa.selenium.WebDriver;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Parent class to provide methods that all Selenium Step classes will use
 */
public abstract class AbstractSeleniumSteps {

  protected final SeleniumDriverContainer seleniumDriverContainer;

  public AbstractSeleniumSteps(SeleniumDriverContainer seleniumDriverContainer) {
    this.seleniumDriverContainer = checkNotNull(seleniumDriverContainer, "seleniumDriverContainer must not be null");
  }

  protected WebDriver webDriver() {
    return seleniumDriverContainer.get();
  }
}
