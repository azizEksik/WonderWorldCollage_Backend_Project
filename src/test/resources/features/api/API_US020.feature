Feature: As an administrator, I want to create a new Book Issue record through API connection.


  Scenario: TC_01 >> When a valid authorization credentials and correct data (book_id, member_id, due_return_date, return_date, issue_date) are sent in the POST body to the 'api/bookIssueAdd' endpoint, it should be verified that the returned status code is 200 and the message information in the response body is 'Success'

    * Prepare url with path parameters "api/bookIssueAdd".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * A POST request with valid authorization credentials and valid data is sent to the api-bookIssueAdd endpoint
    * The response returned from the api-bookIssueAdd endpoint is verified to have a status code of 200, and the message information in the response body is 'Success'


  Scenario: TC_02 >> When invalid authorization credentials or incomplete data (missing book_id, member_id, due_return_date, return_date, issue_date) are sent in the POST body to the 'api/bookIssueAdd' endpoint, it should be verified that the returned status code is 403 and the message information in the response body is 'failed

    * Prepare url with path parameters "api/bookIssueAdd".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * An invalid POST request with incorrect authorization credentials and incomplete data is sent to the api-bookIssueAdd endpoint
    * It is verified that the response from the api-bookIssueAdd endpoint, when a request with missing data is sent, has a status code of 403, and the message information in the response body is 'failed'


  Scenario: TC_03 >>

    * Prepare url with path parameters "api/bookIssueAdd".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * The creation of a new book issue record through the api-bookIssueAdd endpoint on the API should be verified