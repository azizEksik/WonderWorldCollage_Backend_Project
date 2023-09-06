Feature: [US_046] As an administrator (teacher), I want to update the registered Homework information in the system through API connection.

  @API_46-1
  Scenario: [TC_01] When valid authorization and correct data are sent in a PATCH body to the "apiteacher/homeworkUpdate" endpoint,
  the response status code should be 200, and the response body's message should be "Success."

    * Prepare url with path parameters "apiteacher/homeworkUpdate".
    * Prepare "teacher" "nurettin" "CorrectPassword" token
    * Update request for homeworkUpdate
    * Verify that status code 200 and message is Success for HomeworkUpdate Request

    @API_46-2
    Scenario: [TC_02] When invalid authorization or incorrect data (id) are sent in a PATCH body
    o the "apiteacher/homeworkUpdate" endpoint, the response status code should be 403, and the response body's message should be "failed."

      * Prepare url with path parameters "apiteacher/homeworkUpdate".
      * Prepare "teacher" "nurettin" "WrongPassword" token
      * Update request for homeworkUpdate
      * Verify that status code 403 and message is failed for HomeworkUpdate Request

      @API_46-3
      Scenario: [TC_03] The "updateId" field in the response body should match the "id" field sent in the PATCH request body
      to the "apiteacher/homeworkUpdate" endpoint.

        * Prepare url with path parameters "apiteacher/homeworkUpdate".
        * Prepare "teacher" "nurettin" "CorrectPassword" token
        * Update request for homeworkUpdate
        * Verify that responseId match the updated Id.

        @API_46-4

        Scenario:  [TC_04] The successful update of the homework record can be verified through the API;
        sending a POST body to the "apiteacher/homeworkbyId" endpoint.

          * Prepare url with path parameters "apiteacher/homeworkUpdate".
          * Prepare "teacher" "nurettin" "CorrectPassword" token
          * Update request for homeworkUpdate

          * Prepare url with path parameters "apiteacher/homeworkbyId".
          * Prepare "teacher" "nurettin" "CorrectPassword" token
          * Post request for updated homeworkById

          * Verify that POST response data match the updated data