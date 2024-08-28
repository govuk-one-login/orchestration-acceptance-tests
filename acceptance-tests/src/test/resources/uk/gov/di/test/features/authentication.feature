Feature: Authentication
  Scenario: User successfully login without 2FA
    Given the user comes from the stub relying party with options: "2fa-off"
    Then the user is taken to the "Create your GOV.UK One Login or sign in" page
    When the user selects sign in
    Then the user is taken to the "Enter your email" page
    When user enters "TEST_USER_EMAIL" email address
    Then the user is taken to the "Enter your password" page
    When the user enters their password
    Then the user is returned to the service
    And the user logs out
  
  Scenario: User is logged out on browser closed
    Given the user comes from the stub relying party with options: "2fa-off"
    Then the user is taken to the "Create your GOV.UK One Login or sign in" page
    When the user selects sign in
    Then the user is taken to the "Enter your email" page
    When user enters "TEST_USER_EMAIL" email address
    Then the user is taken to the "Enter your password" page
    When the user enters their password
    Then the user is returned to the service
    When the user closes their browser
    And the user comes from the stub relying party with options: "2fa-off"
    Then the user is taken to the "Create your GOV.UK One Login or sign in" page