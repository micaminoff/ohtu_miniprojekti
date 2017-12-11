Feature: User can add a new suggestion

  Scenario: User cannot add a suggestion for an invalid type of suggestable
    Given command "add" is selected
    When command "bool" is entered
    Then message "Unknown command!" is displayed

  # BOOKS

  Scenario: User can add a suggestion with a new book with full information
    Given command "add" is selected
    Given command "book" is selected
	When ISBN "78-100-98548-9-2" and title "Book name" and creator "Matti" and description "Book description" are entered
    Then message "New suggestion with book added!" is displayed

  Scenario: User can add suggestion with new book without description
    Given command "add" is selected
    Given command "book" is selected
	When ISBN "78-100-98548-9-2" and title "Book name" and creator "Matti" and description "" are entered
    Then message "New suggestion with book added!" is displayed

	#RIKKI
  Scenario: User cannot add suggestion with new book without title
    Given command "add" is selected
    Given command "book" is selected
	When ISBN "78-100-98548-9-2" is entered
	And title "" is entered
    Then message "Title is required\nTitle:" is displayed

	#RIKKI
  Scenario: User cannot add suggestion with new book without author
    Given command "add" is selected
    Given command "book" is selected
	When ISBN "78-100-98548-9-2" is entered
	And title "Book Name" is entered
	And creator "" is entered
	Then message "Author is required\nAuthor:" is displayed

	#RIKKI
  Scenario: User cannot add suggestion with new book without ISBN
    Given command "add" is selected
    Given command "book" is selected
	When ISBN "" is entered
	Then message "ISBN is required\nISBN:" is displayed

	#RIKKI
  Scenario: User cannot add suggestion with malformed ISBN
    Given command "add" is selected
    Given command "book" is selected
	When ISBN "08923478934-ADHASDJK-" is entered
	Then message "ISBN must consist of only numbers" is displayed

  # BLOGS

  Scenario: User can add a suggestion with a new blog with full information
    Given command "add" is selected
    Given command "blog" is selected
    When url "http://www.blowyourmind.io" and title "5 clickbait titles" and creator "Michael" and blogname "FuzzBeed" and description "You won't believe it!" are entered
    Then message "New suggestion with blog added!" is displayed

  Scenario: User can add a suggestion with a new blog without blogname and description
    Given command "add" is selected
    Given command "blog" is selected
    When url "http://www.blowyourmind.io" and title "5 clickbait titles" and creator "Michael" and blogname "" and description "" are entered
    Then message "New suggestion with blog added!" is displayed

  Scenario: User cannot add blog without url
    Given command "add" is selected
    Given command "blog" is selected
    When url "" is entered
    And url "http://www.blowyourmind.io" and title "5 clickbait titles" and creator "Michael" and blogname "" and description "" are entered
    Then message "URL is required!" is displayed

  Scenario: User cannot add blog without title
    Given command "add" is selected
    Given command "blog" is selected
    When url "http://www.blowyourmind.io" is entered
    And title "" is entered
    And url "http://www.blowyourmind.io" and title "5 clickbait titles" and creator "Michael" and blogname "" and description "" are entered
    Then message "Title is required!\nTitle:" is displayed

  Scenario: User cannot add blog without author
    Given command "add" is selected
    Given command "blog" is selected
    When url "http://www.blowyourmind.io" is entered
    And title "5 clickbait titles" is entered
    And creator "" is entered
    And url "http://www.blowyourmind.io" and title "5 clickbait titles" and creator "Michael" and blogname "" and description "" are entered
    Then message "Author is required!\nAuthor:" is displayed

  Scenario: User cannot add blog with existing URL
    Given command "add" is selected
    Given command "blog" is selected
    When url "https://www.agilealliance.org/how-to-increase-velocity/" and title "How to Increase Velocity" and creator "David Bernstein" and blogname "" and description "" are entered
    Then message "Found the following blog:" is displayed
    And message "Failed to add suggestion with blog!" is displayed

  Scenario: User cannot add blog with malformed URL
    Given command "add" is selected
    Given command "blog" is selected
    When url "abc.def" is entered
    And url "http://www.blowyourmind.io" and title "5 clickbait titles" and creator "Michael" and blogname "" and description "" are entered
    Then message "Malformed or empty URL" is displayed

  # VIDEOS

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
    When url "" is entered
    And url "https://github.com/micaminoff/ohtu_miniprojekti/commits/master" and title "Agile Livestream" and creator "" and description "" are entered
    Then message "URL is required!" is displayed

  Scenario: User cannot add video without title
    Given command "add" is selected
    Given command "video" is selected
    When url "https://github.com/micaminoff/ohtu_miniprojekti/commits/master" is entered
    And title "" is entered
    And url "https://github.com/micaminoff/ohtu_miniprojekti/commits/master" and title "Agile Livestream" and creator "" and description "" are entered
    Then message "Title is required!\nTitle:" is displayed

  Scenario: User cannot add video with existing URL
    Given command "add" is selected
    Given command "video" is selected
    When url "https://www.youtube.com/watch?v=PvLaPKPzq2I" and title "" and creator "" and description "" are entered
    Then message "Found the following video:" is displayed
    And message "Failed to add suggestion with video!" is displayed

  Scenario: User cannot add video with malformed URL
    Given command "add" is selected
    Given command "video" is selected
    When url "asd.fgh" is entered
    And url "https://www.youtube.com/watch?v=PvLaPKPzq2I" and title "" and creator "" and description "" are entered
    Then message "Malformed or empty URL" is displayed

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
    When url "" is entered
    And url "http://podbay.fm/show/877968260/e/1511431200" and title "The FAST Way to Grow Instagram & Facebook (20k/day)" and podcast name "The Tai Lopez Show" and creator "" and description ""
    Then message "URL is required!" is displayed

  Scenario: User cannot add podcast without title
    Given command "add" is selected
    Given command "podcast" is selected
    When url "http://podbay.fm/show/877968260/e/1511431200" is entered
    And title "" is entered
    And url "http://podbay.fm/show/877968260/e/1511431200" and title "The FAST Way to Grow Instagram & Facebook (20k/day)" and podcast name "The Tai Lopez Show" and creator "" and description ""
    Then message "Title is required!\nTitle:" is displayed

  Scenario: User cannot add podcast without podcast name
    Given command "add" is selected
    Given command "podcast" is selected
    When url "http://podbay.fm/show/877968260/e/1511431200" is entered
    And title "The FAST Way to Grow Instagram & Facebook (20k/day)" is entered
    And podcast name "" is entered
    And url "http://podbay.fm/show/877968260/e/1511431200" and title "The FAST Way to Grow Instagram & Facebook (20k/day)" and podcast name "The Tai Lopez Show" and creator "" and description ""
    Then message "Podcast name is required!\nPodcast name:" is displayed

  Scenario: User won't add podcast with existing URL
    Given command "add" is selected
    Given command "podcast" is selected
    When url "http://podcasts.joerogan.net/podcasts/mma-show-2" and title "" and podcast name "" and creator "" and description ""
    And message "Found the following podcast:" is displayed
    And message "Failed to add suggestion with podcast!" is displayed

  Scenario: User cannot add podcast with malformed url
    Given command "add" is selected
    Given command "podcast" is selected
    When url "123.456" is entered
    And url "http://podbay.fm/show/877968260/e/1511431200" and title "The FAST Way to Grow Instagram & Facebook (20k/day)" and podcast name "The Tai Lopez Show" and creator "" and description ""
    Then message "Malformed or empty URL" is displayed