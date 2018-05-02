package com.ca.rs.steps;

import cucumber.api.java.en.Then;

import java.io.File;
import java.io.IOException;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class SyncSteps {

  private static final String SYNC_FILE_NAME = "SYNC.lock";

  /**
   * Flags a test as concurrently sensitive.
   *
   * This will cause all other "concurrently sensitive" to wait for the current test to finish before they continue.
   *
   * WARNING - A test must manually exit the Sync block. DO NOT expect cleanup to sort this for you.
   */
  @Then("^I enter a synchronised block")
  public void enterSyncBlock() throws IOException {
    File yourFile = new File(SYNC_FILE_NAME);

    while (yourFile.exists()) {
      // wait!
      sleepUninterruptibly(50, MILLISECONDS);
    }
    yourFile.createNewFile();
  }

  /**
   * Flags a test as leaving the concurrently sensitive part of execution.
   *
   * This will unblock the other waiting threads.
   */
  @Then("^I exit a synchronised block")
  public void exitSyncBlock() {
    new File(SYNC_FILE_NAME).delete();
  }


}
