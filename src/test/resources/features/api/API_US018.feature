Feature: I want to access the Book Issue List through the API connection as an administrator.


  Scenario: TC_01 >> The status code returned is 200 and the response message should be verified as 'Success' when a GET request is sent to the 'api/bookIssueList' endpoint with valid authorization credentials.

    * Prepare url with path parameters "api/bookIssueList".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * A GET request is sent for the book issue list
    * It is verified that the returned response has a status code of 200 and the message information is "Success"


  Scenario: TC_02 >> When a GET request is sent to the 'api/bookIssueList' endpoint with invalid authorization credentials, it should be verified that the returned status code is 403 and the response message information is 'failed'

    * Prepare url with path parameters "api/bookIssueList".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * A GET request with invalid authorization is sent for the book issue list
    * It is verified that when invalid authorization credentials are used, the returned response has a status code of 403 and the message information is "failed"


  Scenario: TC_03 >> The content of the 'lists' within the response body should be verified to match the expected data

    * Prepare url with path parameters "api/bookIssueList".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * The data with an id of seven in the response's list body should be verified to match the expected data