Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if Place is being added successfully using AddPlaceAPI
  Given Add Place Payload with "<name>" and "<language>" and "<address>"
  When user calls "AddPlaceAPI" with "POST" http request
  Then the API call is success with status code 200
  And "status" in response body is "OK"
  And "scope" in response body is "APP"
  And verify placeid mapped to "name" as "<name>" using "getPlaceAPI"

  Examples:
    |name     |language |address             |
    | AAHouse | English | World cross center |
 #   |  BBHouse| Spanish |  Sea cross center  |


@UpdatePlace
Scenario Outline: Verify if place is being updated successfully using UpdatePlaceAPI
  Given Update Place Payload with "<address>"
  When user calls "UpdatePlaceAPI" with "PUT" http request
  Then the API call is success with status code 200
  And "msg" in response body is "Address successfully updated"
  And verify "address" is mapped to "<address>" using "getPlaceAPI"


 Examples:
  | address |
  | 70 winter walk, USA |


@DeletePlace
Scenario: Verify if place is being deleted using DeletePlaceAPI
  Given DeletePlace API Payload
  When user calls "deletePlaceAPI" with "POST" http request
  Then the API call is success with status code 200
  And "status" in response body is "OK"
  And verify placeid mapped to "name" as "<name>" using "getPlaceAPI"


