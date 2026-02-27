package GETAPITestsWithoutBDD;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProductAPIsTest {
	
	
	//without BDD
	@Test
	public void getProductsTest_1() {
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		RequestSpecification request = RestAssured.given();
		
		request.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2Njk5MGExZjc5ZTZlYTAwMTNiMjlhNTciLCJpYXQiOjE3MjU1MTI5MDZ9.l0kUz9iCPMccrOd2DR9hZS8oBm_m0w0DJuQtDcy38ME");

		Response response = request.get("/contacts");
			
		int statusCode = response.statusCode();
		System.out.println("status code : " + statusCode);
		
		Assert.assertEquals(statusCode, 200);
		
		
		String statusLine = response.statusLine();
		System.out.println("status line : " + statusLine);
		
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

		String resBody = response.prettyPrint();
		System.out.println(resBody);
		
		
	}

}