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
	public void CreateCustomer_TC001() {
		//Empty name feild
		//output-required name feild
		RestAssured.baseURI = "http://localhost:8080";
		String response = given().log().all().header("Content-Type", "application/json")
				.body("{\r\n" + "  \r\n" + "  \"name\": \"\",\r\n" + "  \"email\": \""
						+ UUID.randomUUID().toString() + "@d2.com\",\r\n" + "  \"phoneNumber\": \"121212\"\r\n" + "}")
				.when().post("/cetascyber/customer").then().extract().response()
				.asPrettyString();
		System.out.println("Response of the post method=" + response);

		JsonPath js = new JsonPath(response);
		String customerid = js.getString("customer.customerId");
		System.out.println("customer id which is extracted==" + customerid);
	}
	@Test
	public void CreateCustomer_TC002() {
		//Valid Inputs and name feild with integers
		RestAssured.baseURI = "http://localhost:8080";
		String response = given().log().all().header("Content-Type", "application/json")
				.body("{\r\n" + "  \r\n" + "  \"name\": \"sharmiTC2\",\r\n" + "  \"email\": \""
						+ UUID.randomUUID().toString() + "@d2.com\",\r\n" + "  \"phoneNumber\": \"121212\"\r\n" + "}")
				.when().post("/cetascyber/customer").then().extract().response()
				.asPrettyString();
		System.out.println("Response of the post method=" + response);

		JsonPath js = new JsonPath(response);
		String customerid = js.getString("customer.customerId");
		System.out.println("customer id which is extracted==" + customerid);
	}
	@Test
	public void CreateCustomer_TC003() {
		//Valid Inputs and name feild with special characters
		RestAssured.baseURI = "http://localhost:8080";
		String response = given().log().all().header("Content-Type", "application/json")
				.body("{\r\n" + "  \r\n" + "  \"name\": \"sharmiTC2@Special\",\r\n" + "  \"email\": \""
						+ UUID.randomUUID().toString() + "@d2.com\",\r\n" + "  \"phoneNumber\": \"121212\"\r\n" + "}")
				.when().post("/cetascyber/customer").then().extract().response()
				.asPrettyString();
		System.out.println("Response of the post method=" + response);

		JsonPath js = new JsonPath(response);
		String customerid = js.getString("customer.customerId");
		System.out.println("customer id which is extracted==" + customerid);
	}
	@Test
	public void CreateCustomer_TC004() {
		//Valid Inputs ,but customer name feild will be repeated
		//duplicate name feild-same name accepts twice with different email id
		RestAssured.baseURI = "http://localhost:8080";
		String response = given().log().all().header("Content-Type", "application/json")
				.body("{\r\n" + "  \r\n" + "  \"name\": \"sharmiTC2@Special\",\r\n" + "  \"email\": \""
						+ UUID.randomUUID().toString() + "@d2.com\",\r\n" + "  \"phoneNumber\": \"121212\"\r\n" + "}")
				.when().post("/cetascyber/customer").then().extract().response()
				.asPrettyString();
		System.out.println("Response of the post method=" + response);

		JsonPath js = new JsonPath(response);
		String customerid = js.getString("customer.customerId");
		System.out.println("customer id which is extracted==" + customerid);
	}
	@Test
	public void CreateCustomer_TC005() {
		//Valid Inputs ,but customer email feild is missing
		RestAssured.baseURI = "http://localhost:8080";
		String response = given().log().all().header("Content-Type", "application/json")
				.body(" {\r\n"
						+ "     \"name\": \"sharmilaEmail\",\r\n"
						+ "    \"email\": \"\",\r\n"
						+ "    \"phoneNumber\": \"888111\"\r\n"
						+ "  }")
				.when().post("/cetascyber/customer").then().extract().response()
				.asPrettyString();
		System.out.println("Response of the post method=" + response);

		JsonPath js = new JsonPath(response);
		String customerid = js.getString("customer.customerId");
		System.out.println("customer id which is extracted==" + customerid);
	}
	@Test
	public void CreateCustomer_TC006() {
		//Valid Inputs ,but phoneno with strings are passed as inputs
		RestAssured.baseURI = "http://localhost:8080";
		String response = given().log().all().header("Content-Type", "application/json")
				.body("{\r\n" + "  \r\n" + "  \"name\": \"sharmiphone\",\r\n" + "  \"email\": \""
						+ UUID.randomUUID().toString() + "@d2.com\",\r\n" + "  \"phoneNumber\": \"hhhhaaaa\"\r\n" + "}")
				.when().post("/cetascyber/customer").then().extract().response()
				.asPrettyString();
		System.out.println("Response of the post method=" + response);

		JsonPath js = new JsonPath(response);
		String customerid = js.getString("customer.customerId");
		System.out.println("customer id which is extracted==" + customerid);
	}
	@Test
	public void CreateCustomer_TC007() {
		//Valid Inputs ,but phoneno feild is empty
		RestAssured.baseURI = "http://localhost:8080";
		String response = given().log().all().header("Content-Type", "application/json")
				.body("{\r\n" + "  \r\n" + "  \"name\": \"sharmiphonefeildmissing\",\r\n" + "  \"email\": \""
						+ UUID.randomUUID().toString() + "@d2.com\",\r\n" + "  \"phoneNumber\": \"\"\r\n" + "}")
				.when().post("/cetascyber/customer").then().extract().response()
				.asPrettyString();
		System.out.println("Response of the post method=" + response);

		JsonPath js = new JsonPath(response);
		String customerid = js.getString("customer.customerId");
		System.out.println("customer id which is extracted==" + customerid);
	}
}
