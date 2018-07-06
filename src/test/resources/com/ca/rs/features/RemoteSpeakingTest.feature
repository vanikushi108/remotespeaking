@regression
Feature: Remote speaking
  As an user
  I need to do
  Remote speaking tests

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
    And I sleep for 1 seconds
    And I click on test type dropdown
    And I select test type as "A2 Key"
    And I sleep for 1 seconds
    #And I enter Candidate1 name as "Test1"
    #And I enter candidate1 number as "2"
    #And I enter candidate2 name as "Test2"
    #And I enter candidate2 number as "3"
#    And I select part2 inputBox1 test material as "2"
#    And I sleep for 1 seconds
#    And I select part2 inputBox2 test material as "3"
#    And I sleep for 1 seconds
#    And I select part3 inputBox1 test material as "4"
#    And I sleep for 1 seconds
#    And I select part3 inputBox2 test material as "5"
#    And I sleep for 1 seconds
    #And I click on exam type A2 Key
    #And I sleep for 3 seconds
    #And I click on Proceed button
    #And I sleep for 5 seconds
    #And I click on Record button
    #And I sleep for 8 minutes
    #And I click on Record button
    #And I sleep for 3 seconds
    #And I click on ok button
    #And I sleep for 3 seconds
    #And I select candidate one marks
    #And I select "2.0" from drop down
    #And I select candidate two marks
    #And I select "2.0" from drop down
    #And I click on Submit Button
    #And I click on Confirm Yes Submit Button

  #Scenario: Remote Assessor
    #Given User navigates to Remote Speaking Page
   # And I sleep for 1 seconds
    #And I enter user name as "kasala.v@cambridgeenglish.org"
   # And I enter password as "Remotespeaking1"
    #And I click on Login button
    #And I sleep for 1 seconds
    #And I enter Examiner No as 108
    #And I sleep for 1 seconds
    #And I click on Proceed button
    #And I sleep for 1 seconds
    #And I click on Examiner type Remote Assessor
    #And I sleep for 1 seconds
   # And I click on Proceed button
   # And I sleep for 1 seconds
    #And I click on Proceed button
    #And I sleep for 1 seconds
   # And I click play button
   # And I sleep for 1 seconds
   # And I click on Play button for video Recording
   # And I sleep for 15 seconds
    #And I select candidate one Grammar and Vocabulary
    #And I select "2.0" from drop down
    #And I sleep for 1 seconds
    #And I select candidate one Pronunciation
   # And I select "2.0" from drop down
   # And I select candidate one Interactive Communication
   # And I select "2.0" from drop down
   # And I select candidate two Grammar and Vocabulary
   # And I select "2.0" from drop down
   # And I select candidate two Pronunciation
   # And I select "2.0" from drop down
    #And I select candidate two Interactive Communication
    #And I select "2.0" from drop down
    #And I click on Submit Button
    #And I click on Confirm Yes Submit Button
    #And I sleep for 2 seconds