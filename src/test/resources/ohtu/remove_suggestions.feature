Feature: User can removeremove suggestions

    Scenario: User can see a list of deletable suggestions
        Given command "remove" is selected
        When command "n" is entered
        Then message "Title: Mitä tietojenkäsittely on?" is displayed

    Scenario: User can remove a suggestion
        Given command "remove" is selected
        And command "n" is selected
        When command "7" is entered
        And command "y" is entered
        Then message "Suggestion removed!" is displayed

    Scenario: User can search for and remove specific suggestions
        Given command "remove" is selected
        And command "y" is selected
        When command "Joulu" is entered
        Then message "Title: Joulun käyttölahjoja" is displayed

    Scenario: User can choose not to remove a suggestion
        Given command "remove" is selected
        And command "n" is selected
        When command "0" is entered
        And command "n" is entered
        Then message "Command (list" is displayed
