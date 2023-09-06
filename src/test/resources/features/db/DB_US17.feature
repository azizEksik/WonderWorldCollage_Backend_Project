Feature: [DB_US17] Calculate and list the average passing_percentage values from the onlineexam table.

  @berke
  Scenario: [TC_01] Calculate and list the average passing_percentage values from the onlineexam table.

    * Database connection is established
    * Query is prepared, executed and results are collected "db_us17_query"
    * Results for US17 are verified
    * Database connection is closed