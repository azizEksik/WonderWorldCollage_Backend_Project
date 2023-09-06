@furkan
Feature: DB_US15 List the books from the books table where the quantity (qty) value is between 100 and 500.

  Scenario: TC_01

    * Database connection is established
    * Query is prepared, executed and results are collected "db_us15_query"
    * Result of US15 query
    * Database connection is closed