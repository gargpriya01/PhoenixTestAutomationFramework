package com.api.tests;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


import java.io.IOException;

import org.testng.annotations.Test;

import com.api.utils.AuthTokenProvider;
import static com.api.utils.ConfigManager.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CountAPITest {

	@Test
	public void verifyCountAPIResponse() throws IOException {
		given().baseUri(getProperty("BASE_URI"))
		.header("Authorization",AuthTokenProvider.getToken(FD))
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.get("dashboard/count")
		.then()
		.log()
		.all()
		.statusCode(200)
		.body("message", equalTo("Success"))
		.time(lessThan(1000L))
		.body("data", notNullValue())
		.body("data.size()", equalTo(3))
		.body("data.count",everyItem(greaterThanOrEqualTo(0)))
		.body("data.label", everyItem(not(blankOrNullString())))
		.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"))
		.body("data.key", containsInAnyOrder("pending_fst_assignment","pending_for_delivery","created_today"));
	}
	
	@Test
	public void countAPITest_MissingAuthToken() throws IOException {
		given().baseUri(getProperty("BASE_URI"))
		.log().uri()
		.log().method()
		.log().headers()
		.when()
		.get("dashboard/count")
		.then()
		.log()
		.all()
		.statusCode(401);
		
	}

}
