@Test
Feature: As an API user i will get the promotion details

  @PositiveTestCase
  Scenario Outline: Verify the promotions API response
    When I get a list of promotions using API key "<url>", "<apiKey>"
    Then I validate the response "<statusCode>"

    Examples:
      | url                                                              | apiKey                        | statusCode |
      | https://api.intigral-ott.net/popcorn-api-rs-7.9.17/v1/promotions | webB2BGDMSTGExy0sVDlZMzNDdUyZ | 200        |

  @NegativeTestCase
  Scenario Outline: Verify the promotions API error response
    When I get a list of promotions using API key "<url>", "<apiKey>"
    Then I validate the error response "<statusCode>","<errorCode>","<errorMessage>"

    Examples:
      | url                                                              | apiKey  | statusCode | errorCode | errorMessage    |
      | https://api.intigral-ott.net/popcorn-api-rs-7.9.17/v1/promotions | invalid | 403        | 8001      | invalid api key |