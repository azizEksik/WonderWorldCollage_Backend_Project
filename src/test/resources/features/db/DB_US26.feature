@db_demo
Feature: [DB_US26] List the admission_no, firstname, lastname of students from the online_admissions table who registered in January 2023.

  Scenario: [TC_01]
    * Database connection is established
    * Query is prepared, executed and results are collected "db_us26_query"
    * Result of db_us26 query
    * Database connection is closed