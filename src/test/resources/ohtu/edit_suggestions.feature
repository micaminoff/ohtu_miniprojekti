Feature: User can edit suggestions

  Scenario: User can see a list of editable suggestions
    Given command "edit" is selected
    When command "n" is entered
    Then message "Title: Mitä tietojenkäsittely on?" is displayed

  Scenario: User can edit a bookSuggestion title
    Given command "edit" is selected
    And command "n" is selected
    When command "2" is entered
    And command "1" is entered
    And command "title" is entered
    And command "Clean Code: edited" is entered
    And command "list" is entered
    Then message "Clean Code: edited" is displayed

  Scenario: User can edit a blogSuggestion author
    Given command "edit" is selected
    And command "n" is selected
    When command "0" is entered
    And command "1" is entered
    And command "author" is entered
    And command "David Edited" is entered
    And command "list" is entered
    Then message "David Edited" is displayed

  Scenario: User can edit a videoSuggestion description
    Given command "edit" is selected
    And command "n" is selected
    When command "7" is entered
    And command "1" is entered
    And command "description" is entered
    And command "This is an edited video!" is entered
    And command "list" is entered
    Then message "This is an edited video!" is displayed
