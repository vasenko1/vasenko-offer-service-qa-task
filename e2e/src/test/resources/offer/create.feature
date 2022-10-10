Feature: Create offer

  Scenario: Offer with 'Long Max' Prize value is created
    Given Offer page is opened
    When User create new offer with valid data
    Then Saved offer data is not corrupted

  Scenario: Offer with 'Long+1' Prize value is not created
    Given Offer page is opened
    When User trying to create new offer with invalid data
    Then The offer is not created