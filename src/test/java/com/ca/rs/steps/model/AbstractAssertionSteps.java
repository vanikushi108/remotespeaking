package com.ca.rs.steps.model;

import com.ca.rs.map.DataTableParser;
import com.ca.rs.models.ModelContainer;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import cucumber.api.DataTable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Abstract parent Cucumber Step file for dealing with asserting checks against a json object
 *
 * Created by TKRPRASAD on 27/07/2017.
 */
public abstract class AbstractAssertionSteps {

  private final ModelContainer modelContainer;

  @Autowired
  public AbstractAssertionSteps(ModelContainer modelContainer) {
    this.modelContainer = checkNotNull(modelContainer, "modelContainer must not be null");
  }

  public void checkObjectsSubjectArray(List<String> subjectCodes) throws Throwable {
    checkNotNull(subjectCodes, "subjectCodes must not be null");
    checkObjectsSubjectArray(getDefaultObjectName(), subjectCodes);
  }

  public void checkSubjectCodes(String objectName, List<String> subjectCodes) throws Throwable {
    checkObjectsSubjectArray(objectName, subjectCodes);
  }

  public void theObjectWithNameShouldBeEqualToTheFixture(String objectName, String fixturePath) throws Throwable {
    checkNotNull(fixturePath, "fixturePath must not be null");
    executeObjectEqualToFixture(objectName, fixturePath, ImmutableMap.of());
  }

  public void theObjectWithNameShouldBeEqualToTheFixtureWithParameters(String objectName, String fixturePath,
                                                                       @Nullable DataTable table) throws Throwable {
    checkNotNull(fixturePath, "fixturePath must not be null");
    executeObjectEqualToFixture(objectName, fixturePath, DataTableParser.parseTemplateParamsDataTable(table));
  }

  public void theObjectShouldBeEqualToTheFixtureWithParameters(String fixturePath,
                                                               @Nullable DataTable table) throws Throwable {
    checkNotNull(fixturePath, "fixturePath must not be null");
    executeObjectEqualToFixture(getDefaultObjectName(), fixturePath, DataTableParser.parseTemplateParamsDataTable(table));
  }

  protected abstract String getDefaultObjectName();

  private void checkObjectsSubjectArray(String objectName, List<String> subjectCodes) throws Throwable {
    checkNotNull(modelContainer.get(objectName), "modelContainer.get(objectName) must not be null");

    assertThat(isIdenticalMatch(modelContainer.get(objectName), subjectCodes)).isTrue();
  }

  protected boolean isIdenticalMatch(JSONObject object, List<String> subjectCodes) throws JSONException {
    boolean isMatched = isMatch(object, subjectCodes);

    // Is that the only thing you contain?
    return isMatched && subjectCodes.size() == object.getJSONArray("subject").length();
  }

  private boolean isMatch(JSONObject object, List<String> subjectCodes) throws JSONException {
    if (object.has("subject")) {
      // do you contain the subjectCodes i'm looking for?
      JSONArray subjects = object.getJSONArray("subject");

      for (String subjectCode : subjectCodes) {
        boolean foundSubjectCode = false;

        for (int i = 0; i < subjects.length(); i++) {
          JSONObject subject = (JSONObject) subjects.get(i);
          if (subjectCode.equals(subject.getString("code"))) {
            foundSubjectCode = true;
          }
        }

        if (!foundSubjectCode) {
          return false;
        }
      }
    } else {
      return subjectCodes.isEmpty();
    }

    return true;
  }

  private void executeObjectEqualToFixture(String objectName, String fixturePath,
                                           ImmutableMap<String, String> rewriteParameters)
      throws IOException, JSONException {
    checkNotNull(modelContainer.get(objectName), "modelContainer.get(objectName) must not be null");

    String template = Resources.toString(Resources.getResource(fixturePath), Charsets.UTF_8);

    for (Map.Entry<String, String> entry : rewriteParameters.entrySet()) {
      template = template.replace("${" + entry.getKey() + "}", entry.getValue());
    }

    JSONObject eventJsonObject = modelContainer.get(objectName);
    // If the Item has a uri, attempt to copy this into the template.
    // This would be fiddly to pass in as a parameter & express this in cucumber
    if (eventJsonObject.has("uri")) {
      template = template.replace("${uri}", eventJsonObject.getString("uri"));
    }

    JSONAssert.assertEquals(template, eventJsonObject, false);
  }
}
