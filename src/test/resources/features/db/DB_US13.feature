@furkan
Feature: DB_US13 List the email addresses of records in the online_admissions table where the firstname contains the word 'al'.

  Scenario: TC_01
    * Database connection is established
    * Query is prepared, executed and results are collected "db_us13_query"
    * Result of US13 query
    * Database connection is closed
