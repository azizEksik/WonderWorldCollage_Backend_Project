
Feature: As an administrator, I want to access the Vehicle List through API connection.

  Scenario: TC_01

    * Prepare url with path parameters "api/vehicleList".
    * Prepare "admin" "batuhan" "CorrectPassword" token
    * Get request.
    * Find the id of the content created by "Batuhan"
    * Prepare url with path parameters "api/vehicleDelete".
    * "DELETE" request for Vehicle - id "0" -
    * Verify that status code 200 and message is "Success".

  Scenario: TC_02 When invalid authorization information or incorrect data (id) are sent in the DELETE body to the api/vehicleDelete endpoint, the expected status code is 403, and the message in the response body should be "failed."


    * Prepare "admin" "batuhan" "CorrectPassword" token
    * Prepare url with path parameters "api/vehicleDelete".
    * "DELETE" request for Vehicle - id "3462346" -
    * Verify that status code 403 and message is "failed" for "DELETE"

  Scenario: TC_03 The DeletedId in the response body should be verified to be the same as the id sent in the DELETE request body to the api/vehicleDelete endpoint.

    * Prepare url with path parameters "api/vehicleList".
    * Prepare url with path parameters "api/vehicleList".
    * Get request.
    * Find the id of the content created by "Batuhan"
    * Prepare url with path parameters "api/vehicleDelete".
    * "DELETE" request for Vehicle - id "0" -
  * Response body should be validated for "DELETE" request


  Scenario: TC_04 The successful deletion of the desired vehicle record via the API should be validated. (This can be confirmed by using the DeletedId returned in the response body to send a POST body to the api/vehicleId endpoint and verify that the record is no longer present.)

    * Prepare url with path parameters "api/vehicleList".
    * Prepare "admin" "batuhan" "CorrectPassword" token
    * Get request.
    * Find the id of the content created by "Batuhan"
    * Prepare url with path parameters "api/vehicleDelete".
    * "DELETE" request for Vehicle - id "0" -
    * Prepare url with path parameters "api/vehicleId".
    * Prepare "admin" "batuhan" "CorrectPassword" token
    *  "POSTDELETE" request for Vehicle - id "0" -
    * Verify that status code 403 and message is "failed" for "DELETE"