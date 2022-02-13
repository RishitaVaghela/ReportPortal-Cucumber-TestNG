@HomePageValidation
  Feature: Test Scenarios for Home Page Validation

    Background:
      Given user navigates to "https://www.amazon.com/"

    @HomePage @Test-1
    Scenario: Validate top Panel on Home page

      Then validate title as "Amazon.com. Spend less. Smile more."
      And validate "deliver_location" has text "United Kingdom" on "HomePage"
      And validate list of "menubar_titles" has value as "Today's Deals,Customer Service,Registry,Gift Cards,Sell" on "HomePage"

    @HomePage @Test-2
    Scenario Outline: Change Delivery country and validate
      Then user click on "select_deliver_location" on "HomePage"
      Then wait for "choose_your_Loction_popup" on "HomePage"
      Then user click on "countryOption" on "HomePage"
      Then user selects "location" value as "<country>" on "HomePage"
      Then user click on "done" on "HomePage"
      Then wait for "pageLoad" on "HomePage"
      And validate "deliver_location" has text "<country>" on "HomePage"
      Examples:
        | country |
        | Canada  |
        | China   |
        | Belgium |

    @HomePage @Test-3 #FailureTestcase
    Scenario: Validate Amazon Payment Products
      Then scroll in to view "footer" on "HomePage"
      And validate list of "AmazonPaymentProducts" has value as "Amazon Business Card" on "HomePage"
      And validate list of "AmazonPaymentProducts" has value as "Amazon Pay" on "HomePage"
      And validate list of "AmazonPaymentProducts" has value as "Amazon Gift Card" on "HomePage"