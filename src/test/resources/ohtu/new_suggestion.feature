Feature: User can add a new suggestion

Scenario: User cannot add a suggestion for an invalid type of suggestable
    Given command "add" is selected
    When command "bool" is entered
    Then message "Unknown command!" is displayed

# BOOKS

#title
#creator
#isbn

#desc

  Scenario: User can add a suggestion with a new book with full information
    Given command "add" is selected
    Given command "book" is selected
    When title "Book name" and creator "Matti" and ISBN "78-100-98548-9-2" and description "Book description" are entered
    Then message "New suggestion with book added!" is displayed
 
  Scenario: User can add suggestion with new book without description
	Given command "add" is selected
    	Given command "book" is selected
	When title "Book name" and creator "Matti" and ISBN "78-100-98548-9-2" and description "" are entered
	Then message "New suggestion with book added!" is displayed

 #Scenario: User cannot add suggestion with new book without title
#Given command "add" is selected
  #  	Given command "book" is selected
	#When title "" and creator "Matti" and ISBN "78-100-98548-9-2" and description "" are entered
	#Then message "Title is required!" is displayed

# BLOGS

  Scenario: User can add a suggestion with a new blog with full information
    Given command "add" is selected
    Given command "blog" is selected
	When url "www.blowyourmind.io" and title "5 clickbait titles" and creator "Michael" and blogname "FuzzBeed" and description "You won't believe it!" are entered
    Then message "New suggestion with blog added!" is displayed

  Scenario: User can add a suggestion with a new blog without blogname and description
    Given command "add" is selected
    Given command "blog" is selected
	When url "www.blowyourmind.io" and title "5 clickbait titles" and creator "Michael" and blogname "" and description "" are entered
    Then message "New suggestion with blog added!" is displayed

  Scenario: User cannot add blog without url
    Given command "add" is selected
    Given command "blog" is selected
    When url "" and title "5 clickbait titles" and creator "Michael" and blogname "" and description "" are entered
    Then message "Blog must have url!" is displayed

  Scenario: User cannot add blog without title
    Given command "add" is selected
    Given command "blog" is selected
	When url "www.blowyourmind.io" and title "" and creator "Michael" and blogname "" and description "" are entered
    Then message "Blog must have title and writer!" is displayed

  Scenario: User cannot add blog without writer
    Given command "add" is selected
    Given command "blog" is selected
	When url "www.blowyourmind.io" and title "5 clickbait titles" and creator "" and blogname "" and description "" are entered
    Then message "Blog must have title and writer!" is displayed

  Scenario: User won't add blog with existing URL
    Given command "add" is selected
    Given command "blog" is selected
	When url "https://www.agilealliance.org/how-to-increase-velocity/" and title "How to Increase Velocity" and creator "David Bernstein" and blogname "" and description "" are entered
	Then message "Found the following blog:" is displayed
    And message "New suggestion with blog added!" is displayed

# VIDEOS

# When url "https://github.com/micaminoff/ohtu_miniprojekti/commits/master" and title "Agile Livestream" and creator "ohtu-kurkku" and description "So smooth" are entered

  Scenario: User can add a suggestion with a new video with full information
    Given command "add" is selected
    Given command "video" is selected
	When url "https://github.com/micaminoff/ohtu_miniprojekti/commits/master" and title "Agile Livestream" and creator "ohtu-kurkku" and description "So smooth" are entered
    Then message "New suggestion with video added!" is displayed

  Scenario: User can add a suggestion with a new video without creator and description
    Given command "add" is selected
    Given command "video" is selected
	When url "https://github.com/micaminoff/ohtu_miniprojekti/commits/master" and title "Agile Livestream" and creator "" and description "" are entered
    Then message "New suggestion with video added!" is displayed

  Scenario: User cannot add video without url
    Given command "add" is selected
    Given command "video" is selected
	When url "" and title "Agile Livestream" and creator "" and description "" are entered
    Then message "Video must have url!" is displayed
	
  Scenario: User cannot add video without title
    Given command "add" is selected
    Given command "video" is selected
	When url "https://github.com/micaminoff/ohtu_miniprojekti/commits/master" and title "" and creator "" and description "" are entered
    Then message "Video must have title!" is displayed

  Scenario: User won't add video with existing URL
    Given command "add" is selected
    Given command "video" is selected
	When url "https://www.youtube.com/watch?v=PvLaPKPzq2I" and title "" and creator "" and description "" are entered
	Then message "Found the following video:" is displayed
	And message "New suggestion with video added!" is displayed

# PODCASTS

  Scenario: User can add a suggestion with a new podcast with full information
    Given command "add" is selected
    Given command "podcast" is selected
    When url "http://podbay.fm/show/877968260/e/1511431200" and title "The FAST Way to Grow Instagram & Facebook (20k/day)" and podcast name "The Tai Lopez Show" and creator "Tai Lopez" and description "The more followers you have, the easier it is to get more followers"
    Then message "New suggestion with podcast added!" is displayed

  Scenario: User can add a suggestion with a new podcast without creator and description
    Given command "add" is selected
    Given command "podcast" is selected
	When url "http://podbay.fm/show/877968260/e/1511431200" and title "The FAST Way to Grow Instagram & Facebook (20k/day)" and podcast name "The Tai Lopez Show" and creator "" and description ""
    Then message "New suggestion with podcast added!" is displayed
	
  Scenario: User cannot add podcast without url
    Given command "add" is selected
    Given command "podcast" is selected
	When url "" and title "The FAST Way to Grow Instagram & Facebook (20k/day)" and podcast name "The Tai Lopez Show" and creator "" and description ""
    Then message "Podcast must have url!" is displayed
	
  Scenario: User cannot add podcast without title
    Given command "add" is selected
    Given command "podcast" is selected
	When url "http://podbay.fm/show/877968260/e/1511431200" and title "" and podcast name "The Tai Lopez Show" and creator "" and description ""
    Then message "Podcast must have title and podcast name!" is displayed
	
  Scenario: User cannot add podcast without podcast name
    Given command "add" is selected
    Given command "podcast" is selected
	When url "http://podbay.fm/show/877968260/e/1511431200" and title "The FAST Way to Grow Instagram & Facebook (20k/day)" and podcast name "" and creator "" and description ""
    Then message "Podcast must have title and podcast name!" is displayed

  Scenario: User won't add podcast with existing URL
    Given command "add" is selected
    Given command "podcast" is selected
    When url "http://podcasts.joerogan.net/podcasts/mma-show-2" and title "" and podcast name "" and creator "" and description ""
	And message "Found the following podcast:" is displayed
	Then message "New suggestion with podcast added!" is displayed
	
	
#Kannaattaa katsoa nää uudestaan

# EI VIELÄ IMPLEMENTOITU Appissa eikä testinä.
#  Scenario: Adding a suggestion for a nonexistant book
#    Given title "Book1" is given
#    When book with title "Book1" isn't found
#    Then user is asked for the rest of the book's attributes
#    And book with title "Book1" exists
#    And user is asked for the rest of the suggestion's attributes
#    And suggestion exists, and it's related to a book with title "Book1"
#
# EI VIELÄ IMPLEMENTOITU Appissa eikä testinä.
#  Scenario: Adding a suggestion for an existing book
#    Given title "Book1" is given
#    When book with title "Book1" is found
#    Then user is asked for the rest of the suggestion's attributes
#    And suggestion exists, and it's related to a book with title "Book1"
