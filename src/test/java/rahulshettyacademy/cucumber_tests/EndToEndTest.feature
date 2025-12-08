Feature: Purchase Order

  Background:
    Given  user in the ecommerce login page

  @EndToEnd
  Scenario Outline: Validate Successful Purchase Order
    Given   user login using email<email> and password<password>
    When    a product<product> is added to cart
    Then    check if product<product> is added to  cart
    When    user fill up the checkout details with coupon
    Then    check if coupon is applied
    When    user place the order
    Then    "THANKYOU FOR THE ORDER." is displayed
    And     item name and order id is displayed

    Examples:
      | email                             | password   | product         |
      | unknownuuid8123712@mailinator.com | Test1234@! | IPHONE 13 PRO   |
      | unknownuuid8123713@mailinator.com | Test1234@! | ADIDAS ORIGINAL |
      | unknownuuid8123712@mailinator.com | Test1234@! | ZARA COAT 3     |