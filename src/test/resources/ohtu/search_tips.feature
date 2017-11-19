Feature: User can search for tips

	Scenario: search tips by book's title
		Given tips exist
		When  user selects option "Search by title of book: "
		Then  user is asked for a title of a book
		Then  user gives title "Ohjelmointi 101"
		Then  user is given a list of tips related to book with title "Ohjelmointi 101"
		