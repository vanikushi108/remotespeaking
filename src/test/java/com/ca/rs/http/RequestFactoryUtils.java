package com.ca.rs.http;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


@Component
public class RequestFactoryUtils {


  /**
   * Takes the url query parameters given in the Map and appends them onto the given URL in the correct format.
   *
   * @param url the source URL to enrich
   * @param properties the query parameters to enrich the URL with
   * @return the compiled URL
   */
  public static String compileQueryParamsIntoUrl(String url, Map<String, List<String>> properties) {
    int index = 0;
    for (String key : properties.keySet()) {
      url += index == 0 ? "?" : "&";

      List<String> values = properties.get(key);
      for (int i = 0; i < values.size(); i++) {
        if (i > 0) {
          url += "&";
        }
        url += key + "=" + values.get(i);
      }

      index++;
    }
    return url;
  }


  public String getResourceAsString(final String fileName) throws URISyntaxException, IOException {
    return new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(fileName).toURI())), StandardCharsets.UTF_8);
  }

}
