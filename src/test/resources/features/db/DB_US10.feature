Feature: A specific entry should be deletable from the visitors_book table.

  @a
  Scenario: TC_01 >> The requested data should be able to be deleted from the visitors_book table in the database

    * Database connection is established
    * A requested data is deleted from the visitors_book table
    * The deletion of a requested data from the visitors_book table is confirmed
    * Database connection is closed