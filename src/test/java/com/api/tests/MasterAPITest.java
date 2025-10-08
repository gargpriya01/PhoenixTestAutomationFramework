package com.api.tests;

import static com.api.constant.Role.FD;
import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import io.restassured.http.ContentType;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPITest {

	@Test(description = "Verify if the Master API response is giving correct response", groups= {"api","smoke","regression"})
	public void masterAPITest() throws IOException {
		
		
		given()
		.spec(requestSpecWithAuth(FD))
		.when()
		.post("master")   //default content type application/url-formencoded
		.then()
		.spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		.body("data", notNullValue())
		.body("data", hasKey("mst_oem"))
		.body("data", hasKey("mst_model"))
		.body("$", hasKey("message"))
		.body("$", hasKey("data"))
		.body("data.mst_oem.size()", greaterThan(0))  //check the size of the json array with matchers
		.body("data.mst_model.size()",equalTo(3))
		.body("data.mst_oem.id", everyItem(notNullValue()))
		.body("data.mst_oem.name", everyItem(notNullValue()))
		.body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
		
		
	}

	
	@Test(description = "Verify if the Master API response is giving correct status code for invalid token", groups= {"api","negative","smoke","regression"})
	public void invalidTokenMasterAPITest() throws IOException {
		given()
		.spec(requestSpec())
		.when()
		.post("master")   //default content type application/url-formencoded
		.then()
	.spec(responseSpec_TEXT(401));
		
	}
}
