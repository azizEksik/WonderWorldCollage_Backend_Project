Feature: As an administrator, I want to access the Session List through API connection.

  Scenario: TC_01 >> When a GET request is sent to the 'api/sessionList' endpoint with valid authorization credentials, it should be verified that the returned status code is 200 and the response message information is 'Success'

    * Prepare url with path parameters "api/sessionList".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * When a GET request is sent to the api-sessionList endpoint with valid authorization credentials, it is verified that the returned response has a status code of 200 and the message information in the body is confirmed to be 'Success'


  Scenario: TC_02 >> When a GET request with invalid authorization credentials is sent to the 'api/sessionList' endpoint, it should be verified that the returned status code is 403 and the response message information is confirmed to be 'failed'

    * Prepare url with path parameters "api/sessionList".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * When a GET request with invalid authorization credentials is sent to the api-sessionList endpoint, it is verified that the returned response has a status code of 403 and the message information in the body is confirmed to be 'failed'


  Scenario: TC_03 >> The content of the 'lists' within the response body should be verified to match the data (with id = '11') where the content contains session: '2017-18', is_active: 'no', created_at: '2017-04-20 02:41:37', and updated_at: '0000-00-00'

    * Prepare url with path parameters "api/sessionList".
    * Prepare "admin" "aziz" "CorrectPassword" token
    * When a GET request is sent to the api-sessionList endpoint with valid authorization credentials, the returned response should be verified to match the expected data