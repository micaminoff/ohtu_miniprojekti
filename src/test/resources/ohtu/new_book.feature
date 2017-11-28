Feature: User can add a new book, so new suggestions for it can be added

# POISTETTU KÄYTÖSTÄ: sovelluksen logiikka muuttunut suggestion lisäämiseen (ei tällä hetkellä pelkän kirjan lisäämistä). Tämä scenario siirretty featureen new_suggestion_feature.
#    Scenario: User can add a new book
#	Given command "add" is selected
#        Given command "book" is selected
#        When title "Book1" and author "Matti" and description "good book" and ISBN "123" are entered
#        Then message "New suggestion with book added!" is displayed

# POISTETTU KÄYTÖSTÄ: sovelluksen logiikka muuttunut suggestion lisäämiseen (ei tällä hetkellä pelkän kirjan lisäämistä)
#    Scenario: User cannot add existing book
#        Given command "add" is selected
#        Given command "book" is selected
#        When title "Clean Code: A Handbook of Agile Software Craftsmanship" and author "Robert Martin" and description "Good book" and ISBN "978-951-98548-9-2" are entered
#	Then message "Adding a new book failed!" is displayed