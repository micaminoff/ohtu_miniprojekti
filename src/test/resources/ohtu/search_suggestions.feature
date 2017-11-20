Feature: User can search for suggestions

	Scenario: search suggestions by book's title
		Given suggestions exist
		When  user selects option "Search by title of book: "
		Then  user is asked for a title of a book
		Then  user gives title "Book1"
		Then  user is given a list of suggestions related to book with title "Book1"
		