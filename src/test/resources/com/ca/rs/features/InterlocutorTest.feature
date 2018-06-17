@regression
Feature: Interlocutor test
  As an user
  I can do Interlocutor test

  Scenario: Interlocutor test
    Given User navigates to Remote Speaking Page
    And I sleep for 1 seconds
    And I enter user name as "kasala.v@cambridgeenglish.org"
    And I enter password as "Remotespeaking1"
    And I click on Login button
    And I sleep for 1 seconds
    And I enter Examiner No as 108
    And I sleep for 1 seconds
    And I click on Proceed button
    And I click on Examiner type Interlocutor
    And I click on Proceed button
    And I sleep for 1 seconds
    And I click on Proceed button
    And I click on exam type A2 Key
    And I click on Proceed button
    And I click on Record button
    And I click on Record button
    And I click on ok button
    And I select candidate one marks
    And I select "3.5" from drop down
    And I select candidate two marks
    And I select "3.5" from drop down
    And I click on Submit Button
    And I click on Confirm Yes Submit Button