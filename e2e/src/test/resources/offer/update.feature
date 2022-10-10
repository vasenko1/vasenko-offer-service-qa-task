Feature: Update offer

  Scenario: Offer Name value is updated successfully
    Given New offer is already created
    And Offer Page opened
    When User updating the offer Name
    Then Only offers Name value is updated

  Scenario: Offer Prize value is updated successfully
    Given New offer is already created
    And Offer Page opened
    When User updating the offer Prize
    Then Only offers Prize value is updated

  Scenario: Offer Description value is updated successfully
    Given New offer is already created
    And Offer Page opened
    When User updating the offer Description
    Then Only offers Description value is updated