Feature: Login Error Validation

  Background:
    Given  user in the ecommerce login page

  @ErrorValidation
  Scenario Outline: Validate Login Error Validation
    Given  user in the ecommerce login page
    When   user login using email<email> and password<password>
    Then   "Incorrect email or password." error is displayed


    Examples:
      | email                             | password    | testcasename               |
      | invalidemail8912@mailinator.com   | Test1234@!  | invalid email              |
      | unknownuuid8123712@mailinator.com | Test12345@! | invalid password           |
      | invalidemail8912@mailinator.com   | Test12345@! | invalid email and password |