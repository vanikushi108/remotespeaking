package com.ca.rs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Load the file from a particular path
 */
public class LoadFileResourcesHelper {

    public static String getResourceAsString(final String fileName) throws URISyntaxException, IOException {
        return new String(Files.readAllBytes(Paths.get(LoadFileResourcesHelper.class.getClassLoader().getResource(fileName).toURI())), StandardCharsets.UTF_8);
    }

    public static String generateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
