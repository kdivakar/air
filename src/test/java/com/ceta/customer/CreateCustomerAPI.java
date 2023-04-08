package com.ceta.customer;

import static io.restassured.RestAssured.given;

import java.util.UUID;

import com.ceta.pojos.CreateCustomerRequest;
import com.ceta.pojos.CreateCustomerResponse;
import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class CreateCustomerAPI {

	private static String URI = "http://localhost:8080";

	// given-all the inputs
	// when -submit the http methods
	// then-validate the response
	@Test
	public void createCustomerWithEmptyName() {
		//Empty name field
		//output-required name field
		CreateCustomerRequest request = new CreateCustomerRequest();
		request.setName("");
		request.setEmail(UUID.randomUUID().toString() + "@gmail.com");
		request.setPhoneNumber("408-222-3222");

		RestAssured.baseURI = URI;
		CreateCustomerResponse response = given().log().all().header("Content-Type", "application/json")
				.body(request)
				.when().post("/cetascyber/customer").then()
				.assertThat().statusCode(400)
				.extract().response()
				.as(CreateCustomerResponse.class);

		// Convert POJO to json string, only for debugging
		Gson gson = new Gson();
		String jsonString = gson.toJson(response);
		System.out.println(jsonString);

		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getErrors());
		Assert.assertNull(response.getCustomer());
		Assert.assertEquals(response.getErrors().size(), 1);
		Assert.assertEquals(response.getErrors().get(0).getErrorCode(), "100");
		Assert.assertEquals(response.getErrors().get(0).getErrorMessage(), "Missing required field");
		Assert.assertEquals(response.getErrors().get(0).getDetail(), "name");
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
		String customerid = js.getString("com.ceta.customer.customerId");
		System.out.println("com.ceta.customer id which is extracted==" + customerid);
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
		String customerid = js.getString("com.ceta.customer.customerId");
		System.out.println("com.ceta.customer id which is extracted==" + customerid);
	}
	@Test
	public void CreateCustomer_TC004() {
		//Valid Inputs ,but com.ceta.customer name feild will be repeated
		//duplicate name feild-same name accepts twice with different email id
		RestAssured.baseURI = "http://localhost:8080";
		String response = given().log().all().header("Content-Type", "application/json")
				.body("{\r\n" + "  \r\n" + "  \"name\": \"sharmiTC2@Special\",\r\n" + "  \"email\": \""
						+ UUID.randomUUID().toString() + "@d2.com\",\r\n" + "  \"phoneNumber\": \"121212\"\r\n" + "}")
				.when().post("/cetascyber/customer").then().extract().response()
				.asPrettyString();
		System.out.println("Response of the post method=" + response);

		JsonPath js = new JsonPath(response);
		String customerid = js.getString("com.ceta.customer.customerId");
		System.out.println("com.ceta.customer id which is extracted==" + customerid);
	}
	@Test
	public void CreateCustomer_TC005() {
		//Valid Inputs ,but com.ceta.customer email feild is missing
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
		String customerid = js.getString("com.ceta.customer.customerId");
		System.out.println("com.ceta.customer id which is extracted==" + customerid);
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
		String customerid = js.getString("com.ceta.customer.customerId");
		System.out.println("com.ceta.customer id which is extracted==" + customerid);
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
		String customerid = js.getString("com.ceta.customer.customerId");
		System.out.println("com.ceta.customer id which is extracted==" + customerid);
	}
}
