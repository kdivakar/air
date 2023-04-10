package com.ceta.customer;

import static io.restassured.RestAssured.given;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ceta.pojos.CreateCustomerRequest;
import com.ceta.pojos.CreateCustomerResponse;
import com.ceta.pojos.UpdateCustomerRequest;
import com.ceta.pojos.UpdateCustomerResponse;

import io.restassured.RestAssured;

public class UpdateCustomerAPI {
	private static String URI = "http://localhost:8080";
	
	@Test
	public void UpdateCustomerWithEmailId() {
		CreateCustomerRequest createrequest = new CreateCustomerRequest();
		createrequest.setName("stephendevsy");
		createrequest.setEmail(UUID.randomUUID().toString() + "@gmail.com");
		createrequest.setPhoneNumber("408-111-3222");

		RestAssured.baseURI = URI;
		CreateCustomerResponse response = given().log().all().header("Content-Type", "application/json")
				.body(createrequest)
				.when().post("/cetascyber/customer").then()
				.assertThat().statusCode(201).log().all()
				.extract().response()
				.as(CreateCustomerResponse.class);
		String  getcustomerEmail=response.getCustomer().getEmail();
		System.out.println("customer Email id="+getcustomerEmail);
		UpdateCustomerRequest updatereq=new UpdateCustomerRequest();
		updatereq.setEmail(getcustomerEmail);
		updatereq.setName("stephendevsy@Updated");
		updatereq.setPhoneNumber("408-000-3222");
		UpdateCustomerResponse updateresponse = given().log().all().header("Content-Type", "application/json")
				.body(updatereq)
				.when().put("/cetascyber/customer").then()
				.assertThat().statusCode(200).log().all()
				.extract().response()
				.as(UpdateCustomerResponse.class);
		System.out.println("Response got with updated name ==="+updateresponse.getCustomer().getName());
		System.out.println("Response got with updated phone ==="+updateresponse.getCustomer().getPhoneNumber());
		Assert.assertNotNull(updateresponse);
		Assert.assertNull(updateresponse.getErrors());
		Assert.assertNotNull(updateresponse.getCustomer());
		Assert.assertNotNull(updateresponse.getCustomer().getId());
		Assert.assertNotNull(updateresponse.getCustomer().getEmail());
		Assert.assertNotNull(updateresponse.getCustomer().getCustomerId());
		Assert.assertNotNull(updateresponse.getCustomer().getName());
		Assert.assertNotNull(updateresponse.getCustomer().getPhoneNumber()); 	
	}
	@Test
	public void UpdateCustomerWithEmptyFeildEmailId() {
		CreateCustomerRequest createrequest = new CreateCustomerRequest();
		createrequest.setName("stephendevsy");
		createrequest.setEmail(UUID.randomUUID().toString() + "@gmail.com");
		createrequest.setPhoneNumber("408-111-3222");

		RestAssured.baseURI = URI;
		CreateCustomerResponse response = given().log().all().header("Content-Type", "application/json")
				.body(createrequest)
				.when().post("/cetascyber/customer").then()
				.assertThat().statusCode(201).log().all()
				.extract().response()
				.as(CreateCustomerResponse.class);
		String  getcustomerEmail=response.getCustomer().getEmail();
		System.out.println("customer Email id="+getcustomerEmail);
		UpdateCustomerRequest updatereq=new UpdateCustomerRequest();
		
		updatereq.setEmail("");
		updatereq.setName("stephendevsy@Updated");
		updatereq.setPhoneNumber("408-000-3222");
		UpdateCustomerResponse updateresponse = given().log().all().header("Content-Type", "application/json")
				.body(updatereq)
				.when().put("/cetascyber/customer").then()
				.assertThat().statusCode(400).log().all()
				.extract().response()
				.as(UpdateCustomerResponse.class);
		
		Assert.assertNotNull(updateresponse);
		Assert.assertNotNull(updateresponse.getErrors());
		Assert.assertNull(updateresponse.getCustomer());
		Assert.assertEquals(updateresponse.getErrors().size(), 1);
		Assert.assertEquals(updateresponse.getErrors().get(0).getErrorCode(), "100");
		Assert.assertEquals(updateresponse.getErrors().get(0).getErrorMessage(), "Missing required field");
		Assert.assertEquals(updateresponse.getErrors().get(0).getDetail(), "email");
		}
	@Test
	public void UpdateCustomerWithOutPassingEmailAtribute() {
		CreateCustomerRequest createrequest = new CreateCustomerRequest();
		createrequest.setName("stephendevsy");
		createrequest.setEmail(UUID.randomUUID().toString() + "@gmail.com");
		createrequest.setPhoneNumber("408-111-3222");

		RestAssured.baseURI = URI;
		CreateCustomerResponse response = given().log().all().header("Content-Type", "application/json")
				.body(createrequest)
				.when().post("/cetascyber/customer").then()
				.assertThat().statusCode(201).log().all()
				.extract().response()
				.as(CreateCustomerResponse.class);
		String  getcustomerEmail=response.getCustomer().getEmail();
		System.out.println("customer Email id="+getcustomerEmail);
		UpdateCustomerRequest updatereq=new UpdateCustomerRequest();
		//updatereq.setEmail("");
		updatereq.setName("stephendevsy@Updated");
		updatereq.setPhoneNumber("408-000-3222");
		UpdateCustomerResponse updateresponse = given().log().all().header("Content-Type", "application/json")
				.body(updatereq)
				.when().put("/cetascyber/customer").then()
				.assertThat().statusCode(400).log().all()
				.extract().response()
				.as(UpdateCustomerResponse.class);
		
		Assert.assertNotNull(updateresponse);
		Assert.assertNotNull(updateresponse.getErrors());
		Assert.assertNull(updateresponse.getCustomer());
		Assert.assertEquals(updateresponse.getErrors().size(), 1);
		Assert.assertEquals(updateresponse.getErrors().get(0).getErrorCode(), "100");
		Assert.assertEquals(updateresponse.getErrors().get(0).getErrorMessage(), "Missing required field");
		Assert.assertEquals(updateresponse.getErrors().get(0).getDetail(), "email");
		}
	
