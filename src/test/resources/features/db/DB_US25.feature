Feature: [DB_US025] List the student IDs and entry dates (entrydt) from the staff_rating table where the comments column contains 'good'.

  @DB_25
  Scenario: [TC_01]
    * Database connection is established
    * Query is prepared, executed and results are collected "db_us25_query"
    * Result of db_us25 query
    * Database connection is closed