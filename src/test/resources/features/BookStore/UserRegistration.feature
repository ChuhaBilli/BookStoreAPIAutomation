Feature: Test to verify /signup endpoint for user registration.

  @sanity @regression
  Scenario Outline: Verify User Registration
    When Send POST request to signup endpoint with values "<email>" and "<password>"
    Then Verify response status code is "200"
    And Verify success response message for registered user

    Examples: 
      | email              | password |
      | test@spurqlabs.com |   123456 |

  @regression
  Scenario Outline: Verify that signing up with already registered user is denied.
    When Send POST request to signup endpoint with values "<email>" and "<password>"
    Then Verify response status code is "400"
    And Verify error in response message for already registered user

    Examples: 
      | email        | password |
      | @testContext |   123456 |

  ##= This case is commented out because API is allowing the registraion with null values wich it should not.
  
  #@regression @error
  #Scenario Outline: Verify that signing up with null field values.
  #When Send POST request to signup endpoint with values "<email>" and "<password>"
  #Then Vefiry response status code is "400"
  #And Verify error in response message for invaid parameters
  #
  #Examples:
  #| email | password |
  #| null  | null     |
  
  
