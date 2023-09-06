Feature: [DB_US18] List the number of students in the onlineexam_students table based on unique student_session_id values.

  @berke
  Scenario: [TC_01] List the number of students in the onlineexam_students table based on unique student_session_id values.

    * Database connection is established
    * Query is prepared, executed and results are collected "db_us18_query"
    * Results for US18 are verified
    * Database connection is closed