package hu.blzsaa.syscodeprofileservice;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ProfileServiceIT {

	@BeforeAll
	static void beforeAll(@LocalServerPort int serverPort) {
		RestAssured.baseURI = "http://localhost:" + serverPort;
	}

	@Test
	void openApiGeneratedSkeletonShouldReturnsWith501() {
		RestAssured.get("/students").then().statusCode(501);
	}

}
