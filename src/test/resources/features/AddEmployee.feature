Feature: add employee scenario

  Background:
    When user enters admin username and password
    And user clicks on login button
    Then user is successfully logged in
    When user clicks on PIM option
    And user clicks on add employee option

  @addemp @test
  Scenario: Adding one employee in the hrms system
   # Given user is navigated to HRMS Application
  #  When user enters admin username and password
   # And user clicks on login button
    #Then user is successfully logged in
    #When user clicks on PIM option
    #And user clicks on add employee option
    When user enters firstName middleName and lastName
    And user clicks on save button
    Then employee added successfully
  #  Then user close the browser

  @cucumber
  Scenario: Adding one employee using feature file
  #  When user enters admin username and password
   # And user clicks on login button
    #Then user is successfully logged in
    #When user clicks on PIM option
    #And user clicks on add employee option
    When user enters "miraj" and "fali" and "moralejo"
    And user clicks on save button
    Then employee added successfully

  @ddt
  Scenario Outline: addning
    When user enters "<firstName>" and "<middleName>" and enters "<lastName>"
    And user clicks on save button
    Then employee added successfully
    Examples:
      | firstName | middleName | lastName |
      | Ayesha    | syntaxstu  | Saif     |
      | Ehab      | syntaxstu  | maryland |
      | Tahmin    | ss         | Virginia |

  @excel
  Scenario: Addning multiple employees from excel files
    When user adds multiple employees from excel using "Sheet1" and verify them

  @datatable
  Scenario: adding multiple employees from data table
    When user adds multiple employees from data table

      | firstName | middleName  | lastName |
      | Ayesha1   | syntaxstwu  | Saif     |
      | Ehab1     | syntaxstweu | maryland |
      | Tahmin1   | ss          | Virginia |


    @sqldatabase
  Scenario: Add Employee from FrontEnd and verify from DB
    When user enters "Joey" and "Phoebe" and "Monica"
    And user clicks on save button
    Then employee added successfully
    And fetch employee info from backend
    Then verify employee info is properly stored in db
