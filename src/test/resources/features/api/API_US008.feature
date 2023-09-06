Feature: As an administrator, I want to access the alumni events information between the specified start date and end date through API connection.


  Scenario: TC_01 >> When a POST request with valid authorization credentials and correct data (start, end) included in the POST body is sent to the 'api/alumniEventsByDateRange' endpoint, it should be verified that the returned status code is 200 and the message information in the response body is confirmed to be 'Success'

    * Prepare url with path parameters "api/alumniEventsByDateRange".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * a POST request is sent to the api-alumniEventsByDateRange endpoint with valid authorization credentials, it is verified that the returned response has a status code of 200 and the message information in the body is confirmed to be 'Success'


  Scenario: TC_02 >> When a POST request with invalid authorization credentials or invalid data (start, end) included in the POST body is sent to the 'api/alumniEventsByDateRange' endpoint, it should be verified that the returned status code is 403 and the message information in the response body is confirmed to be 'failed'

    * Prepare url with path parameters "api/alumniEventsByDateRange".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * when a POST request is sent to the api-alumniEventsByDateRange endpoint with invalid authorization credentials, it is verified that the returned response has a status code of 403 and the message information in the body is confirmed to be 'failed'
    * When a POST request with invalid request body information is sent to the api-alumniEventsByDateRange endpoint, it is verified that the returned response has a status code of 403 and the message information in the body is confirmed to be 'failed'


  Scenario: TC_03 >> When a POST request with valid authorization credentials and correct data included in the POST body is sent to the 'api/alumniEventsByDateRange' endpoint, it should be verified that the data within the 'list' in the returned response body matches the data in the expected data

    * Prepare url with path parameters "api/alumniEventsByDateRange".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * when a POST request with valid authorization credentials and correct data included in the POST body is sent to the api-alumniEventsByDateRange endpoint, the data within the list in the returned response body should be verified to match the data in the expected data