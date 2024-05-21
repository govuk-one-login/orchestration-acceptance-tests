Feature: DocApp
  Scenario: User completes a Doc app journey successfully
    When the user visits the doc app relying party
    And the user clicks the continue button
    And the user sends a valid json payload
    Then the user is taken to the user information page