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
    And I sleep for 4 seconds
    And I enter Examiner No as 108
    And I sleep for 1 seconds
    And I click on Proceed button
    And I click on Examiner type Interlocutor
    And I click on Proceed button
    And I sleep for 2 seconds
    And I allow camera notification
    And I sleep for 4 seconds
    And I click on Proceed button
    And I click on exam type A2 Key
    And I click on Proceed button
    And I sleep for 3 seconds
    And I click on Record button
    And I sleep for 1 minutes
    And I click on Record button
    And I sleep for 4 seconds
    And I click on ok button
    And I sleep for 4 seconds
    And I select candidate one marks as "3.5"
    And I sleep for 4 seconds
    And I select candidate two marks as "3.5"
    And I sleep for 4 seconds
#    And I click on SUBMIT Button


