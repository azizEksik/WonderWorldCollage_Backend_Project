
  @query04
  Feature: [DB_US04] List the firstname and lastname of students in the students table with admission numbers between
           18001 and 18010.

    Scenario:  [TC_01]

      Then Database connection is established.
      Then "DB_US04_query" The query is run and the results are retrieved.
      Then US04 Query results are validated
      Then Database connection is closed
