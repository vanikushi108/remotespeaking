package com.ca.rs.http;

import com.ca.rs.AppProperties;

public class URLFactory {
  public static String compileBaseUrl() {
    return AppProperties.baseUrl();
  }
  public static String compileApiBaseUrl()
  {
    return AppProperties.apiBaseUrl();
  }

}
