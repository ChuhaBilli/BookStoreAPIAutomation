Feature: Tests to verify /health (health check) End Point.

  @test
  Scenario: Verify success response of Health Check
    When Send GET request to health check endpoint
    Then Vefiry response status code is "200"
    And Verify response message for health check
