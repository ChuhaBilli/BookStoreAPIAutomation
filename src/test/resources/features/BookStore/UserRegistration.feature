Feature: Test to verify /signup endpoint for user registration.

  @sanity
  Scenario Outline: Verify User Registration
    When Send POST request to signup endpoint with values "<email>" and "<password>"
    Then Vefiry response status code is "200"
    And Verify success response message for registered user

    Examples: 
      | email              | password |
      | test@spurqlabs.com |   123456 |
