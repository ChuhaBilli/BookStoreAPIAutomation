Feature: Health Check End Point.

  @test
  Scenario Outline: Verify User Registration
    When Hit Request for Register User "<email>" and "<password>"
    Then Validate Response Status code "200"
    And Verify success response message for registered user

    Examples: 
      | email           | password |
      | test1@test1.com |   123456 |
