Feature: List the first 5 employees in the staff table sorted by work experience from the oldest to the newest.


  Scenario: TC_01 >> Retrieve the first 5 employees from the 'staff' table in the database, sorted based on their 'work_exp' value in ascending order (from the oldest to the newest)

    * Database connection is established
    * The first five employees in the staff table are listed, sorted based on their work_exp value in ascending order from oldest to newest
    * Database connection is closed

