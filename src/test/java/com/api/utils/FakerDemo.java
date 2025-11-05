package com.api.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class FakerDemo {

	public static void main(String[] args) {
		// we will be using the faker library for our fake test data creation
		// we will be creating a fakerUtil that uses this faker library!

		Faker faker = new Faker(new Locale("en-IND"));
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		System.out.println(firstName + " " + lastName);
		String buildingNumber = faker.address().buildingNumber();
		System.out.println(buildingNumber);
		System.out.println(faker.address().streetAddress());
		System.out.println(faker.address().streetName());
		System.out.println(faker.address().city());
		System.out.println(faker.number().digits(5));
		System.out.println(faker.numerify("704#######"));
		System.out.println(faker.numerify("704#######"));
		System.out.println(faker.internet().emailAddress());
		System.out.println(faker.phoneNumber().cellPhone());
	}
}
