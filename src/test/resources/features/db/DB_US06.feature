
  @query06
  Feature: [DB_US06] List the roll_no values of students in the students table whose father_occupation is either Doctor
           or Police, ordered from highest to lowest.


    Scenario:  [TC_01]

      Then Database connection is established.
      Then "DB_US06_query" The query is run and the results are retrieved.
      Then US06 Query results are validated
      Then Database connection is closed