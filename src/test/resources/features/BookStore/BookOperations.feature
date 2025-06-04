Feature: Sanity check for various book operations like add, get book by id, get all books and update book record

####note: This is only sanity and there are many scenarios which can be creted with detailed test data for each endpoint.

  @test
  Scenario Outline: Register user, Login and save the access token
    When Send POST request to signup endpoint with values "<email>" and "<password>"
    Then Vefiry response status code is "200"
    And Verify success response message for registered user
    When Send POST request to login endpoint using registered user credentials
    Then Vefiry response status code is "200"
    And Store access token

    Examples: 
      | email              | password |
      | test@spurqlabs.com |   123456 |

  @test
  Scenario Outline: Verify user is able to add book, get book details and update the book details
    ##==Given Scenario above is successful in registering and logging in the user==
    When Send POST request to add book with details <bookName>, <bookAuthor>, <publisherYear>, <bookSummary>
    Then Vefiry response status code is "200"
    And Store The Created Book Id
    When Send GET request to book endpoint with id parameter of book created
    Then Verify the added book details with details in response
    When Send PUT request to book endpoint with id parameter with update book data
    Then Verify the added book details with details in response
    When Send GET request to book endpoint to get list of all the books
    And Verify the added book details with details in get all response

    Examples: 
      | bookName   | bookAuthor   | publisherYear | bookSummary   |
      | "TestName" | "TestAuthor" | "TestYear"    | "TestSummary" |
