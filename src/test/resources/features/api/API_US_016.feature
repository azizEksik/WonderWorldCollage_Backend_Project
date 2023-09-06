Feature: As an administrator, I want to update the registered Vehicle information in the system through API connection.

  Scenario: TC_01


    * Prepare url with path parameters "api/vehicleList".
    * Prepare "admin" "batuhan" "CorrectPassword" token
    * Get request.
    * Find the id of the content created by "Batuhan"
    * Prepare url with path parameters "api/vehicleUpdate".
  * "PATCH" request for Vehicle - id "0" -
  * Verify that status code 200 and message is "Success".

  Scenario: TC_02

    * Prepare url with path parameters "api/vehicleList".
    * Prepare "admin" "batuhan" "CorrectPassword" token
    * Get request.
    * Find the id of the content created by "Batuhan"
    * Prepare "admin" "batuhan" "WrongPassword" token
    * Prepare url with path parameters "api/vehicleUpdate".
    * "PATCH" request for Vehicle - id "3" -
    * Verify that status code 403 and message is "failed" for "PATCH"

  Scenario: TC_03 The updateId in the response body should be verified to be the same as the id sent in the PATCH request body to the api/vehicleUpdate endpoint.

    * Prepare url with path parameters "api/vehicleList".
    * Prepare "admin" "batuhan" "CorrectPassword" token
    * Get request.
    * Find the id of the content created by "Batuhan"
    * Prepare url with path parameters "api/vehicleUpdate".
    * "PATCH" request for Vehicle - id "0" -
  * Response body should be validated for "PATCH" request


  Scenario: TC_04 The successful update of the desired vehicle record via the API should be validated. (This can be confirmed by using the updateId returned in the response body to send a POST body to the api/vehicleId endpoint and verify the record is updated.)

    * Prepare url with path parameters "api/vehicleList".
    * Prepare "admin" "batuhan" "CorrectPassword" token
    * Get request.
    * Find the id of the content created by "Batuhan"
    * Prepare url with path parameters "api/vehicleUpdate".
    * "PATCH" request for Vehicle - id "0" -
    * Prepare url with path parameters "api/vehicleId".
    * "POST" request for Vehicle - id "0" -
    * Response body should be validated for "PATCHPOST" request

