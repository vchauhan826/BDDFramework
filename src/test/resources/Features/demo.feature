@all
Feature: FaceBook Login Module
  I want to use this template for my feature file

  Scenario: Facebook Login with static data
    Given I Navigave to Login Page
    When I Enter userName as mana.santosh9991@gmail.com
    And I Enter password as 1234
    And I Click on LogIn
    Then Verify User is Logged in or not

  Scenario Outline: Facebook Login with Test Data
    Given I Navigave to Login Page
    When I Enter userName as <name>
    And I Enter password as <password>
    And I Click on LogIn
    Then Verify User is Logged in or not

    Examples: 
      | name            | password        |
      | userName_Field1 | password_Field1 |
