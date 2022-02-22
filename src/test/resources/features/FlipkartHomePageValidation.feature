@FlipkartHomePageValidation
  Feature: Test Scenarios for Home Page Validation

    Background:
      Given user navigates to "https://www.flipkart.com/"

    @FlipkartHomePage @Test-1
    Scenario: Validate top Panel on Home page
      Then validate title as "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!"
      And validate list of "menubar_titles" has value as "Top Offers,Grocery,Mobiles,Fashion,Electronics,Home,Appliances,Travel" on "FlipkartHomePage"

    @FlipkartHomePage @Test-2 #FailureTestcase
    Scenario Outline: Validate FlipKart Policy Details
      Then scroll in to view "footer" on "FlipkartHomePage"
      And validate list of "<Locator>" has value as "<product1>" on "FlipkartHomePage"
      And validate list of "<Locator>" has value as "<failproduct2>" on "FlipkartHomePage"
      And validate list of "<Locator>" has value as "<product3>" on "FlipkartHomePage"
      Examples:
      |Locator       |product1     |failproduct2          |product3|
      |PolicyProducts|Return Policy|Cancellation & Returns|Privacy|
      |AboutProducts |Contact Us   |Shipping              |About Us|
      |HelpProducts  |Payments     |Security              |Shipping|

    @FlipkartHomePage @Test-3 #FailureTestcase
    Scenario: Validate Filpkart Cart
      Then user click on "cart" on "FlipkartHomePage"