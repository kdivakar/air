package Customer;

import static io.restassured.RestAssured.given;

import java.util.UUID;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class CreateCustomer {

	// given-all the inputs
	// when -submit the http methods
	// then-validate the response
	@Test
	public void CreateCustomerPost() {
		RestAssured.baseURI = "http://localhost:8080";
		String response = given().log().all().header("Content-Type", "application/json")
				.body("{\r\n" + "  \r\n" + "  \"name\": \"divakar2\",\r\n" + "  \"email\": \""
						+ UUID.randomUUID().toString() + "@d2.com\",\r\n" + "  \"phoneNumber\": \"121212\"\r\n" + "}")
				.when().post("/cetascyber/customer").then().assertThat().statusCode(201).extract().response()
				.asPrettyString();
		System.out.println("Response of the post method=" + response);

		JsonPath js = new JsonPath(response);
		String customerid = js.getString("customerId");
		System.out.println("customer id which is extracted==" + customerid);
	}

}
