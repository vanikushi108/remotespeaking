package com.ca.rs;

import com.google.common.io.Resources;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.io.Resources.getResource;
import static org.skyscreamer.jsonassert.JSONParser.parseJSON;


/**
 * A collection of useful methods for using with Json
 */
public final class JsonUtils {

  private JsonUtils() {}

  public static JSONObject loadResource(String resourcePath) throws IOException, JSONException {
    String json = Resources.toString(getResource(resourcePath), UTF_8);
    return (JSONObject) parseJSON(json);
  }

  public static JSONObject convertXMLToJson(String xmlString)
      throws IOException, JSONException, ParserConfigurationException, SAXException {
    checkNotNull(xmlString);
    JSONObject jsonObject = XML.toJSONObject(xmlString);
    return jsonObject ;
  }
}