	@Test
	public void UpdateCustomerWithInvalidEmailId() {
		//developer should fix the problem
		UpdateCustomerRequest updatereq=new UpdateCustomerRequest();
		String email="tthhttt@hhh";
		updatereq.setEmail(email);
		updatereq.setName("stephendevsy@Updated");
		updatereq.setPhoneNumber("408-000-3222");
		UpdateCustomerResponse updateresponse = given().log().all().header("Content-Type", "application/json")
				.body(updatereq)
				.when().put("/cetascyber/customer").then()
				.assertThat().statusCode(400).log().all()
				.extract().response()
				.as(UpdateCustomerResponse.class);
		Assert.assertNotNull(updateresponse);
		Assert.assertNotNull(updateresponse.getErrors());
		Assert.assertNull(updateresponse.getCustomer());
		Assert.assertEquals(updateresponse.getErrors().size(), 1);
		Assert.assertEquals(updateresponse.getErrors().get(0).getErrorCode(), "201");
		Assert.assertEquals(updateresponse.getErrors().get(0).getErrorMessage(), "Entity does not exists");
		Assert.assertEquals(updateresponse.getErrors().get(0).getDetail(), email);	
	}
	@Test
	public void UpdateCustomerWithoutPasssingName() {
		CreateCustomerRequest createrequest = new CreateCustomerRequest();
		createrequest.setName("stephendevsy");
		createrequest.setEmail(UUID.randomUUID().toString() + "@gmail.com");
		createrequest.setPhoneNumber("408-111-3222");

		RestAssured.baseURI = URI;
		CreateCustomerResponse response = given().log().all().header("Content-Type", "application/json")
				.body(createrequest)
				.when().post("/cetascyber/customer").then()
				.assertThat().statusCode(201).log().all()
				.extract().response()
				.as(CreateCustomerResponse.class);
		String  getcustomerEmail=response.getCustomer().getEmail();
		System.out.println("customer Email id="+getcustomerEmail);
		UpdateCustomerRequest updatereq=new UpdateCustomerRequest();
		updatereq.setEmail(getcustomerEmail);
		updatereq.setName("");//we can remove the whole line and same result will come
		updatereq.setPhoneNumber("408-000-3222");
		UpdateCustomerResponse updateresponse = given().log().all().header("Content-Type", "application/json")
				.body(updatereq)
				.when().put("/cetascyber/customer").then()
				.assertThat().statusCode(400).log().all()
				.extract().response()
				.as(UpdateCustomerResponse.class);
		Assert.assertNotNull(updateresponse);
		Assert.assertNotNull(updateresponse.getErrors());
		Assert.assertNull(updateresponse.getCustomer());
		Assert.assertEquals(updateresponse.getErrors().size(), 1);
		Assert.assertEquals(updateresponse.getErrors().get(0).getErrorCode(), "100");
		Assert.assertEquals(updateresponse.getErrors().get(0).getErrorMessage(), "Missing required field");
		Assert.assertEquals(updateresponse.getErrors().get(0).getDetail(),"name");	
	}
	@Test
	public void UpdateCustomerWithEmptyPhoneno() {
		CreateCustomerRequest createrequest = new CreateCustomerRequest();
		createrequest.setName("stephendevsy");
		createrequest.setEmail(UUID.randomUUID().toString() + "@gmail.com");
		createrequest.setPhoneNumber("408-111-3222");

		RestAssured.baseURI = URI;
		CreateCustomerResponse response = given().log().all().header("Content-Type", "application/json")
				.body(createrequest)
				.when().post("/cetascyber/customer").then()
				.assertThat().statusCode(201).log().all()
				.extract().response()
				.as(CreateCustomerResponse.class);
		String  getcustomerEmail=response.getCustomer().getEmail();
		System.out.println("customer Email id="+getcustomerEmail);
		UpdateCustomerRequest updatereq=new UpdateCustomerRequest();
		updatereq.setEmail(getcustomerEmail);
		updatereq.setName("Peter");
		//updatereq.setPhoneNumber("");
		UpdateCustomerResponse updateresponse = given().log().all().header("Content-Type", "application/json")
				.body(updatereq)
				.when().put("/cetascyber/customer").then()
				.assertThat().statusCode(200).log().all()
				.extract().response()
				.as(UpdateCustomerResponse.class);
		System.out.println("Response got with updated name ==="+updateresponse.getCustomer().getName());
		System.out.println("Response got with updated phone ==="+updateresponse.getCustomer().getPhoneNumber());
		Assert.assertNotNull(updateresponse);
		Assert.assertNull(updateresponse.getErrors());
		Assert.assertNotNull(updateresponse.getCustomer());
		Assert.assertNotNull(updateresponse.getCustomer().getId());
		Assert.assertNotNull(updateresponse.getCustomer().getEmail());
		Assert.assertNotNull(updateresponse.getCustomer().getCustomerId());
		Assert.assertNotNull(updateresponse.getCustomer().getName());
		Assert.assertNull(updateresponse.getCustomer().getPhoneNumber()); 	
	}
	
	
	
	
	}
	
	


