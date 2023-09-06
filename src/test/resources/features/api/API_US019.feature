Feature: As an administrator, I want to access the Book Issue information with the given ID through the API connection


  Scenario: TC_01 >> When a valid authorization credentials and correct data (id) are sent in the POST body to the 'api/bookIssueId' endpoint, it should be verified that the returned status code is 200 and the message information in the response body is 'Success'

    * Prepare url with path parameters "api/bookIssueId".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * A POST request is sent with the correct data and Authorization
    * The returned response's status code is verified to be 200, and the message information in the body is confirmed to be "Success"


  Scenario: TC_02 >> When invalid authorization credentials or incorrect data (id) are sent in the POST body to the 'api/bookIssueId' endpoint, it should be verified that the returned status code is 403 and the message information in the response body is 'failed'

    * Prepare url with path parameters "api/bookIssueId".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * An invalid POST request is sent to the api-bookIssueId endpoint with incorrect data for the id
    * It is verified that the response with an invalid id returned from the api-bookIssueId endpoint has a status code of 403, and the message information in the body is 'failed'
    * An invalid POST request with incorrect Authorization is sent to the api-bookIssueId endpoint
    * It is verified that the response with invalid Authorization returned from the api-bookIssueId endpoint has a status code of 403, and the message information in the body is 'failed'


  Scenario: TC_03 >> The contents of the list data (id, book_id, member_id, due_return_date, return_date, issue_date, is_returned, is_active, created_at) within the response body should be verified

    * Prepare url with path parameters "api/bookIssueId".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * It should be verified that the POST request response returned from the api-bookIssueId address contains the values given in the expected data

