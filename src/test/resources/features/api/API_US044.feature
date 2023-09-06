Feature: [API_US044] As an administrator (teacher), I want to access the Homework information of a homework
          with a given ID through API connection.

@API_44-1

  Scenario: [TC_01] When a valid authorization and correct data (id) are sent in a POST body to the "apiteacher/homeworkbyId" endpoint,
            the response status code should be 200, and the response body's message should be "Success."

    * Prepare url with path parameters "apiteacher/homeworkbyId".
    * Prepare "teacher" "nurettin" "CorrectPassword" token
    * Post request homeworkById
    * Verify that status code 200 and message is "Success" for POST Request


  @API_44-2
  Scenario: [TC_02] When invalid authorization or invalid data (id) is sent in a POST body to the "apiteacher/homeworkbyId" endpoint,
               the response status code should be 403, and the response body's message should be "failed."

    * Prepare url with path parameters "apiteacher/homeworkbyId".
    * Prepare "teacher" "nurettin" "WrongPassword" token
    * Post request homeworkById
    * Verify that status code 403 and message is "failed" for POST Request


  @API_44-3

  Scenario: [TC_03] The content of the "list" data in the response body should be verified to match the specified data.

    * Prepare url with path parameters "apiteacher/homeworkbyId".
    * Prepare "teacher" "nurettin" "CorrectPassword" token
    * Post request homeworkById
    * Verify the POST response data with expected content of the data
