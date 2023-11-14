package hu.blzsaa.addressservice;

import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import io.restassured.RestAssured;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class AddressServiceIT {

	@BeforeAll
	static void beforeAll(@LocalServerPort int serverPort) {
		RestAssured.baseURI = "http://localhost:" + serverPort;
	}

	@Test
	void requiresBasicAuth() {
		UUID uuid = UUID.randomUUID();
		RestAssured.given()
			.auth()
			.basic("admin", "admin")
			.get("/addresses/{addressId}", uuid.toString())
			.then()
			.body("address", is("123 Fake Street"))
			.body("id", is(uuid.toString()))
			.statusCode(200);
	}

	@Test
	void requiresBasicAuthThrows401WhenUserIsUnauthenticated() {
		UUID uuid = UUID.randomUUID();
		RestAssured.given()
			.auth()
			.basic("admin", "wrong-password")
			.get("/addresses/{addressId}", uuid.toString())
			.then()
			.statusCode(401);
	}

}
