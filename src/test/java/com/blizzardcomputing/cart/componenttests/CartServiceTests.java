package com.blizzardcomputing.cart.componenttests;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import com.blizzardcomputing.cart.componenttests.annotation.ComponentTest;

import io.restassured.RestAssured;
import net.serenitybdd.junit.spring.integration.SpringIntegrationSerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SpringIntegrationSerenityRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0, files = "classpath:/wiremock/")
@Category(ComponentTest.class)
public class CartServiceTests {

	@Steps
	private CartSteps cartSteps;

	@LocalServerPort
	private int port;

	@Value("${wiremock.server.port}")
	private Integer wiremockPort;
	
	@Before
	public void setUp() {
		RestAssured.port = port;
	}

	@Test
	public void verifyWeCanRetrieveAnExistingCart() {

		System.out.println("Using Wiremock port: " + wiremockPort);
		System.out.println("Using Spring Boot port: " + port);
		
		// GIVEN there is a cart with ID 1
		// WHEN I retrieve the cart
		cartSteps.retrieveCart("1");
		
		// THEN I should get back a successful response
		cartSteps.searchIsExecutedSuccesfully(200);
		
		// THEN I should get back the expected body
		cartSteps.iShouldGetExpectedResponseBody("{\"id\":1,\"items\":[{\"id\":21323293803,\"productId\":\"ASAS2-D343AA\",\"price\":122.33}]}");

	}

	@Test
	public void verifyWeGetErrorForInvalidProductId() {

		System.out.println("Using Wiremock port: " + wiremockPort);
		System.out.println("Using Spring Boot port: " + port);
		
		// GIVEN there is a cart with ID 2
		// WHEN I retrieve the cart
		cartSteps.retrieveCart("2");
		
		// THEN I should get back a successful response
		cartSteps.searchIsExecutedSuccesfully(200);
		
		// THEN I should get back the expected body
		cartSteps.iShouldGetExpectedResponseBody("{\"id\":2,\"items\":[{\"id\":21323293803,\"productId\":\"FF9A1-G355LP\",\"price\":null}]}");

	}
	
	@Test
	public void verifyWeGetErrorForInvalidCartId() {

		System.out.println("Using Wiremock port: " + wiremockPort);
		System.out.println("Using Spring Boot port: " + port);
		
		// GIVEN there is a cart with ID 2
		// WHEN I retrieve the cart
		cartSteps.retrieveCart("99");
		
		// THEN I should get back a not found response
		cartSteps.searchIsExecutedSuccesfully(404);
		
		// THEN I should get back the expected body
		cartSteps.iShouldGetExpectedErrorMessage("org.springframework.data.rest.webmvc.ResourceNotFoundException");

	}
}
