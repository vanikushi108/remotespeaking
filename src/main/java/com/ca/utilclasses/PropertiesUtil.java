package com.ca.utilclasses;

import java.util.Properties;

/**
 * Created by TKRPRASAD on 09/05/2017.
 */
public class PropertiesUtil {

    private static final Properties props = new Properties();

    public PropertiesUtil() {
        PropertiesUtil.loadPropertiesFiles();
    }

    public static void loadPropertiesFiles() throws RuntimeException {
        try {
            if (props.size() == 0) {

                String propertiesFile = "application.properties";
                props.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesFile));
                props.putAll(System.getProperties());

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }


}
