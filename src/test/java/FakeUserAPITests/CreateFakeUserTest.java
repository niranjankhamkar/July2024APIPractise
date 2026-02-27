package FakeUserAPITests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import FakeUserAPITests.User.Address.GeoLocation;
import io.restassured.RestAssured;


public class CreateFakeUserTest {
	
	
	@Test
	public void createUserTest_LombokPojo() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		//GeoLocation geoLocation1 = new GeoLocation("-98.900", "987.999");

		
		User.Address.GeoLocation geoLocation = new User.Address.GeoLocation("-98.900", "987.999");
		
		User.Address address = new User.Address("Bangalore", "new road", 9090, "98789", geoLocation);
		
		User.Name name = new User.Name("Sonia", "Sharma");
		
		User user = new User("sonia@gmail.com", "soniasharma", "sonia@123", "9898-9090-987", name, address);
		
		given().log().all()
			.body(user)
			.when()
				.post("/users")
					.then().log().all()
						.assertThat()
							.statusCode(200);
					
	}
	
	
	@Test
	public void createUserTest_LombokBuilder() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		
		User.Address.GeoLocation geoLocation =	new User.Address.GeoLocation.GeoLocationBuilder()
														.lat("-90.899")
															.longitude("876.33")
																	.build();
		
		
		User.Address address = new User.Address.AddressBuilder()
											.city("Pune")
												.street("old road")
													.number(123)
														.zipcode("98989")
															.geoLocation(geoLocation)	
																.build();
		
		User.Name name = new User.Name.NameBuilder()
								.firstname("Piyush")
										.lastname("Sharma")
											.build();
		
		
		User user = new User.UserBuilder()
							.email("piyush@gmail.com")
								.password("piyush@123")
									.phone("9876-090-987")
										.username("piyusapi")
											.name(name)
												.address(address)
													.build();
		
		
		given().log().all()
			.body(user)
			.when()
				.post("/users")
					.then().log().all()
						.assertThat()
							.statusCode(200);
					
	}
	
}
