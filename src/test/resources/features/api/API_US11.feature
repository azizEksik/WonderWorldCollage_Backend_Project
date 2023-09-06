
  @alumniEvents
  Feature: [API_US011] As an administrator, I want to update the registered Alumni Events information in the system
           through API connection.

    Scenario: [TC_01 -> API_US011] When valid authorization information and correct data are sent in the PATCH body to
              the api/alumniEventsUpdate endpoint, the expected status code is 200, and the message in the response body
              should be "Success."

        * Prepare url with path parameters "api/alumniEventsUpdate".
        * Prepare "admin" "hilal" "CorrectPassword" token
        * PATCH Request for alumniEventsUpdate
        * Verify that status code 200 and message is "Success" for PATCH Request

    Scenario: [TC_02 -> API_US011] When valid authorization information and correct data are sent in the PATCH body to
              the api/alumniEventsUpdate endpoint, the expected status code is 200, and the message in the response body
              should be "Success."

        * Prepare url with path parameters "api/alumniEventsUpdate".
        * Prepare "admin" "hilal" "WrongPassword" token
        * PATCH Request for alumniEventsUpdate
        * Verify that status code 403 and message is "failed" for PATCH Request

    Scenario: [TC_03 -> API_US011] The updateId information in the response body should be validated to be the same as
              the id information in the PATCH request body sent to the api/alumniEventsUpdate endpoint.

        * Prepare url with path parameters "api/alumniEventsUpdate".
        * Prepare "admin" "hilal" "CorrectPassword" token
        * PATCH Request for alumniEventsUpdate
        * Verify response updateId matches PATCH request id in alumniEventsUpdate.

    Scenario: [TC_04 -> API_US011] The successful update of the Alumni Events record via the API should be validated.

        * Prepare url with path parameters "api/alumniEventsUpdate".
        * Prepare "admin" "hilal" "WrongPassword" token
        * PATCH Request for alumniEventsUpdate
        * Response body content check test for alumniEventsUpdate