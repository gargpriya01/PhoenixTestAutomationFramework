package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.constant.Role.*;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;

public class UserDetailsAPITest {

	@Test
	public void userDetailsAPITest() throws IOException {
		Header authHeader = new Header("Authorization",getToken(SUP));

		given().baseUri(getProperty("BASE_URI")).header(authHeader).accept(ContentType.JSON)
		.log().uri()
		.log().method()
		.log().headers()
		.log().body()
		.when()
				.get("userdetails").then().log().all()
				.statusCode(200).time(lessThan(1000L))
				.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}

}
