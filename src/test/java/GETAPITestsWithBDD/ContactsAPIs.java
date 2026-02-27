package GETAPITestsWithBDD;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ContactsAPIs {
	
	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

	}
	
	
	
	@Test
	public void getContactsAPITest() {
		
		given().log().all()
			.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2Njk5MGExZjc5ZTZlYTAwMTNiMjlhNTciLCJpYXQiOjE3MjU1MTI5MDZ9.l0kUz9iCPMccrOd2DR9hZS8oBm_m0w0DJuQtDcy38ME")
				.when().log().all()
					.get("/contacts")
							.then().log().all()
								.assertThat()
									.statusCode(200)
										.and()
											.statusLine("HTTP/1.1 200 OK")
												.and()
													.contentType(ContentType.JSON)
														.and()
															.body("$.size()", equalTo(6));								
	}
	
	@Test
	public void getContactAPITest_WithInvalidToken() {
		
		given().log().all()
			.header("Authorization", "Bearer eyJhbGciOiJIUzI1N")
				.when().log().all()
					.get("/contacts")
						.then().log().all()
							.assertThat()
								.statusCode(401);
		
		
	}
	
	
}
