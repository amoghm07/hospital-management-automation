Feature: Search for a patient in OpenMRS

  @Positive
  Scenario: Search for an existing patient
    Given the user is on the login page
    When the user enters username "admin" and password "Admin123"
    And clicks the Login button
    And the user clicks on Find Patient Record
    And the user searches for patient with name "John Doe"
    Then the patient with name "John Doe" should be displayed

  @Negative
  Scenario: Search for a non-existing patient
    Given the user is on the login page
    When the user enters username "admin" and password "Admin123"
    And clicks the Login button
    And the user clicks on Find Patient Record
    And the user searches for patient with name "Non Existing Patient"
    Then the patient with name "Non Existing Patient" should not be displayed
