
@query05
Feature: [DB_US05] List the mother_name and mother_occupation values of the student in the students table whose lastname
         starts with 'T'.

  Scenario: [TC_01]

    Then Database connection is established.
    Then "DB_US05_query" The query is run and the results are retrieved.
    Then US05 Query results are validated
    Then Database connection is closed
