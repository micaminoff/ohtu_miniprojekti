Feature: User can add suggestions for blogs

  Scenario: User can add a suggestion with a new blog with full information
    Given command "add" is selected
    Given command "blog" is selected
    When url "http://www.blowyourmind.io" and title "5 clickbait titles" and author "Michael" and name "FuzzBeed" and description "You won't believe it!" are entered
    Then message "New suggestion with blog added!" is displayed

  Scenario: User can add a suggestion with a new blog without name and description
    Given command "add" is selected
    Given command "blog" is selected
    When url "http://www.blowyourmind.io" and title "5 clickbait titles" and author "Michael" and name "" and description "" are entered
    Then message "New suggestion with blog added!" is displayed

  Scenario: User cannot add blog without url
    Given command "add" is selected
    Given command "blog" is selected
    When url "" is entered
    And url "http://www.blowyourmind.io" and title "5 clickbait titles" and author "Michael" and name "" and description "" are entered
    Then message "URL is required!" is displayed

  Scenario: User cannot add blog without title
    Given command "add" is selected
    Given command "blog" is selected
    When url "http://www.blowyourmind.io" is entered
    And title "" is entered
    And title "5 clickbait titles" and author "Michael" and name "" and description "" are entered
    Then message "Title must be" is displayed

  Scenario: User cannot add blog without author
    Given command "add" is selected
    Given command "blog" is selected
    When url "http://www.blowyourmind.io" is entered
    And title "5 clickbait titles" is entered
    And author "" is entered
    And url "http://www.blowyourmind.io" and title "5 clickbait titles" and author "Michael" and name "" and description "" are entered
    Then message "Author's name must be" is displayed

  Scenario: User cannot add blog with existing URL
    Given command "add" is selected
    Given command "blog" is selected
    When url "https://www.agilealliance.org/how-to-increase-velocity/" and title "How to Increase Velocity" and author "David Bernstein" and name "" and description "" are entered
    Then message "Found the following blog:" is displayed
    And message "Failed to add suggestion with blog!" is displayed

  Scenario: User cannot add blog with malformed URL
    Given command "add" is selected
    Given command "blog" is selected
    When url "abc.def" is entered
    And url "http://www.blowyourmind.io" and title "5 clickbait titles" and author "Michael" and name "" and description "" are entered
    Then message "Malformed or empty URL" is displayed

  Scenario: User cannot add blog with too long URL
    Given command "add" is selected
    Given command "blog" is selected
    When url "http://www.tosipitkaverkkosivunalidomainin.samanverkkosivundomaini.jonkunpitkannimisenmaanTLDvaikkapistecom" is entered
    And url "http://www.blowyourmind.io" and title "5 clickbait titles" and author "Michael" and name "" and description "" are entered
    Then message "Malformed or empty URL" is displayed

  Scenario: User cannot add blog with too long title
    Given command "add" is selected
    Given command "blog" is selected
    When url "http://www.blowyourmind.io" is entered
    And title "sidufhawliuhfpoasihfnpasiouhfpaushrfepiufdsgbpiuahfdspgiuhasdpfuibnaspidfguhbnpiasudhfgpiosauhfiusdhgfipsabdfp" is entered
    And title "5 clickbait titles" and author "Michael" and name "" and description "" are entered
    Then message "Title must be" is displayed

  Scenario: User cannot add blog with too long author name
    Given command "add" is selected
    Given command "blog" is selected
    When url "http://www.blowyourmind.io" is entered
    And title "5 clickbait titles" is entered
    And author "dfuhasdföoihjasdöofhjaspöoeirhfjpaosehfpasiouhefijsdfijnbsdijfnbpsiojehfpiasuhefpiuhasefpiuhsdfiujnsidjvnbpisudhfpiasuhf" is entered
    And author "Michael" and name "" and description "" are entered
    Then message "Author's name must be" is displayed

  Scenario: User cannot add blog with too long blog name
    Given command "add" is selected
    Given command "blog" is selected
    When url "http://www.blowyourmind.io" is entered
    And title "5 clickbait titles" is entered
    And author "Michael" is entered
    And name "dfuhasdföoihjasdöofhjaspöoeirhfjpaosehfpasiouhefijsdfijnbsdijfnbpsiojehfpiasuhefpiuhasefpiuhsdfiujnsidjvnbpisudhfpiasuhf" is entered
    And name "" and description "" are entered
    Then message "Name too long" is displayed

  Scenario: User cannot add blog with too long description
    Given command "add" is selected
    Given command "blog" is selected
    When url "http://www.blowyourmind.io" is entered
    And title "5 clickbait titles" is entered
    And author "Michael" is entered
    And name "" is entered
    And wrong description "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam convallis sagittis ante, vel consectetur nisl porttitor eget. Praesent aliquam quam nisi, id volutpat massa laoreet nec. Nam eu ultrices massa. Phasellus facilisis nisl in magna dapibus, at congue arcu condimentum. Ut eu turpis vel nullam." is entered
    And description "" is entered
    Then message "Description too long" is displayed
