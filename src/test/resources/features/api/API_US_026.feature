@bookAndAlumni
Feature: API_US_026 As an administrator, I want to update the registered Alumni information in the system through API connection.

  Scenario: TC_01 Success Response

    * Prepare url with path parameters "api/alumniUpdate".
    * Prepare "admin" "furkan" "CorrectPassword" token
    * Patch request for Alumni Update
    * Verify that status code 200 and message is "Success" for PATCH Request
    

  Scenario: TC_02 Failed Response

    * Prepare url with path parameters "api/alumniUpdate".
    * Prepare "admin" "furkan" "WrongPassword" token
    * Patch request for Alumni Update
    * Verify that status code 403 and message is "failed" for PATCH Request
    
    
    

  Scenario: TC_03 The updateId in the response body should match the id sent in the PATCH request body
            to the api/alumniUpdate endpoint.

    * Prepare url with path parameters "api/alumniUpdate".
    * Prepare "admin" "furkan" "CorrectPassword" token
    * Patch request for Alumni Update
    * Verify that the updated id in the response body should be same updated id in the reqBody.
    
    

  Scenario: TC_04 The API should successfully update the desired Alumni record, and this can be verified by sending 
            the updateId from the response body to the api/alumniId endpoint in a POST body to retrieve the updated Alumni record.

    * Prepare url with path parameters "api/alumniUpdate".
    * Prepare "admin" "furkan" "CorrectPassword" token
    * Patch request for Alumni Update
    * Verify that status code 200 and message is "Success" for PATCH Request
