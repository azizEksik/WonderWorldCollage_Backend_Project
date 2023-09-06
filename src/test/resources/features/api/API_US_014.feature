Feature: API_US014 As an administrator, I want to access the Vehicle information of a vehicle with a given ID through API connection.

  Scenario:TC_001 When valid authorization information and correct data (id) are sent in the POST body to the api/vehicleId endpoint,
  the expected status code is 200, and the message in the response body should be "Success."

    * Prepare url with path parameters "api/vehicleId".
    * Prepare "admin" "batuhan" "CorrectPassword" token
    * "POST" request for Vehicle - id "1" -
    * Verify that status code 200 and message is "Success" for "POST"

  Scenario: TC_02 When invalid authorization information or invalid data (id) are sent in the POST body to the api/vehicleId endpoint,
  the expected status code is 403, and the message in the response body should be "failed."

    * Prepare url with path parameters "api/vehicleId".
    * Prepare "admin" "batuhan" "WrongPassword" token
    *  "POST" request for Vehicle - id "3" -
    * Verify that status code 403 and message is "failed" for "POST"

  Scenario: TC_03 The content of the list in the response body should be validated.
  Specifically, the data in the response body should include the following attributes:
  id, vehicle_no, vehicle_model, vehicle_photo, manufacture_year, registration_number,
  chasis_number, max_seating_capacity, driver_name, driver_licence, driver_contact, note, created_at.

    * Prepare url with path parameters "api/vehicleId".
    * Prepare "admin" "batuhan" "CorrectPassword" token
    * "POST" request for Vehicle - id "3" -
    * Response body should be validated for "POST" request

