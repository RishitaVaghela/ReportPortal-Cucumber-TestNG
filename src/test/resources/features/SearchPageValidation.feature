@SearchPageValidation
Feature: Test Scenarios for Search Page Validation

  Background:
    Given user navigates to "https://www.amazon.com/"

  @Search @Test-1
  Scenario: Validate search result displays with valid product name
    Then user enters data for "search" as "MDR-ZX110" on "HomePage"
    Then user click on "searchButton" on "HomePage"
    Then wait for "pageLoad" on "HomePage"
    And validate "searchResult" are displayed on "SearchPage"
    And validate "searchResult" contains product name as "MDR-ZX110" for atleast "5" products on "SearchPage"

  @Search @Test-2
  Scenario: Enter nothing/ click on the search button, it should show the same page without refreshing the page
    Then user click on "searchButton" on "HomePage"
    Then wait for "pageLoad" on "HomePage"
    And validate "SearchPage" "is not" loaded
    And validate list of "card_headers" has value as "Gaming accessories,Shop by Category,Computers & Accessories" on "HomePage"

  @Search @Test-3
  Scenario Outline: Enter search characters and it should display suggestion based on entered characters
    Then user enters data for "search" as "<data>" on "HomePage"
    And validate "suggestion_list" displayed has searched char as "<data>" on "HomePage"
    Examples:
    |data  |
    | h    |
    | ha   |
    | han  |
    | cycle|

  @Search @Test-4
  Scenario Outline: Select department and Enter search characters and it should display suggestion based on entered characters
    Then user selects "department" value as "<department>" on "HomePage"
    Then user enters data for "search" as "<data>" on "HomePage"
    And validate "suggestion_list" displayed has searched char as "<data>" on "HomePage"
    Examples:
      |data   |department    |
      | chetan| Books        |
      | chetan| Kindle Store |
      | dell  | Computers    |