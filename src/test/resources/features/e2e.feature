@e2e @addBook
Feature: Add Book To The Store

  Scenario: Add Book To The Store
    Given Create a random user
    And Generate Authentication Token
    When Get list of books
    And Filter by publisher: "No Starch Press"
    And Post books to the User in context
    Then Test the API & Validate performed actions
    When Navigate to "https://demoqa.com"
    And Access the Bookstore application
    And Login with user in context
    Then Verify number of books added to user
    And Verify book details