Feature: User can browse tips

	Scenario: browsing all tips
		Given tips exist
		When  user selects option "Browse all tips"
		Then  user is given a list of all tips