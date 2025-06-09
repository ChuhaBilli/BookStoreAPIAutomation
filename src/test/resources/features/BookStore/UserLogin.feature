Feature: Test to verify /login endpoint for user login.

  #= For logging in a user we 1st needs to register the same user
  #= To run this test for login independently we need to 1st register
  #= the user and then login.
  @sanity
  Scenario Outline: Verify User Login
    When Send POST request to signup endpoint with values "<email>" and "<password>"
    Then Verify response status code is "200"
    And Verify success response message for registered user
    When Send POST request to login endpoint using registered user credentials
    Then Verify response status code is "200"

    Examples: 
      | email              | password |
      | test@spurqlabs.com |   123456 |

  @sanity @regression
  Scenario Outline: Verify User Login with invalid email is rejected.
    When Send POST request to login endpoint with values "<email>" and "<password>"
    Then Verify response status code is "400"
    Then Verify response message for invalid credentials

    Examples: 
      | email                 | password |
      | invalid@spurqlabs.com |   123456 |

  @sanity @regression
  Scenario Outline: Verify User Login with invalid pasword is rejected.
    When Send POST request to login endpoint with values "<email>" and "<password>"
    Then Verify response status code is "400"
    Then Verify response message for invalid credentials

    Examples: 
      | email        | password |
      | @testContext |        1 |
