package com.ca.rs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {

    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            Properties props = System.getProperties();
            String environment = "";

            if (props.containsKey("environment.name")) {
                environment = props.getProperty("environment.name");
            } else {
                // Uncomment the one that you want to use
                environment = "ci";
//                environment = "sys";

            }

            InputStream in =
            Thread.currentThread().getContextClassLoader().getResourceAsStream("envs/" + environment + ".properties");
            PROPERTIES.load(in);
            in.close();
        }
        catch (IOException e) {
                throw new RuntimeException(e);
        }
    }

    private AppProperties() { }

    public static String baseUrl() {
    return PROPERTIES.getProperty("baseUrl");
    }

    public static String testUrl() {
        return PROPERTIES.getProperty("testUrl");
    }
    public static String apiBaseUrl() { return PROPERTIES.getProperty("api.baseUrl"); }

    public static String userId() {
        return PROPERTIES.getProperty("userId");
    }

    public static String password() {
        return PROPERTIES.getProperty("password");
    }

}
