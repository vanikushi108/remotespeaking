package com.ca.rs.models;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.ibatis.common.resources.Resources;
import com.ca.rs.SeleniumExternalDriversConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkState;
import static org.openqa.selenium.remote.CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION;

/**
 * A fluent builder of a {@link ChromeDriver}.
 */
class ChromeDriverBuilder {

    private static final String CHROME_SWITCHES = "chrome.switches";
    private static final String PREFS = "prefs";
    private static boolean CHROMEDRIVER_VERBOSE = Boolean.getBoolean("chromedriver.verbose");

    private static String OS = System.getProperty("os.name");

    private static File resolveDriverExecutableFile() {
        switch (OS) {
            case "Mac OS X":
            case "Darwin":
                try {
                    return Resources.getResourceAsFile(SeleniumExternalDriversConstants.CHROME_DRIVER_MAC_PATH);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            default:
                return new File(SeleniumExternalDriversConstants.CHROME_DRIVER_DEFAULT_PATH);
        }
    }

    private final ImmutableMap.Builder<String, Object> prefs = ImmutableMap.builder();
    private final ImmutableSet.Builder<String> switches = ImmutableSet.builder();

    private final DesiredCapabilities capabilities;
    private final ChromeOptions options;

    ChromeDriverBuilder() {
        this.capabilities = DesiredCapabilities.chrome();
        this.options = new ChromeOptions();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
    }

    /**
     * Build the {@link ChromeDriver} instance.
     *
     * @return the chrome driver
     */
    public ChromeDriver build() {
        checkState(options.getExperimentalOption(PREFS) == null,
                "prefs must not be configured manually, use ChromeDriverBuilder#pref(String, Object) instead");
        checkState(capabilities.getCapability(CHROME_SWITCHES) == null,
                "chrome.switches must not be configured manually, use ChromeDriverBuilder#chromeSwitch(String) instead");
        options.setExperimentalOption(PREFS, prefs.build());
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability(CHROME_SWITCHES, switches.build());
        return new ChromeDriver(createChromeDriverService(), capabilities);
    }

    /**
     * Modifiy the desired capabilities.
     *
     * @param consumer the capabilities consumer
     * @return this builder
     */
    public ChromeDriverBuilder capabilities(Consumer<DesiredCapabilities> consumer) {
        consumer.accept(capabilities);
        return this;
    }

    /**
     * Adds a switch value to the set of switches.
     *
     * @param value the value
     * @return this builder
     */
    public ChromeDriverBuilder chromeSwitch(String value) {
        switches.add(value);
        return this;
    }

    /**
     * Set the download folder location.
     *
     * @param downloadFolderLocation the download folder location
     * @return this builder
     */
    public ChromeDriverBuilder downloadFolderLocation(String downloadFolderLocation) {
        return pref("download.default_directory", downloadFolderLocation);
    }

    /**
     * Ensures that the chrome session is clean.
     *
     * @return this builder
     */
    public ChromeDriverBuilder ensureCleanSession() {
        return capabilities(capabilities -> capabilities.setCapability(ENSURING_CLEAN_SESSION, true));
    }

    /**
     * Disables the chrome sandbox.
     *
     * @return this builder
     */
    public ChromeDriverBuilder noSandbox() {
        return options(options -> options.addArguments("allow-file-access-from-files")
                                         //.addArguments("use-fake-device-for-media-stream")
                                         .addArguments("use-fake-ui-for-media-stream")
        );
    }

    /**
     * Modifiy the desired chrome options.
     *
     * @param consumer the chrome options consumer
     * @return this builder
     */
    public ChromeDriverBuilder options(Consumer<ChromeOptions> consumer) {
        consumer.accept(options);
        return this;
    }

    /**
     * Sets a experimental preference property.
     *
     * @param key   the property key
     * @param value the property value
     * @return this builder
     */
    public ChromeDriverBuilder pref(String key, Object value) {
        prefs.put(key, value);
        return this;
    }

    private ChromeDriverService createChromeDriverService() {
        return new ChromeDriverService.Builder()
                .usingDriverExecutable(resolveDriverExecutableFile())
                .usingAnyFreePort()
                .withVerbose(CHROMEDRIVER_VERBOSE)
                .build();
    }
}
