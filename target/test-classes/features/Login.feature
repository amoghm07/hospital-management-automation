Feature: Login functionality for OpenMRS

  @Positive
  Scenario: Login with valid credentials
    Given the user is on the login page
    When the user enters username "admin" and password "Admin123"
    And clicks the Login button
    Then the user should see "Login successful"

  @Negative
  Scenario: Login with invalid credentials
    Given the user is on the login page
    When the user enters username "wrong" and password "wrong"
    And clicks the Login button
    Then the user should see "Invalid username/password. Please try again."
