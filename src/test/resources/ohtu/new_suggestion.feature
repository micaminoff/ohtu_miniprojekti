Feature: User can add a new suggestion

  Scenario: User can add a suggestion with a new book
    Given command "add" is selected
    Given command "book" is selected
    When title "Book1" and author "Matti" and description "good book" and ISBN "123" are entered
    Then message "New suggestion with book added!" is displayed

  Scenario: User cannot add a suggestion for an invalid type of suggestable
    Given command "add" is selected
    When command "bool" is entered
    Then message "Unknown command!" is displayed

# EI VIELÄ IMPLEMENTOITU Appissa eikä testinä.
#  Scenario: Adding a suggestion for a nonexistant book
#    Given title "Book1" is given
#    When book with title "Book1" isn't found
#    Then user is asked for the rest of the book's attributes
#    And book with title "Book1" exists
#    And user is asked for the rest of the suggestion's attributes
#    And suggestion exists, and it's related to a book with title "Book1"
#
# EI VIELÄ IMPLEMENTOITU Appissa eikä testinä.
#  Scenario: Adding a suggestion for an existing book
#    Given title "Book1" is given
#    When book with title "Book1" is found
#    Then user is asked for the rest of the suggestion's attributes
#    And suggestion exists, and it's related to a book with title "Book1"
