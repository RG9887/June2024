package stepDefinations;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.Payload;
import static org.junit.Assert.*;
import java.io.IOException;
import utility.Utils;

public class stepDefinations extends Utils {
	Payload data = new Payload();
	RequestSpecification Reqs;
	Response Resp;
	static String PlaceId;
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		
		Reqs = given().spec(RequestSpecification()).body(data.addPlacePayload(name, language, address));  
	}
	
	@When("user triggers Place api with {string} method")
	public void user_triggers_place_api_with_method(String API) {
		
		if(API.equalsIgnoreCase("POST"))
	    Resp = Reqs.when().post("/maps/api/place/add/json");
		
		if(API.equalsIgnoreCase("GET"))
		Resp = Reqs.when().get("/maps/api/place/get/json");
		
		if(API.equalsIgnoreCase("PUT"))
		Resp = Reqs.when().put("/maps/api/place/update/json");
		
		if(API.equalsIgnoreCase("DELETE"))
		Resp = Reqs.when().post("/maps/api/place/delete/json");	
	}
	
	@Then("Api call is sucusses with {int} status code.")
	public void api_call_is_sucusses_with_status_code(int code) {
		//Resp = Resp.then().extract().response();
	    assertEquals(Resp.getStatusCode(),code);
	}
	
	@Then("In api response {string} is {string}")
	public void in_api_response_is(String Key, String ExpValue) {
		String actualValue =  getJsonPath(Resp, Key);
		assertEquals(actualValue,ExpValue);
	}
	
	@Then("Check the {string} returned in the getPlace API")
	public void check_the_returned_in_the_get_place_api(String expName) throws IOException {
	    PlaceId = getJsonPath(Resp, "place_id");
	    Reqs = given().spec(RequestSpecification()).queryParam("place_id", PlaceId);
	    user_triggers_place_api_with_method("GET");
	    String actualName = getJsonPath(Resp, "name");
	    assertEquals(actualName,expName);
	}
	
	@Given("Update place payload.")
	public void update_place_payload() throws IOException {
		Reqs = given().spec(RequestSpecification()).body("{\"place_id\":\""+PlaceId+"\",\"address\":\"Zase House 15\",\"key\":\"qaclick123\"}");
	}
	
	@Given("Delete place payload.")
	public void delete_place_payload() throws IOException {
		Reqs = given().spec(RequestSpecification()).body("{\"place_id\":\""+PlaceId+"\"}");
	}
}