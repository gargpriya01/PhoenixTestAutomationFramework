package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;

public class CreateJobAPITest {
	

	
	
	@Test
	public void createJobAPITest() throws IOException {
		Customer customer=new Customer("Priya","Garg","9739791218","","priyagarg34@gmail.com","");
		CustomerAddress customerAddress=new CustomerAddress("d-26", "dream park", "sushant lok 2", "shalom presidency school",
				"sector 5", "122011", "India", "Haryana");
		CustomerProduct customerProduct=new CustomerProduct("2025-04-06T18:30:00.000Z", "13963931609299", "13963931609299", "13963931609299", "2025-04-06T18:30:00.000Z", 1, 1);
		
		Problems problems=new Problems(1, "Battery Issue");
		Problems[] problemsArray=new Problems[1];
		problemsArray[0]=problems;
		CreateJobPayload createJobPayload
		=new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		given()
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.log().all()
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtil.responseSpec_OK());
	}

}
