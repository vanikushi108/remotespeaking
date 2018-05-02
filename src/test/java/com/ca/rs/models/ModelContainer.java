package com.ca.rs.models;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A wrapper around a Map to provide
 * a) the ability to get & have the return type be the correct object
 * b) a place to store a list of default keys for the different usages
 */
@Component
public class ModelContainer {

  // List of the default values for the various models we store

  public static final String UNNAMED_EVENT = "<UNNAMED_EVENT>";
  public static final String UNNAMED_URI_ID = "<UNNAMED_URI_ID>";
  public static final String UNNAMED_LOCAL_STORAGE = "<UNNAMED_LOCAL_STORAGE>";
  public static final String UNNAMED_DOWNLOAD = "<UNNAMED_DOWNLOAD>";
  public static final String UNNAMED_EMAIL_ADDRESS = "<UNNAMED_EMAIL_ADDRESS>";
  public static final String UNNAMED_TEST_NAME = "<UNNAMED_TEST_NAME>";
  public static final String UNNAMED_TEST_ID = "<UNNAMED_TEST_ID>";
  public static final String UNNAMED_PAGE_NUMBER = "<UNNAMED_PAGE_NUMBER>";

  // Internal map handling
  private final Map<String, Object> modelContainer = Maps.newHashMap();
  private final Map<String, List<String>> markers = Maps.newHashMap();
  private String lastAddedValueKey;

  public void put(String key, Object value) {
    modelContainer.put(key, value);
    lastAddedValueKey = key;
  }

  public void put(String key, Object value, String marker) {
    put(key, value);

    if (!markers.containsKey(marker)) {
      markers.put(marker, new ArrayList<>());
    }
    markers.get(marker).add(key);
  }

  public boolean contains(String key) {
    return modelContainer.get(key) != null;
  }

  public <Any> Any get(String key) {
    return (Any) modelContainer.get(key);
  }

  public <Any> Any getLastAddedValue() {
    return get(lastAddedValueKey);
  }

  public List<Object> list(Class clazz) {
    return modelContainer.values().stream()
        .filter(object -> object.getClass().equals(clazz))
        .collect(Collectors.toList());
  }

  public List<String> listKeys(String marker) {
    return modelContainer.keySet()
        .stream()
        .filter(key -> markers.get(marker) != null
                       && markers.get(marker).contains(key))
        .collect(Collectors.toList());
  }

  public void clear() {
    modelContainer.clear();
  }
}
