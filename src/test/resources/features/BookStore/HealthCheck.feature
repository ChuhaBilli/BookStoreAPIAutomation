Feature: Tests to verify /health (health check) End Point.

  @sanity
  Scenario: Verify success response of Health Check
    When Send GET request to health check endpoint
    Then Verify response status code is "200"
    And Verify response message for health check
