
Feature: User can add a new tip
	
	Scenario: Adding a tip for a nonexistant book
		Given title "Ohjelmointi 101" is given
		When  book with title "Ohjelmointi 101" isn't found
		Then  user is asked for the rest of the book's attributes
		And   book with title "Ohjelmointi 101" exists
		And   user is asked for the rest of the tip's attributes
		And   tip exists, and it's related to a book with title "Ohjelmointi 101"
		
	Scenario: Adding a tip for an existing book
		Given title "Ohjelmointi 101" is given
		When  book with title "Ohjelmointi 101" is found
		Then  user is asked for the rest of the tip's attributes 
		And   tip exists, and it's related to a book with title "Ohjelmointi 101"