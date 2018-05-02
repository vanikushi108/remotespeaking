package com.ca.rs.map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cucumber.api.DataTable;
import gherkin.formatter.model.DataTableRow;
import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Nullable;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Static class for turning a DataTable into a Map<String, String>
 */
public class DataTableParser {
  public static ImmutableMap<String, String> parseTemplateParamsDataTable(@Nullable DataTable dataTable) {
    // technically this is different from parseJsonDataTable, although practically not
    if (dataTable != null) {
      return parseSimpleMapDataTable(dataTable, "key", "value");
    } else {
      return ImmutableMap.of();
    }
  }

  public static ImmutableMap<String, List<String>> parseTemplateWithListParamsDataTable(@Nullable DataTable dataTable) {
    // technically this is different from parseJsonDataTable, although practically not
    if (dataTable != null) {
      return parseListedMapDataTable(dataTable, "key", "value");
    } else {
      return ImmutableMap.of();
    }
  }

  public static List<JSONObject> parseMultiObjectDataTable(@Nullable DataTable dataTable) throws JSONException {
    // technically this is different from parseJsonDataTable, although practically not
    if (dataTable != null) {
      return parseDataTableToJsonObject(dataTable, "key", "value");
    } else {
      return (List<JSONObject>) JSONObject.NULL;
    }
  }

  private static ImmutableMap<String, String> parseSimpleMapDataTable(DataTable dataTable,
                                                                      String keyName, String valueName) {
    checkNotNull(dataTable, "data table must not be null");
    checkNotNull(keyName, "key name must not be null");
    checkNotNull(valueName, "value name must not be null");

    List<DataTableRow> gherkinRows = dataTable.getGherkinRows();
    ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
    for (int i = 0; i < gherkinRows.size(); i++) {
      DataTableRow row = gherkinRows.get(i);
      checkArgument(row.getCells().size() == 2,
                    "Expected 2 cells but got %s at line %s", row.getCells().size(), row.getLine());

      Iterator<String> it = row.getCells().iterator();
      String name = checkNotNullNorEmpty(it.next(), keyName + " column");
      String value = checkNotNullNorEmpty(it.next(), valueName + " column");

      // skip header if present
      if (i == 0 && keyName.equalsIgnoreCase(name) && valueName.equalsIgnoreCase(value)) {
        continue;
      }

      builder.put(name, value);
    }
    return builder.build();
  }

  private static List<JSONObject> parseDataTableToJsonObject(DataTable dataTable,
                                                             String keyName, String valueName) throws JSONException {
    checkNotNull(dataTable, "data table must not be null");
    checkNotNull(keyName, "key name must not be null");
    checkNotNull(valueName, "value name must not be null");

    List<DataTableRow> gherkinRows = dataTable.getGherkinRows();
    Map<String, JSONObject> map = new HashMap<>();
    for (int i = 1; i < gherkinRows.size(); i++) {
      DataTableRow row = gherkinRows.get(i);
      Iterator<String> it = row.getCells().iterator();
      String name = checkNotNullNorEmpty(it.next(), keyName + " column");
      String value = checkNotNullNorEmpty(it.next(), valueName + " column");
      if (map.keySet().contains(name.split("\\W")[0])) {
        JSONObject jsonObject = map.get(name.split("\\W")[0]);
        jsonObject.put(name.split("\\W")[1], value);
      } else {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(name.split("\\W")[1], value);
        map.put(name.split("\\W")[0], jsonObject);
      }
    }
    return new ArrayList(map.values());
  }

  private static ImmutableMap<String, List<String>> parseListedMapDataTable(DataTable dataTable,
                                                                            String keyName, String valueName) {
    checkNotNull(dataTable, "data table must not be null");
    checkNotNull(keyName, "key name must not be null");
    checkNotNull(valueName, "value name must not be null");

    List<DataTableRow> gherkinRows = dataTable.getGherkinRows();
    Map<String, List<String>> builderMap = Maps.newHashMap();

    for (int i = 0; i < gherkinRows.size(); i++) {
      DataTableRow row = gherkinRows.get(i);
      checkArgument(row.getCells().size() == 2,
                    "Expected 2 cells but got %s at line %s", row.getCells().size(), row.getLine());

      Iterator<String> it = row.getCells().iterator();
      String name = checkNotNullNorEmpty(it.next(), keyName + " column");
      String value = checkNotNullNorEmpty(it.next(), valueName + " column");

      // skip header if present
      if (i == 0 && keyName.equalsIgnoreCase(name) && valueName.equalsIgnoreCase(value)) {
        continue;
      }

      if (!builderMap.containsKey(name)) {
        builderMap.put(name, Lists.newArrayList());
      }
      builderMap.get(name).add(value);
    }

    ImmutableMap.Builder<String, List<String>> builder = ImmutableMap.builder();
    for (String key : builderMap.keySet()) {
      builder.put(key, ImmutableList.copyOf(builderMap.get(key)));
    }
    return builder.build();
  }

  private static <T extends CharSequence> T checkNotNullNorEmpty(T cs, String propertyName) {
    checkNotNull(cs, "%s must not be null", propertyName);
    checkNotNull(propertyName, "propertyName must not be null");
    checkArgument(cs.length() > 0, "%s must not be empty", propertyName);
    return cs;
  }
}
