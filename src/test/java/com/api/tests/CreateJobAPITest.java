package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.ServiceLocation;
import com.api.constant.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import static com.api.utils.DateTimeUtil.*;
import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CreateJobAPITest {
	private CreateJobPayload createJobPayload;
	
	@BeforeMethod(description = "Creating createjob api request payload")
	public void setup() {
		Customer customer=new Customer("Priya","Garg","9739791218","","priyagarg34@gmail.com","");
		CustomerAddress customerAddress=new CustomerAddress("d-26", "dream park", "sushant lok 2", "shalom presidency school",
				"sector 5", "122011", "India", "Haryana");
		CustomerProduct customerProduct=new CustomerProduct(getTimeWithDaysAgo(10), "13963241618999", "13963241618999", "13963241618999", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		
		Problems problems=new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery Issue");
		List<Problems> problemsList=new ArrayList<Problems>();
		problemsList.add(problems);
		createJobPayload
		=new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemsList);
	}
	
	@Test(description = "Verify if the create job api is able to create the Inwarranty job", groups= {"api","smoke","regression"})
	public void createJobAPITest() throws IOException {
		
		
		given()
		.spec(requestSpecWithAuth(Role.FD, createJobPayload))
		.log().all()
		.when()
		.post("/job/create")
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id",equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
	}
	
	

	
	
	
}
