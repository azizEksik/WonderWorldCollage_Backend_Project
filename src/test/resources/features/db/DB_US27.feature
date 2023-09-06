Feature: [US_27] List students from the online_admissions table who share the same last name.

  @DB_US27
  Scenario: [TC_01]
    * Database connection is established
    * Query is prepared, executed and results are collected "db_us27_query"
    * Result of db_us27 query
    * Database connection is closed
