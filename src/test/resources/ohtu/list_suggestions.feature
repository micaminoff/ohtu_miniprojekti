Feature: User can browse suggestions

	Scenario: browsing all suggestions
		Given suggestions exist
		When  user selects option "Browse all suggestions"
		Then  user is given a list of all suggestions