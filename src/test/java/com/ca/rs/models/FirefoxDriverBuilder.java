package com.ca.rs.models;

import com.ca.rs.SeleniumExternalDriversConstants;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.ibatis.common.resources.Resources;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import static org.openqa.selenium.remote.CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION;


public class FirefoxDriverBuilder {
    private Boolean enableJavaScript;
    private FirefoxProfile firefoxProfile;

    protected DesiredCapabilities desiredCapabilities;

    private static String OS = System.getProperty("os.name");
    private static final String GECKO_DRIVER_EXECUTABLE_SYSTEM_PROPERTY = "webdriver.gecko.driver";
    private final ImmutableMap.Builder<String, Object> prefs = ImmutableMap.builder();
    private static final Logger LOG = LoggerFactory.getLogger(FirefoxDriverBuilder.class);

    FirefoxDriverBuilder()
    {
        this.desiredCapabilities=DesiredCapabilities.firefox();
    }
    private final ImmutableSet.Builder<String> switches = ImmutableSet.builder();
    public FirefoxDriverBuilder ensureCleanSession() {
        return capabilities(desiredCapabilities -> desiredCapabilities.setCapability(ENSURING_CLEAN_SESSION, true));
    }


    /**
     * Adds a switch value to the set of switches.
     *
     * @param value the value
     * @return this builder
     */
    public FirefoxDriverBuilder firefoxSwitch(String value) {
        switches.add(value);
        return this;
    }


    public FirefoxDriverBuilder capabilities(Consumer<DesiredCapabilities> consumer) {
        consumer.accept(desiredCapabilities);
        return this;
    }
    private static File resolveDriverExecutableFile() {

        LOG.debug("***Operating System Name is***"+OS+"******");
        switch (OS) {

            case "Mac OS X":
                try {
                    return Resources.getResourceAsFile(SeleniumExternalDriversConstants.FIREFOX_DRIVER_MAC_PATH);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            default:
                return new File("drivers/firefox/V18/geckodriver");
        }
    }


    /**
     * Configures Firefox to have JavaScript disabled.
     *
     * @return A self reference.
     *
     * @since 0.9.0
     */
    public FirefoxDriverBuilder withoutJavaScript() {
        this.enableJavaScript = false;
        return this;
    }

    /**
     * Sets specific {@link FirefoxProfile} to be used in the {@link FirefoxDriver}.
     *
     * @param firefoxProfile Profile to be used.
     * @return A self reference, allowing further configuration.
     *
     * @since 0.9.0
     */
    public FirefoxDriverBuilder withProfile(FirefoxProfile firefoxProfile) {
        this.firefoxProfile = firefoxProfile;
        return this;
    }

    protected FirefoxDriver build()  {
        configureGeckoDriverPath();
        DesiredCapabilities capabilities = configureFirefoxProfile();
        FirefoxDriver firefoxDriver = new FirefoxDriver(desiredCapabilities);
        //disableJavaScriptIfWanted(firefoxDriver);
        return firefoxDriver;
    }


    private DesiredCapabilities configureFirefoxProfile() {
        FirefoxProfile profile = this.firefoxProfile != null ? this.firefoxProfile : new FirefoxProfile();
        desiredCapabilities.setCapability(FirefoxDriver.PROFILE, profile);
        return desiredCapabilities;
    }

    private void disableJavaScriptIfWanted(FirefoxDriver driver) {
        if (shouldDisableJavaScript()) {
            disableJavaScript(driver);
        }
    }

    private boolean shouldDisableJavaScript() {
        return Boolean.FALSE.equals(this.enableJavaScript);
    }

    @SuppressWarnings("deprecation")
    private void disableJavaScript(FirefoxDriver driver) {
        driver.get("about:config");
        Actions act = new Actions(driver);
        act.sendKeys(Keys.RETURN).sendKeys("javascript.enabled").perform();
        //SeleniumQuery.$.pause(1000);
        act.sendKeys(Keys.TAB).sendKeys(Keys.RETURN).sendKeys(Keys.F5).perform();
        driver.get("about:blank");
    }


    private void configureGeckoDriverPath()
    {
        File geckoDriverFile=null;
        try {
            geckoDriverFile= resolveDriverExecutableFile();
            System.setProperty(GECKO_DRIVER_EXECUTABLE_SYSTEM_PROPERTY, String.valueOf(geckoDriverFile));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    /**
     * Set the download folder location.
     *
     * @param downloadFolderLocation the download folder location
     * @return this builder
     */
    public FirefoxDriverBuilder downloadFolderLocation(String downloadFolderLocation) {
        return pref("download.default_directory", downloadFolderLocation);
    }

    /**
     * Sets a experimental preference property.
     *
     * @param key   the property key
     * @param value the property value
     * @return this builder
     */
    public FirefoxDriverBuilder pref(String key, Object value) {
        prefs.put(key, value);
        return this;
    }

}