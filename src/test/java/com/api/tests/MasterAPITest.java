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

import com.github.fge.jsonschema.main.JsonValidator;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {

	@Test
	public void masterAPITest() throws IOException {
		
		
		given().baseUri(getProperty("BASE_URI"))
		.header("Authorization",getToken(FD))
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post("master")   //default content type application/url-formencoded
		.then()
		.log().all()
		.statusCode(200)
		.time(lessThan(1000L))
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
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
		
		
	}

	
	@Test
	public void invalidTokenMasterAPITest() throws IOException {
		given().baseUri(getProperty("BASE_URI"))
		.header("Authorization","")
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post("master")   //default content type application/url-formencoded
		.then()
		.log().all()
		.statusCode(401);
		
	}
}
