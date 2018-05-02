package com.ca.utilclasses;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;

public class ScreenShotUtil {

    public static void getscreenshot() {
        File scrFile = null;
        try {

            /**
             * The below method will save the screen shot in d drive with name
             * "screenshot.png" var/folders/wc/9dhtbd0d4gn0k_13nw7p64z00000gn/T/
             * screenshot4082555518467309717.png
             *
             */
            String destinationPath = "screenshot/" + "failedPageScreenshot_" + "_" + ".png";
            FileUtils.copyFile(scrFile, new File(destinationPath));

        } catch (WebDriverException somePlatformsDontSupportScreenshots) {

        } catch (IOException e) {

        } catch (Exception ex) {
        }
    }
}
