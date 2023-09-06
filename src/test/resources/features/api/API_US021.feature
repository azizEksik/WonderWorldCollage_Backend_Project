Feature: As an administrator, I want to be able to delete a Book Issue record from the system through API connection.


  Scenario: TC_01 >> When a valid DELETE request with proper authorization credentials and correct data (id) is sent to the 'api/bookIssueDelete' endpoint, it should be confirmed that the returned status code is 200 and the 'message' information in the response body is 'Success'

    * Prepare url with path parameters "api/bookIssueDelete".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * A valid DELETE request is sent to the api-bookIssueDelete endpoint with proper authorization credentials and correct data included
    * The status code of the response from the api-bookIssueDelete endpoint is verified to be 200, and the message information in the body is confirmed to be 'Success'


  Scenario: TC_02 >> When an invalid DELETE request with incorrect authorization credentials or wrong data (id) is sent to the 'api/bookIssueDelete' endpoint, it should be confirmed that the returned status code is 403 and the 'message' information in the response body is 'failed'

    * Prepare url with path parameters "api/bookIssueDelete".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * An invalid DELETE request with incorrect data id is sent to the api-bookIssueDelete endpoint
    * It is verified that the status code of the response with an invalid id returned from the api-bookIssueDelete endpoint is 403, and the message information in the response body is 'failed'
    * An invalid DELETE request with incorrect Authorization is sent to the api-bookIssueDelete endpoint
    * It is confirmed that the status code of the response with invalid Authorization returned from the api-bookIssueDelete endpoint is 403, and the message information in the response body is 'failed'


  Scenario: TC_03 >> It should be verified that the 'DeletedId' information in the response body matches the 'id' information in the DELETE request body sent to the 'api/bookIssueDelete' endpoint.

    * Prepare url with path parameters "api/bookIssueDelete".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * A DELETE request is sent to the api-bookIssueDelete endpoint
    * It is confirmed that the DeletedId in the response from the api-bookIssueDelete endpoint matches the id information in the request body


  Scenario: TC_04 >> It should be verified that the book issue record intended to be deleted is successfully deleted via the API

    * Prepare url with path parameters "api/bookIssueDelete".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * A DELETE request is sent to the api-bookIssueDelete endpoint
    * The DeletedId information from the response body of the api-bookIssueDelete endpoint is used to prepare a request body, which is then sent as a POST request to the api-bookIssueId endpoint
    * The status code of the response from the api-bookIssueId endpoint is verified to be 403, and the message information in the body is confirmed to be 'failed'