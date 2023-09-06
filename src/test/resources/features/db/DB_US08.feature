@db_demo
Feature: [DB_US08] The name information for the specified id in the topic table should be updatable.

  Scenario: [TC_01 -> DB_US08]
    * Database connection is established
    * Prepare query and execute UPDATE for US_08
    * Verify results for "US_08"
    * Database connection is closed