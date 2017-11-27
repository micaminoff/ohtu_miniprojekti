Feature: User can add a new suggestion

# BOOKS

  Scenario: User can add a suggestion with a new book
    Given command "add" is selected
    Given command "book" is selected
    When title "Book1" and author "Matti" and description "good book" and ISBN "123" are entered
    Then message "New suggestion with book added!" is displayed

  Scenario: User cannot add a suggestion for an invalid type of suggestable
    Given command "add" is selected
    When command "bool" is entered
    Then message "Unknown command!" is displayed

# BLOGS

  Scenario: User can add a suggestion with a new blog with full information
    Given command "add" is selected
    Given command "blog" is selected
    When title "5 clickbait titles" and creator "Michael" and url "www.blowyourmind.io" and blogname "FuzzBeed" and description "You won't believe it!" are entered
    Then message "New suggestion with blog added!" is displayed

  Scenario: User can add a suggestion with a new blog without blogname and description
    Given command "add" is selected
    Given command "blog" is selected
    When title "5 clickbait titles" and creator "Michael" and url "www.blowyourmind.io" and blogname "" and description "" are entered
    Then message "New suggestion with blog added!" is displayed

  Scenario: User cannot add blog without url
    Given command "add" is selected
    Given command "blog" is selected
    When title "5 clickbait titles" and creator "Michael" and url "" and blogname "" and description "" are entered
    Then message "Blog must have atleast title, writer and url!" is displayed

  Scenario: User cannot add blog without title
    Given command "add" is selected
    Given command "blog" is selected
    When title "" and creator "Michael" and url "www.blowyourmind.io" and blogname "" and description "" are entered
    Then message "Blog must have atleast title, writer and url!" is displayed

  Scenario: User cannot add blog without writer
    Given command "add" is selected
    Given command "blog" is selected
    When title "5 clickbait titles" and creator "" and url "www.blowyourmind.io" and blogname "" and description "" are entered
    Then message "Blog must have atleast title, writer and url!" is displayed

  Scenario: User cannot add blog with existing URL
    Given command "add" is selected
    Given command "blog" is selected
    When title "5 clickbait titles" and creator "Michael" and url "https://www.agilealliance.org/how-to-increase-velocity/" and blogname "" and description "" are entered
    Then message "Failed to add suggestion with blog!" is displayed

# VIDEOS

  Scenario: User can add a suggestion with a new video with full information
    Given command "add" is selected
    Given command "video" is selected
    When title "Agile Livestream" and creator "ohtu-kurkku" and url "https://github.com/micaminoff/ohtu_miniprojekti/commits/master" and description "So smooth" are entered
    Then message "New suggestion with video added!" is displayed

  Scenario: User can add a suggestion with a new video without creator and description
    Given command "add" is selected
    Given command "video" is selected
    When title "Agile Livestream" and creator "" and url "https://github.com/micaminoff/ohtu_miniprojekti/commits/master" and description "So smooth" are entered
    Then message "New suggestion with video added!" is displayed

  Scenario: User cannot add video without title
    Given command "add" is selected
    Given command "video" is selected
    When title "" and creator "" and url "https://github.com/micaminoff/ohtu_miniprojekti/commits/master" and description "" are entered
    Then message "Video must have atleast title and url!" is displayed

  Scenario: User cannot add video without url
    Given command "add" is selected
    Given command "video" is selected
    When title "Agile Livestream" and creator "" and url "" and description "" are entered
    Then message "Video must have atleast title and url!" is displayed

  Scenario: User cannot add video with existing URL
    Given command "add" is selected
    Given command "video" is selected
    When title "Agile Livestream" and creator "ohtu-kurkku" and url "https://www.youtube.com/watch?v=PvLaPKPzq2I" and description "So smooth" are entered
    Then message "Failed to add suggestion with video!" is displayed

# PODCASTS

  Scenario: User can add a suggestion with a new podcast with full information
    Given command "add" is selected
    Given command "podcast" is selected
    When podcastName "Good Listens" and episodeName "6701" and url "https://www.npr.org/podcasts/organizations/1" and creator "npr" and description "Good Times" are entered
    Then message "New suggestion with podcast added!" is displayed

  Scenario: User can add a suggestion with a new podcast without creator and description
    Given command "add" is selected
    Given command "podcast" is selected
    When podcastName "Good Listens" and episodeName "6701" and url "https://www.npr.org/podcasts/organizations/1" and creator "" and description "" are entered
    Then message "New suggestion with podcast added!" is displayed

  Scenario: User cannot add podcast without podcast name
    Given command "add" is selected
    Given command "podcast" is selected
    When podcastName "" and episodeName "6701" and url "https://www.npr.org/podcasts/organizations/1" and creator "npr" and description "Good Times" are entered
    Then message "Podcast must have atleast podcast name, episode name and url!" is displayed

  Scenario: User cannot add podcast without episode name
    Given command "add" is selected
    Given command "podcast" is selected
    When podcastName "Good Listens" and episodeName "" and url "https://www.npr.org/podcasts/organizations/1" and creator "npr" and description "Good Times" are entered
    Then message "Podcast must have atleast podcast name, episode name and url!" is displayed

  Scenario: User cannot add podcast without url
    Given command "add" is selected
    Given command "podcast" is selected
    When podcastName "Good Listens" and episodeName "6701" and url "" and creator "npr" and description "Good Times" are entered
    Then message "Podcast must have atleast podcast name, episode name and url!" is displayed

  Scenario: User cannot add podcast with existing URL
    Given command "add" is selected
    Given command "podcast" is selected
    When podcastName "Good Listens" and episodeName "6701" and url "http://podcasts.joerogan.net/podcasts/mma-show-2" and creator "npr" and description "Good Times" are entered
    Then message "Failed to add suggestion with podcast!" is displayed

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
