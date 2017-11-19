
Feature: User can add a new book, so new tips for it can be added
	
	Scenario: Adding nonexistant book
		Given book doesn't exist
		When  it's attempted to be added
		Then  it should exist and message "book added" is displayed 
		
	Scenario: Attempting to add a book without title
		Given book doesn't exist
		When  it's attempted to be added
		Then  it shouldn't exist, and message "Title not specified, book was not added" is displayed
		And   user is asked to retry attempting to add the book
		
	Scenario: Attempting to add a book without author
		Given book doesn't exist
		When  it's attempted to be added
		Then  it shouldn't exist, and message "Author not specified, book was not added" is displayed
		And   user is asked to retry attempting to add the book
	
	Scenario: Attempting to add a book without ISBN
		Given book doesn't exist
		When  it's attempted to be added
		Then  it shouldn't exist, and message "ISBN not specified, book was not added" is displayed
		And   user is asked to retry attempting to add the book