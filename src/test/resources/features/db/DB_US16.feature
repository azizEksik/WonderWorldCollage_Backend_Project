Feature: [DB_US16] List the last 10 records from the online_admissions table.

  @berke
  Scenario: [TC_01] List the last 10 records from the online_admissions table.

    * Database connection is established
    * Query is prepared, executed and results are collected "db_us16_query"
    * Results for US16 are verified
    * Database connection is closed
