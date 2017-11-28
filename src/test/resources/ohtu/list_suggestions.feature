Feature: User can browse suggestions

	Scenario: browsing all suggestions
		When  command "list" is entered
		Then  book is found