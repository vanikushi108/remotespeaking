package com.ca.rs.steps;

import com.google.common.util.concurrent.Uninterruptibles;
import cucumber.api.java.en.And;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.concurrent.TimeUnit.*;

/**
 * Cucumber Step file for dealing with waiting assertions
 */
public class WaitSteps {

  @And("^I sleep for " + StepFields.INTEGER + "ms$")
  public void iSleepForMs(Integer timeToSleep) throws Throwable {
    checkNotNull(timeToSleep, "timeToSleep must not be null");
    Uninterruptibles.sleepUninterruptibly(timeToSleep, MILLISECONDS);
  }

  @And("^I sleep for " + StepFields.INTEGER + " second(s)?$")
  public void iSleepForSeconds(Integer timeToSleep, String ignore) throws Throwable {
    checkNotNull(timeToSleep, "timeToSleep must not be null");
    Uninterruptibles.sleepUninterruptibly(timeToSleep, SECONDS);
  }

  @And("^I sleep for " + StepFields.INTEGER + " minute(s)?$")
  public void iSleepForMins(Integer timeToSleep, String ignore) throws Throwable {
    checkNotNull(timeToSleep, "timeToSleep must not be null");
    Uninterruptibles.sleepUninterruptibly(timeToSleep, MINUTES);
  }
}
