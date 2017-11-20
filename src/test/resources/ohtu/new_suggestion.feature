
Feature: User can add a new suggestion
	
	Scenario: Adding a suggestion for a nonexistant book
		Given title "Book1" is given
		When  book with title "Book1" isn't found
		Then  user is asked for the rest of the book's attributes
		And   book with title "Book1" exists
		And   user is asked for the rest of the suggestion's attributes
		And   suggestion exists, and it's related to a book with title "Book1"
		
	Scenario: Adding a suggestion for an existing book
		Given title "Book1" is given
		When  book with title "Book1" is found
		Then  user is asked for the rest of the suggestion's attributes 
		And   suggestion exists, and it's related to a book with title "Book1"