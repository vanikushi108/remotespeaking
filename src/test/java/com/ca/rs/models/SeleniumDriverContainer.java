package com.ca.rs.models;

import com.google.common.base.Joiner;
import com.google.common.util.concurrent.Monitor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.codeborne.selenide.WebDriverRunner;


import javax.annotation.concurrent.GuardedBy;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * A Wrapper around the Selenium driver to make sure it is only instantiated when required.
 */
@Component
public class SeleniumDriverContainer {
    private static final Logger LOG = LoggerFactory.getLogger(SeleniumDriverContainer.class);

    private static final String DOWNLOAD_FOLDER_LOCATION = computeDownloadFolderLocation();
    private static final String BROWSER = System.getProperty("browser", "Chrome");
//    private static final String SAUCE_USERNAME = System.getProperty("SAUCE_USERNAME");
//    private static final String SAUCE_ACCESS_KEY = System.getProperty("SAUCE_ACCESS_KEY");
    private static final String SAUCE_USERNAME = "BTS_Testing_Services";
    private static final String SAUCE_ACCESS_KEY = "9c3e2abc-e9fb-4c27-9166-f5769a635c93";
    private static final String BROWSER_VERSION = System.getProperty("browser_version", "65.0");
    private static final String OS_VERSION = System.getProperty("os_version", "Windows 10");
    private static final String RUN_TYPE = System.getProperty("run_type", "local");
    private static final DesiredCapabilities cap = new DesiredCapabilities();

    /**
     * Creates a new {@link ChromeDriverBuilder}.
     *
     * @return the builder
     */
    private static ChromeDriverBuilder chromeDriverBuilder() {
        return new ChromeDriverBuilder()
                .chromeSwitch("--incognito")
                .ensureCleanSession()
                .noSandbox();
    }

    private static FirefoxDriverBuilder firefoxDriverBuilder() {
        return new FirefoxDriverBuilder().firefoxSwitch("--incognito").ensureCleanSession();

    }

    /**
     * Computes the download folder location from the user home directory.
     *
     * @return the download folder location
     */
    private static String computeDownloadFolderLocation() {
        return Joiner.on(File.separatorChar).join(
                System.getProperty("user.dir"), "Downloads"
        );
    }

    private final Monitor monitor = new Monitor();

    private final Monitor.Guard isInstantiated = new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
            return webDriver != null;
        }
    };

    private final Monitor.Guard needsInstantiation = new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
            return webDriver == null;
        }
    };

    @GuardedBy("monitor")
    private static volatile WebDriver webDriver;

    /**
     * Clears & resets the web driver. This is designed to be run in between scenarios.
     */
    public void clear() {
        if (monitor.enterIf(isInstantiated)) {
            try {
                webDriver.quit();
            } finally {
                webDriver = null;
                monitor.leave();
            }
        }
    }

    /**
     * The download folder location.
     *
     * @return the path to the download directory
     */
    public String downloadFolderLocation() {
        return DOWNLOAD_FOLDER_LOCATION;
    }

    /**
     * Will either return the cached web driver, or create a new one
     * Creation will use the following system properties -
     * browser  - to select which browser to create
     * platform - to select which OS platform the test suite is running from
     *
     * @return a web driver to use
     */
    public WebDriver get() {
        if (monitor.enterIf(needsInstantiation)) {
            try {
                webDriver = createWebDriver();
                webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            } catch (Exception e) {
                LOG.error("Exception occured in get method SeleniumDriverContainerClass", e);
            } finally {
                monitor.leave();
            }
        }

        return webDriver;
    }

    /**
     * Returns whether or not the web driver has been created & cached.
     *
     * @return the state of the web driver
     */
    public boolean isInstantiated() {
        return webDriver != null;
    }

    private ChromeDriver createChromeDriver() {
        return chromeDriverBuilder()
                .downloadFolderLocation(DOWNLOAD_FOLDER_LOCATION)
                .build();
    }

    private FirefoxDriver createFirefoxDriver() {
        return firefoxDriverBuilder().downloadFolderLocation(DOWNLOAD_FOLDER_LOCATION).build();
    }

    private WebDriver createWebDriver() throws Exception {
        if (RUN_TYPE.equalsIgnoreCase("saucelabs")) {
            return androidDriver();
            //return SauceLabsDriver();
        } else {
            switch (BROWSER) {
                case "Chrome":
                    return createChromeDriver();
                case "Phantomjs":
                    return new PhantomJSDriver();
                case "Firefox":
                    return createFirefoxDriver();
                case "Safari":
                    return new SafariDriver();
                case "ie":
                    return new InternetExplorerDriver();
                default:
                    throw new IllegalArgumentException("Unsupported browser <" + BROWSER + ">");
            }
        }
    }

    private WebDriver SauceLabsDriver() throws Exception {
        cap.setCapability(CapabilityType.BROWSER_NAME, BROWSER);
        cap.setCapability("version", BROWSER_VERSION);
        cap.setCapability("platform", OS_VERSION);
        cap.setCapability("passed", "true");
        cap.setCapability("idleTimeout", 600);
        cap.setCapability("recordVideo", "false");
        cap.setCapability("recordScreenshots", "false");
        cap.setCapability("recordLogs", "false");
        cap.setCapability("webdriverRemoteQuietExceptions", "false");
        cap.setCapability("username", SAUCE_USERNAME);
        cap.setCapability("accessKey", SAUCE_ACCESS_KEY);
        cap.setCapability("name", "TYE - " + BROWSER + "-" +BROWSER_VERSION);

        webDriver = new RemoteWebDriver(new URL("http://ondemand.saucelabs.com:80/wd/hub"), cap);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebDriverRunner.setWebDriver(webDriver);
        return webDriver;
    }

    private WebDriver androidDriver() throws Exception {
        cap.setCapability("appiumVersion", "1.7.2");
        cap.setCapability("deviceName","Samsung Galaxy Tab S3 GoogleAPI Emulator");
        cap.setCapability("deviceOrientation", "portrait");
        cap.setCapability("browserName", "Chrome");
        cap.setCapability("platformVersion", "7.1");
        cap.setCapability("platformName","Android");
        cap.setCapability("passed", "true");
        cap.setCapability("idleTimeout", 600);
        cap.setCapability("recordVideo", "false");
        cap.setCapability("recordScreenshots", "false");
        cap.setCapability("recordLogs", "false");
        cap.setCapability("webdriverRemoteQuietExceptions", "false");
        cap.setCapability("username", SAUCE_USERNAME);
        cap.setCapability("accessKey", SAUCE_ACCESS_KEY);
        cap.setCapability("name", "Remote speaking - Samsung Galaxy Tab");
        webDriver = new RemoteWebDriver(new URL("http://ondemand.saucelabs.com:80/wd/hub"), cap) {};
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverRunner.setWebDriver(webDriver);
        return webDriver;
    }
}
