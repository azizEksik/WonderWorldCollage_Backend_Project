Feature: As an administrator, I want to create a new Vehicle record through API connection.



  @Batu00
  Scenario:TC_001 When valid authorization information and correct data (vehicle_no, vehicle_model, vehicle_photo, manufacture_year,
  registration_number, chasis_number, max_seating_capacity, driver_name, driver_licence, driver_contact, note)
  are sent in the POST body to the api/vehicleAdd endpoint, the expected status code is 200, and the message in the response body should be "Success."

    * Prepare url with path parameters "api/vehicleAdd".
    * Prepare "admin" "batuhan" "CorrectPassword" token
    * "POSTADD" request for Vehicle - id "0" -
    * Verify that status code 200 and message is "Success" for "POST"

  Scenario: TC_02 When invalid authorization information or missing data (vehicle_no, vehicle_model, vehicle_photo, manufacture_year,
  registration_number, chasis_number, max_seating_capacity, driver_name, driver_licence, driver_contact, note)
  are sent in the POST body to the api/vehicleAdd endpoint, the expected status code is 403, and the message in the response body should be "failed."

    * Prepare url with path parameters "api/vehicleAdd".
    * Prepare "admin" "batuhan" "WrongPassword" token
    * "POSTADD" request for Vehicle - id "0" -
    * Verify that status code 403 and message is "failed" for "POST"

  Scenario: TC_03 The successful creation of the new vehicle record via the API should be validated.
  (This can be confirmed by using the addId returned in the response body to send a POST body to the api/vehicleId
  endpoint and verify the record is created.)

    * Prepare url with path parameters "api/vehicleAdd".
    * Prepare "admin" "batuhan" "CorrectPassword" token
    * "POSTADD" request for Vehicle - id "0" -
    * Response body should be validated for "POSTADD" request
    * Prepare url with path parameters "/api/vehicleId".
    * Prepare "admin" "batuhan" "CorrectPassword" token
    * "POST2" request for Vehicle - id "0" -
    * Verify that status code 200 and message is "Success" for "POST"