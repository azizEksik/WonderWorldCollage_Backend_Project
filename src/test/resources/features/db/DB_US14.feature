@furkan
Feature: DB_US14 List the book titles of books in the books table where the author data is 'Rubina Malik' or 'Mien Ali'.

  Scenario: TC_01

    * Database connection is established
    * Query is prepared, executed and results are collected "db_us14_query"
    * Result of US14 query
    * Database connection is closed
