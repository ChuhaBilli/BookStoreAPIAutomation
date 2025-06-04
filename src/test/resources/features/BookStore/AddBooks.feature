Feature: Verify Add Book Feature

#  @test
  Scenario Outline: Verify user is able to add book
    Given Set user details
    When Hit Request for Add Book Name <bookName>, Author <bookAuthor>, Published Year <publisherYear>, Book Summary <bookSummary> with new user
    Then Validate Response Status code "200"
    And Store The Created Book Id

    Examples:
      | bookName                | bookAuthor            | publisherYear   | bookSummary                                                                                                                                                                               |
      | "To Kill a Mockingbird" | "Harper Lee"          | "1960"          | "A coming-of-age story told through the eyes of Scout Finch, a young girl growing up in the segregated South, witnessing the injustices of the legal system and the nature of prejudice." |
      | "1984"                  | "George Orwell"       | "1949"          | "A dystopian novel depicting a totalitarian society where the government, led by the enigmatic Big Brother, controls every aspect of citizens lives."                                    |
      | "Pride and Prejudice"   | "Jane Austen"         | "1813"          | "A romantic novel exploring the societal constraints and personal choices of the Bennet family, particularly the relationships between Elizabeth Bennet and Mr. Darcy."                   |
      | "The Great Gatsby"      | "F. Scott Fitzgerald" | "1925"          | "A story of wealth, excess, and the American Dream, set in the Roaring Twenties, focusing on the mysterious Jay Gatsby."                                                                  |
      | "The Hobbit"            | "J.R.R. Tolkien"      | "1937"          | "A fantasy adventure about Bilbo Baggins, a hobbit who joins a group of dwarves and a wizard on a quest to reclaim their treasure from a dragon."                                         |