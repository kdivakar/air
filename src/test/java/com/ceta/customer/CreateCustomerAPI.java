package com.ceta.customer;

import static io.restassured.RestAssured.given;

import java.util.UUID;

import com.ceta.pojos.CreateCustomerRequest;
import com.ceta.pojos.CreateCustomerResponse;
import com.google.gson.Gson;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

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
				.assertThat().statusCode(400).log().all()
				.extract().response()
				.as(CreateCustomerResponse.class);

		// Convert POJO to json string, only for debugging
		Gson gson = new Gson();
		String jsonString = gson.toJson(response);
		System.out.println("Deserialisation==="+jsonString);

		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getErrors());
		Assert.assertNull(response.getCustomer());
		Assert.assertEquals(response.getErrors().size(), 1);
		Assert.assertEquals(response.getErrors().get(0).getErrorCode(), "100");
		Assert.assertEquals(response.getErrors().get(0).getErrorMessage(), "Missing required field");
		Assert.assertEquals(response.getErrors().get(0).getDetail(), "name");
	}

	@Test
	public void CreateCustomerWithValidInputIntegers() {
		//Valid Inputs and name field with integers

		CreateCustomerRequest request = new CreateCustomerRequest();
		request.setName("sharmila07");
		request.setEmail(UUID.randomUUID().toString() + "@gmail.com");
		request.setPhoneNumber("408-222-3222");

		RestAssured.baseURI = URI;
		CreateCustomerResponse response = given().log().all().header("Content-Type", "application/json")
				.body(request)
				.when().post("/cetascyber/customer").then()
				.assertThat().statusCode(201).log().all()
				.extract().response()
				.as(CreateCustomerResponse.class);

		// Convert POJO to json string, only for debugging
		Gson gson = new Gson();
		String jsonString = gson.toJson(response);
		System.out.println("Deserialisation==="+jsonString);

		Assert.assertNotNull(response);
		Assert.assertNull(response.getErrors());
		Assert.assertNotNull(response.getCustomer());
		Assert.assertNotNull(response.getCustomer().getId());
		Assert.assertNotNull(response.getCustomer().getEmail());
		Assert.assertNotNull(response.getCustomer().getCustomerId());
		Assert.assertNotNull(response.getCustomer().getName());
		Assert.assertNotNull(response.getCustomer().getPhoneNumber()); 
	}
	@Test
	public void CreateCustomerValidInputsSpecialChrs() {
		//Valid Inputs and name field with special characters
		CreateCustomerRequest request = new CreateCustomerRequest();
		request.setName("sharmila07@71");
		request.setEmail(UUID.randomUUID().toString() + "@gmail.com");
		request.setPhoneNumber("408-222-3222");

		RestAssured.baseURI = URI;
		CreateCustomerResponse response = given().log().all().header("Content-Type", "application/json")
				.body(request)
				.when().post("/cetascyber/customer").then()
				.assertThat().statusCode(201).log().all()
				.extract().response()
				.as(CreateCustomerResponse.class);

		// Convert POJO to json string, only for debugging
		Gson gson = new Gson();
		String jsonString = gson.toJson(response);
		System.out.println("Deserialisation==="+jsonString);

		Assert.assertNotNull(response);
		Assert.assertNull(response.getErrors());
		Assert.assertNotNull(response.getCustomer());
		Assert.assertNotNull(response.getCustomer().getId());
		Assert.assertNotNull(response.getCustomer().getEmail());
		Assert.assertNotNull(response.getCustomer().getCustomerId());
		Assert.assertNotNull(response.getCustomer().getName());
		Assert.assertNotNull(response.getCustomer().getPhoneNumber()); 

	}
	@Test
	public void CreateCustomerWithDuplicateName() {
		//Valid Inputs ,but com.ceta.customer name field will be repeated
		//duplicate name field-same name accepts twice with different email id
		CreateCustomerRequest request = new CreateCustomerRequest();
		request.setName("sharmila07@71");
		request.setEmail(UUID.randomUUID().toString() + "@gmail.com");
		request.setPhoneNumber("408-222-3222");

		RestAssured.baseURI = URI;
		CreateCustomerResponse response = given().log().all().header("Content-Type", "application/json")
				.body(request)
				.when().post("/cetascyber/customer").then()
				.assertThat().statusCode(201).log().all()
				.extract().response()
				.as(CreateCustomerResponse.class);

		// Convert POJO to json string, only for debugging
		Gson gson = new Gson();
		String jsonString = gson.toJson(response);
		System.out.println("Deserialisation==="+jsonString);

		Assert.assertNotNull(response);
		Assert.assertNull(response.getErrors());
		Assert.assertNotNull(response.getCustomer());
		Assert.assertNotNull(response.getCustomer().getId());
		Assert.assertNotNull(response.getCustomer().getEmail());
		Assert.assertNotNull(response.getCustomer().getCustomerId());
		Assert.assertNotNull(response.getCustomer().getName());
		Assert.assertNotNull(response.getCustomer().getPhoneNumber()); 
	
	}
	@Test
	public void CreateCustomerWithEmptyEmailId() {
		//Valid Inputs ,but  email field is missing
		CreateCustomerRequest request = new CreateCustomerRequest();
		request.setName("sharmila07@71");
		request.setEmail("");
		request.setPhoneNumber("408-222-3222");

		RestAssured.baseURI = URI;
		CreateCustomerResponse response = given().log().all().header("Content-Type", "application/json")
				.body(request)
				.when().post("/cetascyber/customer").then()
				.assertThat().statusCode(400).log().all()
				.extract().response()
				.as(CreateCustomerResponse.class);

		// Convert POJO to json string, only for debugging
		Gson gson = new Gson();
		String jsonString = gson.toJson(response);
		System.out.println("Deserialisation==="+jsonString);

		Assert.assertNotNull(response);
		Assert.assertNotNull(response.getErrors());
		Assert.assertNull(response.getCustomer());
		Assert.assertEquals(response.getErrors().size(), 1);
		Assert.assertEquals(response.getErrors().get(0).getErrorCode(), "100");
		Assert.assertEquals(response.getErrors().get(0).getErrorMessage(), "Missing required field");
		Assert.assertEquals(response.getErrors().get(0).getDetail(), "email");
	
	}
	@Test
	public void CreateCustomerWithValidPhonenumber() {
		//Valid Inputs ,but phoneno with strings are passed as inputs
		CreateCustomerRequest request = new CreateCustomerRequest();
		request.setName("sharmila07");
		request.setEmail(UUID.randomUUID().toString() + "@gmail.com");
		request.setPhoneNumber("408-222-rrre");

		RestAssured.baseURI = URI;
		CreateCustomerResponse response = given().log().all().header("Content-Type", "application/json")
				.body(request)
				.when().post("/cetascyber/customer").then()
				.assertThat().statusCode(201).log().all()
				.extract().response()
				.as(CreateCustomerResponse.class);

		// Convert POJO to json string, only for debugging
		Gson gson = new Gson();
		String jsonString = gson.toJson(response);
		System.out.println("Deserialisation==="+jsonString);

		Assert.assertNotNull(response);
		Assert.assertNull(response.getErrors());
		Assert.assertNotNull(response.getCustomer());
		Assert.assertNotNull(response.getCustomer().getId());
		Assert.assertNotNull(response.getCustomer().getEmail());
		Assert.assertNotNull(response.getCustomer().getCustomerId());
		Assert.assertNotNull(response.getCustomer().getName());
		Assert.assertNotNull(response.getCustomer().getPhoneNumber()); 
	

	}
	@Test
	public void CreateCustomerWithEmptyPhoneNumber() {
		//Valid Inputs ,but phoneno feild is empty
		CreateCustomerRequest request = new CreateCustomerRequest();
		request.setName("sharmila07");
		request.setEmail(UUID.randomUUID().toString() + "@gmail.com");
		request.setPhoneNumber("");

		RestAssured.baseURI = URI;
		CreateCustomerResponse response = given().log().all().header("Content-Type", "application/json")
				.body(request)
				.when().post("/cetascyber/customer").then()
				.assertThat().statusCode(201).log().all()
				.extract().response()
				.as(CreateCustomerResponse.class);

		// Convert POJO to json string, only for debugging
		Gson gson = new Gson();
		String jsonString = gson.toJson(response);
		System.out.println("Deserialisation==="+jsonString);

		Assert.assertNotNull(response);
		Assert.assertNull(response.getErrors());
		Assert.assertNotNull(response.getCustomer());
		Assert.assertNotNull(response.getCustomer().getId());
		Assert.assertNotNull(response.getCustomer().getEmail());
		Assert.assertNotNull(response.getCustomer().getCustomerId());
		Assert.assertNotNull(response.getCustomer().getName());
		Assert.assertNotNull(response.getCustomer().getPhoneNumber()); 

	}
}
