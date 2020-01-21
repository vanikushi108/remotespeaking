
Feature: Remote speaking
  As an user
  I need to do
  Remote speaking tests

  Scenario: Interlocutor C2Proficiency Test
    Given User navigates to Remote Speaking Page
    And I sleep for 1 seconds
    And I enter user name as "kasala.v@cambridgeenglish.org"
    And I enter password as "Vanitha123"
    And I click on Login button
    And I sleep for 2 seconds
    And I enter Examiner No as 108
    And I sleep for 1 seconds
    And I click on Proceed button
    And I Enter Center as "CA111"
    And I sleep for 1 seconds
    And I click on Proceed button
    And I click on Examiner type Interlocutor
    And I click on Proceed button
    And I sleep for 1 seconds
    And I click on Proceed button
    And I sleep for 1 seconds
    And I click on test type dropdown
    And I select test type as "C2 Proficiency"
    And I sleep for 1 seconds
    And I enter Candidate one name as "AutoTest1"
    And I enter candidate Two name as "AutoTest2"
    And I enter candidate One number as "1"
    And I enter candidate Two number as "2"
    And I select partTwo inputBox test material as "2"
    And I sleep for 1 seconds
    And I select partTwo inputBoxTwo test material as "3"
    And I sleep for 1 seconds
    And I select partThree inputBoxOne test material as "4"
    And I sleep for 1 seconds
    And I select partThree inputBoxTwo test material as "5"
    And I sleep for 1 seconds
    And I click on Proceed button
    And I sleep for 5 seconds
    And I click on Record button
    And I sleep for 10 seconds
    And I click on Record button
    And I sleep for 3 seconds
    And I click on ok button
    And I sleep for 3 seconds
    And I select candidate one marks
    And I select "2.0" from drop down
    And I select candidate two marks
    And I select "2.0" from drop down
    And I click on Submit Button
    And I click on Confirm Yes Submit Button

