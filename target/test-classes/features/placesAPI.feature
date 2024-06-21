Feature: Validate many Place related APIs

@AddPlace
Scenario Outline: Verify that add place api is working.
	Given Add place payload with "<Name>" "<Language>" "<Address>"
	When user triggers Place api with "POST" method
	Then Api call is sucusses with 200 status code.
	And In api response "status" is "OK"
	And In api response "scope" is "APP"
	And Check the "<Name>" returned in the getPlace API
	
Examples: 
		| Name 			| Language 		| Address 			|
		| RG House 	|	English-IN	| Base House 11 |
#		| MN House	| French-FR		| Case House 12 |

@PutPlace
Scenario: Verify the update place api is working.
	Given Update place payload.
	When user triggers Place api with "PUT" method
	Then Api call is sucusses with 200 status code.
	And In api response "msg" is "Address successfully updated"

@DeletePlace
Scenario: Verify the delete place api is working.
	Given Delete place payload.
	When user triggers Place api with "DELETE" method
	Then Api call is sucusses with 200 status code.
	And In api response "status" is "OK"