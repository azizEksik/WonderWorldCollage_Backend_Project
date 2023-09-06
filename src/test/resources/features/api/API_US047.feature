Feature: As an administrator (teacher), I want to be able to delete a Homework record from the system through API connection.

  @API_47-1
  Scenario: When valid authorization and correct data (id) are sent in a DELETE body to the "apiteacher/homeworkDelete" endpoint,
  the response status code should be 200, and the response body's message should be "Success."

    * Prepare url with path parameters "apiteacher/homeworkDelete".
    * Prepare "teacher" "nurettin" "CorrectPassword" token
    * Delete request for homeworkDelete
    * Verify that status code 200 and message is Success for homeworkDelete.

    @API_47-2
    Scenario: When invalid authorization or incorrect data (id) are sent in a DELETE body to the "apiteacher/homeworkDelete" endpoint,
    the response status code should be 403, and the response body's message should be "failed."

      * Prepare url with path parameters "apiteacher/homeworkDelete".
      * Prepare "teacher" "nurettin" "WrongPassword" token
      * Delete request for homeworkDelete
      * Verify that status code 403 and message is failed for homeworkDelete.

      @API_47-3
      Scenario: The "DeletedId" field in the response body should match the "id" field sent in the DELETE request body
      to the "apiteacher/homeworkDelete" endpoint.

        * Prepare url with path parameters "apiteacher/homeworkDelete".
        * Prepare "teacher" "nurettin" "CorrectPassword" token
        * Delete request for homeworkDelete
        * Verify that DeletedId in the respBody match the id sent in the reqBody

        @API_47-4
        Scenario: The response body should contain a "DeletedId" field, and this ID can be used to verify that
        the record was deleted by sending a POST body to the "apiteacher/homeworkbyId" endpoint.

          * Prepare url with path parameters "apiteacher/homeworkDelete".
          * Prepare "teacher" "nurettin" "CorrectPassword" token
          * Delete request for homeworkDelete
          * Verify that status code 200 and message is Success for homeworkDelete.
          * Get the deletedId
          * Prepare url with path parameters "apiteacher/homeworkbyId".
          * Prepare "teacher" "nurettin" "CorrectPassword" token
          * Post request homeworkById
          * Verify that status code 403 and message is "failed" for POST Request

