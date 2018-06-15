
Feature: Remote Assessor
  As an user
  I can play back videos and mark video

  Scenario: Remote Assessor
    Given User navigates to Remote Speaking Page
    And I sleep for 1 seconds
    And I enter user name as "kasala.v@cambridgeenglish.org"
    And I enter password as "Remotespeaking1"
    And I click on Login button
    And I sleep for 4 seconds
    And I enter Examiner No as 108
    And I sleep for 1 seconds
    And I click on Proceed button
    And I click on Examiner type Remote Assessor
    And I click on Proceed button
    And I sleep for 2 seconds
    And I Select a video Recording
    And I click play button
    And I sleep for 2 seconds
    And I click on Play button for video Recording




