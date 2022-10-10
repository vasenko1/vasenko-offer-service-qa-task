Feature: Read offer

  Scenario: Info popup: Offer data is displaying correctly
    Given New offer is just created
    And Offer Page is opened
    When User clicking the offer Info button
    Then Unchanged offer data is shown in Info popup

  Scenario: Table row: Offer data is displaying correctly
    Given New offer is just created
    When Offer Page is opened
    Then Unchanged offer Name is show in table row

  Scenario: Edit modal: Offer data is displaying correctly
    Given New offer is just created
    And Offer Page is opened
    When User clicking the offer Edit button
    Then Unchanged offer data is shown in Edit modal