Feature: Logging in for DemoOpenMRS

  Scenario:
    Given User is on register patient panel
    When User provides patient data and accepts
    Then Registered patient data should be equal to provided data