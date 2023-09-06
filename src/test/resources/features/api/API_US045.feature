Feature: [US_045] As an administrator (teacher), I want to create a new Homework record through API connection.

  @API_45-1
  Scenario: [TC_01] When valid authorization and correct data are sent in a POST body to the "apiteacher/homeworkAdd" endpoint,
  the response status code should be 200, and the response body's message should be "Success."

    * Prepare url with path parameters "apiteacher/homeworkAdd".
    * Prepare "teacher" "nurettin" "CorrectPassword" token
    * Post request homeworkAdd
    * Verify that status code 200 and message is "Success" for POST Request

    @API_45-2
    Scenario: [TC_02] When invalid authorization or incorrect/missing data are sent in a POST body to the "apiteacher/homeworkAdd" endpoint,
    the response status code should be 403, and the response body's message should be "failed."

      * Prepare url with path parameters "apiteacher/homeworkAdd".
      * Prepare "teacher" "nurettin" "WrongPassword" token
      * Post request homeworkAdd
      * Verify that status code 403 and message is "failed" for POST Request

      @API_45-3
      Scenario: [TC_03] The response body should contain an "addId" field, and this ID can be used to verify the newly
      created record by sending a POST body to the "apiteacher/homeworkbyId" endpoint.

        * Prepare url with path parameters "apiteacher/homeworkAdd".
        * Prepare "teacher" "nurettin" "CorrectPassword" token
        * Post request homeworkAdd
        * Prepare url with path parameters "apiteacher/homeworkbyId".
        * Prepare "teacher" "nurettin" "CorrectPassword" token
        * Post request homeworkByNewId
        * Verify that status code 200 and message is "Success" for POST Request



