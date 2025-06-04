Feature: Health Check End Point.

  @test
  Scenario: Verify success response of Health Check
    When Hit Request for Health Check
    Then Validate Response Status code "200"
    And Verify status response message for health check

  @test
  Scenario Outline: Verify User Registration
    When Hit Request for Register User "<email>" and "<password>"
    Then Validate Response Status code "200"
    And Verify success response message for registered user

    Examples: 
      | email           | password |
      | test1@test1.com |   123456 |

  @test
  Scenario Outline: Verify User Login
    When Hit Request for Login User "<email>" and "<password>"
    Then Validate Response Status code "200"

    Examples: 
      | email           | password |
      | test1@test1.com |   123456 |
