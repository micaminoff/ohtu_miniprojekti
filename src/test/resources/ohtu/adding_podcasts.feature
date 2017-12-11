Feature: User can add suggestions for podcasts

  Scenario: User can add a suggestion with a new podcast with full information
    Given command "add" is selected
    Given command "podcast" is selected
    When url "http://podbay.fm/show/877968260/e/1511431200" and title "The FAST Way to Grow Instagram & Facebook (20k/day)" and podcast name "The Tai Lopez Show" and creator "Tai Lopez" and description "The more followers you have, the easier it is to get more followers" are entered
    Then message "New suggestion with podcast added!" is displayed

  Scenario: User can add a suggestion with a new podcast without creator and description
    Given command "add" is selected
    Given command "podcast" is selected
    When url "http://podbay.fm/show/877968260/e/1511431200" and title "The FAST Way to Grow Instagram & Facebook (20k/day)" and podcast name "The Tai Lopez Show" and creator "" and description "" are entered
    Then message "New suggestion with podcast added!" is displayed

  Scenario: User cannot add podcast without url
    Given command "add" is selected
    Given command "podcast" is selected
    When url "" is entered
    And url "http://podbay.fm/show/877968260/e/1511431200" and title "The FAST Way to Grow Instagram & Facebook (20k/day)" and podcast name "The Tai Lopez Show" and creator "" and description "" are entered
    Then message "URL is required!" is displayed

  Scenario: User cannot add podcast without title
    Given command "add" is selected
    Given command "podcast" is selected
    When url "http://podbay.fm/show/877968260/e/1511431200" is entered
    And title "" is entered
    And title "The FAST Way to Grow Instagram & Facebook (20k/day)" and podcast name "The Tai Lopez Show" and creator "" and description "" are entered
    Then message "Title must be" is displayed

  Scenario: User cannot add podcast without podcast name
    Given command "add" is selected
    Given command "podcast" is selected
    When url "http://podbay.fm/show/877968260/e/1511431200" is entered
    And title "The FAST Way to Grow Instagram & Facebook (20k/day)" is entered
    And name "" is entered
    And name "The Tai Lopez Show" and creator "" and description "" are entered
    Then message "Name must be" is displayed

  Scenario: User won't add podcast with existing URL
    Given command "add" is selected
    Given command "podcast" is selected
    When url "http://podcasts.joerogan.net/podcasts/mma-show-2" and title "" and podcast name "" and creator "" and description "" are entered
    And message "Found the following podcast:" is displayed
    Then message "New suggestion with podcast added!" is displayed

  Scenario: User cannot add podcast with malformed url
    Given command "add" is selected
    Given command "podcast" is selected
    When url "123.456" is entered
    And url "http://podbay.fm/show/877968260/e/1511431200" and title "The FAST Way to Grow Instagram & Facebook (20k/day)" and podcast name "The Tai Lopez Show" and creator "" and description "" are entered
    Then message "Malformed or empty URL" is displayed

  Scenario: User cannot add podcast with too long url
    Given command "add" is selected
    Given command "podcast" is selected
    When url "http://www.tosipitkaverkkosivunalidomainin.samanverkkosivundomaini.jonkunpitkannimisenmaanTLDvaikkapistecom" is entered
    And url "http://podbay.fm/show/877968260/e/1511431200" and title "The FAST Way to Grow Instagram & Facebook (20k/day)" and podcast name "The Tai Lopez Show" and creator "" and description "" are entered
    Then message "Malformed or empty URL" is displayed

  Scenario: User cannot add podcast with too long title
    Given command "add" is selected
    Given command "podcast" is selected
    When url "http://podbay.fm/show/877968260/e/1511431200" is entered
    And title "dfuhasdföoihjasdöofhjaspöoeirhfjpaosehfpasiouhefijsdfijnbsdijfnbpsiojehfpiasuhefpiuhasefpiuhsdfiujnsidjvnbpisudhfpiasuhf" is entered
    And title "The FAST Way to Grow Instagram & Facebook (20k/day)" and podcast name "The Tai Lopez Show" and creator "" and description "" are entered
    Then message "Title must be" is displayed

  Scenario: User cannot add podcast with too long podcast name
    Given command "add" is selected
    Given command "podcast" is selected
    When url "http://podbay.fm/show/877968260/e/1511431200" is entered
    And title "The FAST Way to Grow Instagram & Facebook (20k/day)" is entered
    And name "dfuhasdföoihjasdöofhjaspöoeirhfjpaosehfpasiouhefijsdfijnbsdijfnbpsiojehfpiasuhefpiuhasefpiuhsdfiujnsidjvnbpisudhfpiasuhf" is entered
    And name "The Tai Lopez Show" and creator "" and description "" are entered
    Then message "Name must be" is displayed

  Scenario: User cannot add podcast with too long creator name
    Given command "add" is selected
    Given command "podcast" is selected
    When url "http://podbay.fm/show/877968260/e/1511431200" is entered
    And title "The FAST Way to Grow Instagram & Facebook (20k/day)" is entered
    And name "The Tai Lopez Show" is entered
    And creator "dfuhasdföoihjasdöofhjaspöoeirhfjpaosehfpasiouhefijsdfijnbsdijfnbpsiojehfpiasuhefpiuhasefpiuhsdfiujnsidjvnbpisudhfpiasuhf" is entered
    And creator "" is entered
    And description "" is entered
    Then message "Name too long" is displayed

  Scenario: User cannot add podcast with too long description
    Given command "add" is selected
    Given command "podcast" is selected
    When url "http://podbay.fm/show/877968260/e/1511431200" is entered
    And title "The FAST Way to Grow Instagram & Facebook (20k/day)" is entered
    And name "The Tai Lopez Show" is entered
    And creator "" is entered
    And wrong description "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam convallis sagittis ante, vel consectetur nisl porttitor eget. Praesent aliquam quam nisi, id volutpat massa laoreet nec. Nam eu ultrices massa. Phasellus facilisis nisl in magna dapibus, at congue arcu condimentum. Ut eu turpis vel nullam." is entered
    And description "" is entered
    Then message "Description too long" is displayed