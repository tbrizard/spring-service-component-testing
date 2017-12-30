package com.blizzardcomputing.cart.componenttests;

import static org.hamcrest.Matchers.containsString;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONAs;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;


public class CartSteps {

	protected Response response;

	@Step("Then I should get back a successful response with code {0}")
	public void searchIsExecutedSuccesfully(int httpStatusCode) {
		response.then().statusCode(httpStatusCode);
	}

	@Step("Then I should get back a response body {0}")
	public void iShouldGetExpectedResponseBody(String expectedResponseBody) {
		response.then().body(sameJSONAs(expectedResponseBody).allowingAnyArrayOrdering());
	}

	@Step("Then I should get back an error response with code {0}")
	public void searchIsExecutedWithErrors(int httpStatusCode) {
		response.then().statusCode(httpStatusCode);
	}

	@Step("Then I should get back the expected error message {0}")
	public void iShouldGetExpectedErrorMessage(String expectedErrorMessage) {
		response.then().body(containsString(expectedErrorMessage));
	}

	@Step("When I search for cart {0}")
	public void retrieveCart(String id) {

		String url = "/carts/" + id;  // use a relative path and Rest Assured will build the absolute URL
		
		response = SerenityRest.given().headers("content-type", "application/json").when().get(url);
	}


}
