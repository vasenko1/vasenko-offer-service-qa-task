Feature: Delete offer

  @Smoke
  Scenario: Check offer deletion
    Given New offer just created
    And Offer page opened
    When User clicking the offer delete button
    Then The offer is not found