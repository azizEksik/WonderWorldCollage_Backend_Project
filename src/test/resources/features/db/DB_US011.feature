Feature: Update the fine_amount value to '200.00' for the record in the transport_feemaster table where the month value is 'October'.

  @a
  Scenario: TC_01 >> The fine_amount value of the record in the transport_feemaster table, where the month value is 'October', should be updated to '200.00'

    * Database connection is established
    * The fine_amount value of the record in the transport_feemaster table with a month value of October is updated to 200.00
    * The update in the transport_feemaster table is verified
    * Database connection is closed