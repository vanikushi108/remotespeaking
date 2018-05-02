package com.ca.rs;

import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.TimeZone.getDefault;

public class Constants {

  public static final DateTimeFormatter TIMESTAMP_FORMAT =
      ofPattern("HH:mm - d MMM YYYY").withZone(getDefault().toZoneId());

  public static final int RANDOM_CASEID_LENGTH_OF_STRING = 36;
}