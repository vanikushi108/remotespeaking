@regression
Feature: Interlocutor test
  As an user
  I can do Interlocutor test

  Scenario: Interlocutor test
    Given User navigates to Remote Speaking Page
    And I enter Examiner No as 108
    And I click on Proceed button
    And I click on Examiner type Interlocutor
    And I click on Proceed button
    And I click on Proceed button
    And I click on exam type A2 Key
    And I click on Proceed button
    And I sleep for 5 seconds