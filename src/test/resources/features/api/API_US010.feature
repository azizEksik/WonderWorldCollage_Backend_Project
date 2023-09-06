
  @alumniEvents
  Feature: [API_US010] As an administrator, I want to create a new Alumni Events record through API connection.

    Scenario: [TC_01 -> API_US010] When valid authorization information and correct data are sent in the POST body to
              the api/alumniEventsAdd endpoint, the expected

            * Prepare url with path parameters "api/alumniEventsAdd".
            * Prepare "admin" "hilal" "CorrectPassword" token
            * The POST body with valid authorization information is sent to the api-alumniEventsAdd endpoint.
            * Verify that status code 200 and message is "Success" for POST Request

    Scenario:  [TC_02 -> API_US010] When invalid authorization information or wrong data are sent in the POST body to
               the api/alumniEventsAdd endpoint, the expected status code is 403, and the message in the response body
               should be "failed."

            * Prepare url with path parameters "api/alumniEventsAdd".
            * Prepare "admin" "hilal" "WrongPassword" token
            * The POST body with valid authorization information is sent to the api-alumniEventsAdd endpoint.
            * Verify that status code 403 and message is "failed" for POST Request

    Scenario: [TC_03 -> API_US010] The successful creation of a new Alumni Events record via the API should be validated.

            * Prepare url with path parameters "api/alumniEventsAdd".
            * Prepare "admin" "hilal" "CorrectPassword" token
            * The POST body with valid authorization information is sent to the api-alumniEventsAdd endpoint.
            * Response body content check test for alumniEventId.