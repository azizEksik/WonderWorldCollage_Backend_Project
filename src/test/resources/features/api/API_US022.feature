Feature: [API_US022] As an administrator, I want to access the Alumni List through API connection.

  @get
  Scenario: [TC_01] >> When a valid GET request with proper authorization credentials is sent to the 'api/alumniList' endpoint, it should be confirmed that the returned status code is 200, and the response message is 'Success'

    * Prepare url with path parameters "api/alumniList".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * A GET request is sent to the api-alumniList endpoint
    * It should be verified that the status code of the response from the api-alumniList endpoint is 200, and the message information in the response body is 'Success'

  @get
  Scenario: [TC_02] >> When an invalid GET request with incorrect authorization credentials is sent to the 'api/alumniList' endpoint, it should be confirmed that the returned status code is 403, and the response message is 'failed
    * Prepare url with path parameters "api/alumniList".
    * Prepare "admin" "aziz" "WrongPassword" token
    * An invalid GET request with incorrect authorization is sent to the api-alumniList endpoint
    * It should be verified that the status code of the response from the api-alumniList endpoint is 403, and the message information in the response body is 'failed'


  Scenario: TC_03 >> The content of the 'lists' within the response body should be verified to match the data (with id = '2') where the content contains student_id: '41', current_email: 'rohan@gmail.com', current_phone: '0808080707', occupation: '', address: '', photo: null, and created_at: '2023-03-11 03:04:50'

    * Prepare url with path parameters "api/alumniList".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * Send a GET request to the api-alumniList endpoint, and verify that the data in the returned response matches the expected data