Feature: User can create suggestions for books

  Scenario: User can add a suggestion with a new book with full information
    Given command "add" is selected
    Given command "book" is selected
    When ISBN "78-100-98548-9-2" and title "Book name" and author "Matti" and description "Book description" are entered
    Then message "New suggestion with book added!" is displayed

  Scenario: User can add suggestion with new book without description
    Given command "add" is selected
    Given command "book" is selected
    When ISBN "78-100-98548-9-2" and title "Book name" and author "Matti" and description "" are entered
    Then message "New suggestion with book added!" is displayed

  Scenario: User cannot add suggestion with new book without title
    Given command "add" is selected
    Given command "book" is selected
    When ISBN "78-100-98548-9-2" is entered
    And title "" is entered
    And title "Book name" is entered
    And author "Author" is entered
    And description "" is entered
    Then message "Title must be" is displayed

  Scenario: User cannot add suggestion with new book without author
    Given command "add" is selected
    Given command "book" is selected
    When ISBN "78-100-98548-9-2" is entered
    And title "Book Name" is entered
    And author "" is entered
    And author "Author" is entered
    And description "" is entered
    Then message "Author's name must be" is displayed

  Scenario: User cannot add suggestion with new book without ISBN
    Given command "add" is selected
    Given command "book" is selected
    When ISBN "" is entered
    And ISBN "78-100-98548-9-2" and title "Book name" and author "Matti" and description "" are entered
    Then message "ISBN is required" is displayed

  Scenario: User cannot add suggestion with malformed ISBN
    Given command "add" is selected
    Given command "book" is selected
    When ISBN "08923478934-ADHASDJK-" is entered
    And ISBN "78-100-98548-9-2" and title "Book name" and author "Matti" and description "" are entered
    Then message "ISBN must consist of only numbers" is displayed

  Scenario: User cannot add book with too long ISBN
    Given command "add" is selected
    Given command "book" is selected
    When ISBN "64876592378465978234659873264598736459783264598732645983764972364723498621348773264598723645987236459872364598736245-7362489716234652134651283476512" is entered
    And ISBN "78-100-98548-9-2" and title "Book name" and author "Matti" and description "" are entered
    Then message "ISBN must consist of only numbers" is displayed

  Scenario: User cannot add book with too long title
    Given command "add" is selected
    Given command "book" is selected
    When ISBN "78-100-98548-9-2" is entered
    And title "sdhfkasjdhfpiasuhfpiuhasrepfiuhaspeifrhusapiefuhaspiudhfpisauhdfpiuashfpiushaepfiuhaspieurhfpiashdfipuahsdpfihaspdifuhpisudhfpiuahrfp" is entered
    And title "Book name" is entered
    And author "Author" is entered
    And description "" is entered
    Then message "Title must be" is displayed

  Scenario: User cannot add book with too long author name
    Given command "add" is selected
    Given command "book" is selected
    When ISBN "78-100-98548-9-2" is entered
    And title "Book Name" is entered
    And author "uhasefiuhasdifuhaspiefuhpasiuefhpsaieuhfpiawsuhefpiaseuhfpiuashdfpiuhsadpifuhpasuehfipuashefpiuhaspeifuhaspieufh" is entered
    And author "Author" is entered
    And description "" is entered
    Then message "Author's name must be" is displayed

  Scenario: User cannot add book with too long description
    Given command "add" is selected
    Given command "book" is selected
    When ISBN "78-10" is entered
    And title "Book Name" is entered
    And author "Author" is entered
    And wrong description "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam convallis sagittis ante, vel consectetur nisl porttitor eget. Praesent aliquam quam nisi, id volutpat massa laoreet nec. Nam eu ultrices massa. Phasellus facilisis nisl in magna dapibus, at congue arcu condimentum. Ut eu turpis vel nullam." is entered
    And description "" is entered
    Then message "Description too long" is displayed
