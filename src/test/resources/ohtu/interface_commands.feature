Feature: User Interface works as intended

  Scenario: User cannot add a suggestion for an invalid type of suggestable
    Given command "add" is selected
    When command "bool" is entered
    Then message "Unknown command!" is displayed

  Scenario: User is informed when giving incorrect command
    When command "lol, testaus" is entered
    Then message "Unknown command!" is displayed

  Scenario: User is informed when giving incorrect command
    Given command "find" is selected
    And command "lol, testaus" is selected
    When command "q" is entered
    Then message "Unknown command!" is displayed

  Scenario: User can go back a step in the program when searching
    Given command "find" is selected
    And command "q" is selected
    When command "add" is entered
    Then message "What would you like to add? (types: book, blog, video, podcast)" is displayed