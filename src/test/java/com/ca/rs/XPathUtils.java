package com.ca.rs;

import org.openqa.selenium.By;

import static org.openqa.selenium.By.xpath;

public class XPathUtils {

  public static By xpathForButtonText(String buttonText) {
    return xpath("//button[contains(text(), '" + buttonText + "')]");
  }
}
