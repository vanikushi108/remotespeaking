package com.ca.rs.steps.model;

import com.ca.rs.models.ModelContainer;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.ca.rs.models.ModelContainer.UNNAMED_EVENT;
import static com.ca.rs.steps.StepFields.LITERAL;
import static org.assertj.core.api.Assertions.fail;

/**
 * Cucumber Step file for dealing with asserting checks against an Event
 * Created by TKRPRASAD on 01/08/2017.
 */
public class EventAssertionSteps extends AbstractAssertionSteps {

  private final ModelContainer modelContainer;

  @Autowired
  public EventAssertionSteps(ModelContainer modelContainer) {
    super(modelContainer);
    this.modelContainer = modelContainer;
  }

  @Then("^the Event's subject array contains exactly the following codes:$")
  public void checkSingleEventsSubjectArray(List<String> subjectCodes) throws Throwable {
    checkObjectsSubjectArray(subjectCodes);
  }

  @Then("^the Event named " + LITERAL + "'s subject array contains exactly the following codes:$")
  public void checkEventSubjectCodes(String eventName, List<String> subjectCodes) throws Throwable {
    checkSubjectCodes(eventName, subjectCodes);
  }

  @Then("^the Event should have a subject array that contains exactly the following codes:$")
  public void theEventShouldHaveASubjectArrayThatContainsExactlyTheFollowingCodes(List<String> subjectCodes)
      throws Throwable {
    checkNotNull(subjectCodes, "subjectCodes must not be null");
    checkNotNull(modelContainer.get("event-0"), "modelContainer.get(\"event-0\") must not be null");

    isIdenticalMatch(modelContainer.get("event-0"), subjectCodes);
  }

  // Go through all the events in the event container, and assert there is one match for what is expected
  @Then("^there should be one Event with a subject array that contains exactly the following codes:$")
  public void oneOfTheEventSSubjectArrayContainsExactlyTheFollowingCodes(List<String> subjectCodes) throws Throwable {
    checkNotNull(subjectCodes, "subjectCodes must not be null");
    boolean identicalMatchFound = false;

    List<Object> events = modelContainer.list(JSONObject.class);
    for (Object event : events) {
      if (isIdenticalMatch((JSONObject) event, subjectCodes)) {
        if (!identicalMatchFound) {
          identicalMatchFound = true;
        } else {
          fail("Found multiple events with the same codes.");
        }
      }
    }

    if (!identicalMatchFound) {
      fail("There was no Event that matched the requested configuration.");
    }
  }

  @Then("^the Event named " + LITERAL + " should be equal to the fixture " + LITERAL + "$")
  public void theEventWithNameShouldBeEqualToTheFixture(String eventName, String fixturePath) throws Throwable {
    theObjectWithNameShouldBeEqualToTheFixture(eventName, fixturePath);
  }

  @Then("^the Event named " + LITERAL + " should be equal to the fixture " + LITERAL + " with parameters:$")
  public void theEventWithNameShouldBeEqualToTheFixtureWithParameters(String eventName, String fixturePath,
                                                                      @Nullable DataTable table) throws Throwable {
    theObjectWithNameShouldBeEqualToTheFixtureWithParameters(eventName, fixturePath, table);
  }

  @Then("^the Event should be equal to the fixture " + LITERAL + " with parameters:$")
  public void theEventShouldBeEqualToTheFixtureWithParameters(String fixturePath,
                                                              @Nullable DataTable table) throws Throwable {
    theObjectShouldBeEqualToTheFixtureWithParameters(fixturePath, table);
  }

  @Override
  protected String getDefaultObjectName() {
    return UNNAMED_EVENT;
  }


}
