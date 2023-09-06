@db_demo
Feature: [DB_US09] A new record should be added to the transport_route table.

  Scenario: [TC_01 -> DB_US09]
    * Database connection is established
    * Prepare query and execute INSERT for US_09
    * Verify results for "US_09"
    * Database connection is closed