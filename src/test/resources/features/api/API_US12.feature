
  @alumniEvents
  Feature: [API_US012] As an administrator, I want to be able to delete an Alumni Events record from the system through
           API connection.


    Scenario: [TC_01 -> API_US012] When valid authorization information and correct data (id) are sent in the DELETE
              body to the api/alumniEventsDelete endpoint, the expected status code is 200, and the message in the
              response body should be "Success."

        * Prepare url with path parameters "api/alumniEventsDelete".
        * Prepare "admin" "hilal" "CorrectPassword" token
        * DELETE Request for alumniEventsDelete
        * Verify that status code 200 and message is "Success" for DELETE Request


    Scenario: [TC_02 -> API_US012] When invalid authorization information or wrong data (id) are sent in the DELETE
              body to the api/alumniEventsDelete endpoint, the expected status code is 403, and the message in the
              response body should be "failed."

      * Prepare url with path parameters "api/alumniEventsDelete".
      * Prepare "admin" "hilal" "WrongPassword" token
      * DELETE Request for alumniEventsDelete
      * Verify that status code 403 and message is "failed" for DELETE Request


    Scenario: [TC_03 -> API_US012] The DeletedId information in the response body should be validated to be the same as
              the id information in the DELETE request body sent to the api/alumniEventsDelete endpoint.

      * Prepare url with path parameters "api/alumniEventsDelete".
      * Prepare "admin" "hilal" "CorrectPassword" token
      * DELETE Request for alumniEventsDelete
      * Verify response updateId matches DELETE request id in alumniEventsDelete


    Scenario: [TC_04 -> API_US012] The successful deletion of the alumni events record via the API should be validated.

      * Prepare url with path parameters "api/alumniEventsDelete".
      * Prepare "admin" "hilal" "CorrectPassword" token
      * DELETE Request for alumniEventsDelete
      * Response body content check test for alumniEventsDelete