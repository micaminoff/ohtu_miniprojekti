
Feature: User can add a new book, so new tips for it can be added
	
    Scenario: User can add a new book
	Given command add is selected
        Given command book is selected
        When author "Matti" and title "Book1" and description "good book" and ISBN "123" are entered
        Then message "New book added!" is displayed

    Scenario: User cannot add existing book
        Given command add is selected
        Given command book is selected
        When author "Robert Martin" and title "Clean Code: A Handbook of Agile Software Craftsmanship" and description "Good book" and ISBN "978-951-98548-9-2" are entered
	Then message "Adding a new book failed!" is displayed