Feature: User can add suggestions for videos

  Scenario: User can add a suggestion with a new video with full information
    Given command "add" is selected
    Given command "video" is selected
    When url "https://github.com/micaminoff/ohtu_miniprojekti/commits/master" and title "Agile Livestream" and author "ohtu-kurkku" and description "So smooth" are entered
    Then message "New suggestion with video added!" is displayed

  Scenario: User can add a suggestion with a new video without author and description
    Given command "add" is selected
    Given command "video" is selected
    When url "https://github.com/micaminoff/ohtu_miniprojekti/commits/master" and title "Agile Livestream" and author "" and description "" are entered
    Then message "New suggestion with video added!" is displayed

  Scenario: User cannot add video without url
    Given command "add" is selected
    Given command "video" is selected
    When url "" is entered
    And url "https://github.com/micaminoff/ohtu_miniprojekti/commits/master" and title "Agile Livestream" and author "" and description "" are entered
    Then message "URL is required!" is displayed

  Scenario: User cannot add video without title
    Given command "add" is selected
    Given command "video" is selected
    When url "https://github.com/micaminoff/ohtu_miniprojekti/commits/master" is entered
    And title "" is entered
    And url "https://github.com/micaminoff/ohtu_miniprojekti/commits/master" and title "Agile Livestream" and author "" and description "" are entered
    Then message "Title must be" is displayed

  Scenario: User cannot add video with existing URL
    Given command "add" is selected
    Given command "video" is selected
    When url "https://www.youtube.com/watch?v=PvLaPKPzq2I" and title "" and author "" and description "" are entered
    Then message "Found the following video:" is displayed
    And message "Failed to add suggestion with video!" is displayed

  Scenario: User cannot add video with malformed URL
    Given command "add" is selected
    Given command "video" is selected
    When url "asd.fgh" is entered
    And url "https://www.youtube.com/watch?v=PvLaPKPzq2I" and title "" and author "" and description "" are entered
    Then message "Malformed or empty URL" is displayed

  Scenario: User cannot add video with too long URL
    Given command "add" is selected
    Given command "video" is selected
    When url "http://www.tosipitkaverkkosivunalidomainin.samanverkkosivundomaini.jonkunpitkannimisenmaanTLDvaikkapistecom" is entered
    And url "https://www.youtube.com/watch?v=PvLaPKPzq2I" and title "" and author "" and description "" are entered
    Then message "Malformed or empty URL" is displayed

  Scenario: User cannot add video with too long title
    Given command "add" is selected
    Given command "video" is selected
    When url "https://github.com/micaminoff/ohtu_miniprojekti/commits/master" is entered
    And title "sidufhawliuhfpoasihfnpasiouhfpaushrfepiufdsgbpiuahfdspgiuhasdpfuibnaspidfguhbnpiasudhfgpiosauhfiusdhgfipsabdfp" is entered
    And title "Agile Livestream" and author "" and description "" are entered
    Then message "Title must be" is displayed

  Scenario: User cannot add video with too long author name
    Given command "add" is selected
    Given command "video" is selected
    When url "http://abc.def" is entered
    And title "Hyvä video" is entered
    And author "dfuhasdföoihjasdöofhjaspöoeirhfjpaosehfpasiouhefijsdfijnbsdijfnbpsiojehfpiasuhefpiuhasefpiuhsdfiujnsidjvnbpisudhfpiasuhf" is entered
    And author "Michael" and name "" and description "" are entered
    Then message "Name too long" is displayed

  Scenario: User cannot add video with too long description
    Given command "add" is selected
    Given command "video" is selected
    When url "http://abc.def" is entered
    And title "Hyvä video" is entered
    And author "Matti Meikä" is entered
    And wrong description "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam convallis sagittis ante, vel consectetur nisl porttitor eget. Praesent aliquam quam nisi, id volutpat massa laoreet nec. Nam eu ultrices massa. Phasellus facilisis nisl in magna dapibus, at congue arcu condimentum. Ut eu turpis vel nullam." is entered
    And description "" is entered
    Then message "Description too long" is displayed
