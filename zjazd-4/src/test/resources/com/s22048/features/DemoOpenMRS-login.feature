Feature: Logging in for DemoOpenMRS

  Scenario:
    Given User is on page https://demo.openmrs.org/openmrs/login.htm
    When User logs in using valid admin credentials
    Then User is logged in as admin