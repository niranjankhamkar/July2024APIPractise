package POSTwithAuthAPI;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Credentials1;


public class AuthAPITest {
	
	@Test
	public void getAuthTokenTest_WithJSON() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenId = given()
			.contentType(ContentType.JSON)
			.body("{\n"
					+ "    \"username\" : \"admin\",\n"
					+ "    \"password\" : \"password123\"\n"
					+ "}")
			.when()
				.post("/auth")
					.then()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
								
		System.out.println("tokenId ==>" + tokenId);
		Assert.assertNotNull(tokenId);
		
	}
	
	
	@Test
	public void getAuthTokenTest_WithJSONFILE() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenId = given().log().all()
			.contentType(ContentType.JSON)
			.body(new File("./src/test/resources1/jsons/auth.json"))
			.when().log().all()
				.post("/auth")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
								
		System.out.println("tokenId ==>" + tokenId);
		Assert.assertNotNull(tokenId);
		
	}
	
	// add dependancy of jackson databind maven
	@Test
	public void getAuthTokenTest_WithPOJOClass() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		Credentials1 cred = new Credentials1("admin", "password123");
		
		String tokenId = given().log().all()
			.contentType(ContentType.JSON)
			.body(cred) //pojo to json: serialization: ObjectMapper(Jackson)
			.when().log().all()
				.post("/auth")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
								
		System.out.println("tokenId ==>" + tokenId);
		Assert.assertNotNull(tokenId);
		
		//json ---> pojo: De-serialization
				
	}

}