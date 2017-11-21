Feature: User can search for suggestions

# Successful searches
    Scenario: search suggestions by book's title
	Given command find is selected
	Given command title is selected
	When search term "Clean Code: A Handbook of Agile Software Craftsmanship" is entered
	Then book is found

    Scenario: search suggestions by author
        Given command find is selected
	Given command creator is selected
	When search term "Robert Martin" is entered
	Then book is found

    Scenario: search suggestions by description
        Given command find is selected
	Given command description is selected
	When search term "Good book" is entered
	Then book is found

    Scenario: search suggestions by ISBN
        Given command find is selected
	Given command isbn is selected
	When search term "978-951-98548-9-2" is entered
	Then book is found