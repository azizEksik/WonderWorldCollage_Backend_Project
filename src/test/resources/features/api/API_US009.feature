
  Feature: As an administrator, I want to access the Alumni Events information with a given ID through API connection.

    @API_US009_TC01
    Scenario: [TC_01 -> API_US009] When a valid authorization information and correct data (id) are sent in the POST
              body to the api/alumniEventsId endpoint, the expected status code is 200, and the message in the response
              body should be "Success."

              * Prepare url with path parameters "api/alumniEventsId".
              * Prepare "admin" "hilal" "CorrectPassword" token
              * The POST body with valid authorization information is sent to the api-alumniEventsId endpoint.
              * Verify that status code 200 and message is "Success" for POST Request

    @API_US009_TC02
    Scenario:  [TC_02 -> API_US009] When invalid authorization information or invalid data (id) are sent in the POST
               body to the api/alumniEventsId endpoint, the expected status code is 403, and the message in the response
               body should be "failed."

              * Prepare url with path parameters "api/alumniEventsId".
              * Prepare "admin" "hilal" "WrongPassword" token
              * The POST body with valid authorization information is sent to the api-alumniEventsId endpoint.
              * Verify that status code 403 and message is "failed" for POST Request

    @API_US009_TC03
    Scenario:  [TC_03 -> API_US009] The content of the list data (id, title, event_for, session_id, class_id, section,
               from_date, to_date, note, photo, is_active, event_notification_message, show_onwebsite, created_at) in
               the response body should be validated.

            * Prepare url with path parameters "api/alumniEventsId".
            * Prepare "admin" "hilal" "CorrectPassword" token
            * The POST body with valid authorization information is sent to the api-alumniEventsId endpoint.
            * The content of the list data in the response body should be validated for AlumniEventsId.