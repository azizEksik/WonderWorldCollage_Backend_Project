Feature: [API_US043] As an administrator (teacher), I want to access the Homework List through API connection.


  @get
  Scenario: [TC_01] When a valid authorization is provided with a GET request to the "apiteacher/homeworkList" endpoint,
  the response status code should be 200, and the response message should be "Success."

    * Prepare url with path parameters "apiteacher/homeworkList".
    * Prepare "teacher" "nurettin" "CorrectPassword" token
    * Get request.
    * Verify that status code 200 and message is "Success".



  @get
    Scenario: [TC_02] When an invalid authorization is provided with a GET request to the "apiteacher/homeworkList" endpoint,
    the response status code should be 403, and the response message should be "failed."

      * Prepare url with path parameters "apiteacher/homeworkList".
      * Prepare "teacher" "nurettin" "WrongPassword" token
      * Get request.
      * Verify that status code 403 and message is "failed".

      @API_43-3
  Scenario: [TC_03] The content of the "lists" in the response body should be verified to match the specified data.

    * Prepare url with path parameters "apiteacher/homeworkList".
    * Prepare "teacher" "nurettin" "CorrectPassword" token
    * Get request.
    * Verify the content of the list with specified data.





