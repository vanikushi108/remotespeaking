
Feature: In Room Assessor
  As an user
  I can play back videos and mark video

  Scenario: In Room Assessor
    Given User navigates to Remote Speaking Page
    And I sleep for 1 seconds
    And I enter user name as "kasala.v@cambridgeenglish.org"
    And I enter password as "Remotespeaking1"
    And I click on Login button
    And I sleep for 1 seconds
    And I enter Examiner No as 108
    And I sleep for 1 seconds
    And I click on Proceed button
    And I click on Examiner type InRoom Assessor
    And I sleep for 1 seconds
    And I click on Proceed button
    And I sleep for 1 seconds
    And I click on exam type A2 Key
    And I sleep for 1 seconds
    And I click on Proceed button
    And I sleep for 1 seconds
    And I select candidate one Grammar and Vocabulary
    And I select "3.0" from drop down
    And I sleep for 1 seconds
    And I select candidate one Pronunciation
    And I select "3.0" from drop down
    And I select candidate one Interactive Communication
    And I select "3.0" from drop down
    And I select candidate two Grammar and Vocabulary
    And I select "3.0" from drop down
    And I select candidate two Pronunciation
    And I select "3.0" from drop down
    And I select candidate two Interactive Communication
    And I select "3.0" from drop down
    And I click on Submit Button
    And I click on Confirm Yes Submit Button
    And I sleep for 2 seconds