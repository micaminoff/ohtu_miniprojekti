Feature: User can search for suggestions

# Successful searches
    Scenario: search suggestions by book's title
	Given command "find" is selected
	Given command "title" is selected
	When search term "Clean Code: A Handbook of Agile Software Craftsmanship" is entered
	Then book is found

    Scenario: search suggestions by author
        Given command "find" is selected
	Given command "creator" is selected
	When search term "Robert Martin" is entered
	Then book is found

    Scenario: search suggestions by description
        Given command "find" is selected
	Given command "description" is selected
	When search term "Noted software expert Robert C. Martin presents" is entered
	Then book is found

    Scenario: search suggestions by ISBN
        Given command "find" is selected
	Given command "isbn" is selected
	When search term "978-951-98548-9-2" is entered
	Then book is found

    Scenario: search by nonexistant title
	Given command "find" is selected
	Given command "title" is selected
	When search term "Nonexistant book" is entered
	Then message "No books found." is displayed

    Scenario: search by nonexistant title
        Given command "find" is selected
	Given command "creator" is selected
	When search term "Michael Aminoff" is entered
	Then message "No books found." is displayed

    Scenario: search by nonexistant description
        Given command "find" is selected
	Given command "description" is selected
	When search term "Nonexistant desc" is entered
	Then message "No books found." is displayed

    Scenario: search by nonexistant ISBN
        Given command "find" is selected
	Given command "isbn" is selected
	When search term "13379999999" is entered
	Then message "No books found." is displayed