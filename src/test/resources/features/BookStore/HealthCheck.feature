Feature: Tests to verify /health (health check) End Point.

  @test
  Scenario: Verify success response of Health Check
    When Hit Request for Health Check
    Then Validate Response Status code "200"
    And Verify status response message for health check
