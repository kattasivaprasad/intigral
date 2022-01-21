@Test
Feature: As an API user i will get the promotion details

  @PositiveTestCase
  Scenario Outline: Verify the promotions API response
    When I get a list of promotions using API key "<url>","<Method>","<apiKey>"
    Then I validate the response "<statusCode>"

    Examples:
      | Method | statusCode | apiKey                        | url                                                              |
      | GET    | 200        | webB2BGDMSTGExy0sVDlZMzNDdUyZ | https://api.intigral-ott.net/popcorn-api-rs-7.9.17/v1/promotions |


  @NegativeTestCase
  Scenario Outline: Verify the promotions API error response
    When I get a list of promotions using API key "<url>","<Method>","<apiKey>"
    Then I validate the error response "<statusCode>","<errorCode>","<errorMessage>"

    Examples:
      | Method | statusCode | apiKey  | errorCode | errorMessage    | url                                                              |
      | GET    | 403        | invalid | 8001      | invalid api key | https://api.intigral-ott.net/popcorn-api-rs-7.9.17/v1/promotions |