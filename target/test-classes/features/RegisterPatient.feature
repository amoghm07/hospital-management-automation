Feature: Register a new patient in OpenMRS

  @Positive
  Scenario: Register a new patient successfully
    Given the user is on the login page
    When the user enters username "admin" and password "Admin123"
    And clicks the Login button
    And the user clicks on "Register a Patient"
    And the user enters given name "Walter"
    And the user enters family name "White"
    And the user selects gender "Male"
    And the user enters birth day "20"
    And the user selects birth month "5"
    And the user enters birth year "1975"
    And the user enters address "221B Baker Street, London"
    And the user enters phone number "9876543210"
    And the user selects relationship type "Parent"
    And the user enters relative name "Jane White"
    Then the patient with name "Walter White" should be registered successfully

  @Negative
  Scenario: Fail to register a patient due to missing address
    Given the user is on the login page
    When the user enters username "admin" and password "Admin123"
    And clicks the Login button
    And the user clicks on "Register a Patient"
    And the user enters given name "Alice"
    And the user enters family name "Smith"
    And the user selects gender "Female"
    And the user enters birth day "10"
    And the user selects birth month "12"
    And the user enters birth year "1992"
    And the user leaves the address field blank
    Then the error message "You need to provide a value for at least one field" should be displayed
