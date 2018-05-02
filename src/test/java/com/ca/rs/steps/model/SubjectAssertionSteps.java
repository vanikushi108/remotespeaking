package com.ca.rs.steps.model;


import com.ca.rs.models.ModelContainer;
import com.ca.rs.steps.StepFields;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Cucumber Step file for dealing with asserting checks against a Subject
 * Created by TKRPRASAD on 01/8/2017.
 */
public class SubjectAssertionSteps extends AbstractAssertionSteps {

  @Autowired
  public SubjectAssertionSteps(ModelContainer modelContainer) {
    super(modelContainer);
  }

  @Then("^the Subject's subject array contains exactly the following codes:$")
  public void checkSingleSubjectsSubjectArray(List<String> subjectCodes) throws Throwable {
    checkObjectsSubjectArray(subjectCodes);
  }

  @Then("^the Subject named " + StepFields.LITERAL + "'s subject array contains exactly the following codes:$")
  public void checkSubjectSubjectCodes(String eventName, List<String> subjectCodes) throws Throwable {
    checkSubjectCodes(eventName, subjectCodes);
  }

  @Then("^the Subject named " + StepFields.LITERAL + " should be equal to the fixture " + StepFields.LITERAL + "$")
  public void theSubjectWithNameShouldBeEqualToTheFixture(String subjectName, String fixturePath) throws Throwable {
    theObjectWithNameShouldBeEqualToTheFixture(subjectName, fixturePath);
  }

  @Then("^the Subject named " + StepFields.LITERAL + " should be equal to the fixture " + StepFields.LITERAL + " with parameters:$")
  public void theSubjectWithNameShouldBeEqualToTheFixtureWithParameters(String subjectName, String fixturePath,
                                                                        @Nullable DataTable table) throws Throwable {
    theObjectWithNameShouldBeEqualToTheFixtureWithParameters(subjectName, fixturePath, table);
  }

  @Then("^the Subject should be equal to the fixture " + StepFields.LITERAL + " with parameters:$")
  public void theSubjectShouldBeEqualToTheFixtureWithParameters(String fixturePath,
                                                                @Nullable DataTable table) throws Throwable {
    theObjectShouldBeEqualToTheFixtureWithParameters(fixturePath, table);
  }

  @Override
  protected String getDefaultObjectName() {
    return ModelContainer.UNNAMED_EVENT;
  }
}
