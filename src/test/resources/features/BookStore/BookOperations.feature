Feature: Sanity check for various book operations like add, get book by id, get all books and update book record

  @sanity  @regression @error
  Scenario Outline: Register user, Login and save the access token
    When Send POST request to signup endpoint with values "<email>" and "<password>"
    Then Verify response status code is "200"
    And Verify success response message for registered user
    When Send POST request to login endpoint using registered user credentials
    Then Verify response status code is "200"
    And Store access token

    Examples: 
      | email              | password |
      | test@spurqlabs.com |   123456 |

  @sanity @regression @error
  Scenario Outline: Verify user is able to add book, get book details, update the book details and delete the record.
    When Send POST request to add book with details <bookName>, <bookAuthor>, <publisherYear>, <bookSummary>
    Then Verify response status code is "200"
    And Store The Created Book Id
    When Send GET request to book endpoint with id parameter of book created
    Then Verify the added book details with details in response
    When Send PUT request to book endpoint with id parameter with update book data
    Then Verify the added book details with details in response
    When Send GET request to book endpoint to get list of all the books
    And Verify the added book details with details in get all response
    When Send DELETE request to books endpoint with book id
    Then Verify response status code is "200"
    And Verify the deleted book message in response
    When Send GET request to book endpoint with id parameter of book created
    Then Verify response status code is "404"
    And Verify response message for book not found

    Examples: 
      | bookName   | bookAuthor   | publisherYear | bookSummary   |
      | "TestName" | "TestAuthor" | "TestYear"    | "TestSummary" |
      
 @regression @error
  Scenario: Verify Update (PUT) books API behaviour with invalid id
    When Send PUT request to book endpoint with invalid id parameter with update book data
    Then Verify response status code is "404"
    And Verify response message for book not found

  @regression @error
  Scenario: Verify GET books with an invalid id
    When Send GET request to book endpoint with invalid id
    Then Verify response status code is "404"
    And Verify response message for book not found

  @regression @error
  Scenario: Verify GET books API behaviour with invalid acess token
    When Send GET request to book endpoint with invalid access token
    Then Verify response status code is "403"
    And Verify response message for invalid token

  @regression @error
  Scenario: Verify GET books API behaviour with null acess token
    When Send GET request to book endpoint with null access token
    Then Verify response status code is "403"
    And Verify response message for not authenticated
    
  @regression @error
  Scenario: Verify DELETE books API behaviour for invalid book id.
    When Send DELETE request to books endpoint with invalid book id
    Then Verify response status code is "404"
    And Verify response message for book not found
    
  @regression @error
  Scenario: Verify DELETE books API behaviour for all books.
    When Send DELETE request to books endpoint for all books
    Then Verify response status code is "405"
    And Verify response message for method not allowed


