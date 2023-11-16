package hu.blzsaa.profileservice;

import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("it")
class AddressServiceIT {

	private static final WireMockServer ADDRESS_SERVICE_MOCK = new WireMockServer(options().port(8089));

	@BeforeAll
	static void beforeAll(@LocalServerPort int serverPort) {
		RestAssured.baseURI = "http://localhost:" + serverPort;
	}

	@BeforeEach
	void setUp() {
		ADDRESS_SERVICE_MOCK.start();
	}

	@AfterEach
	void tearDown() {
		ADDRESS_SERVICE_MOCK.stop();
	}

	@Test
	void callingForAddressShouldCallAddressServiceWithBasicAuth() {
		UUID uuid = UUID.fromString("71264f93-349b-4924-a750-e61df1dc2a00");

		ADDRESS_SERVICE_MOCK.stubFor(WireMock.get(urlPathEqualTo("/addresses/" + uuid))
			.withBasicAuth("admin", "admin")
			.willReturn(WireMock.jsonResponse("""
					{"id":"71264f93-349b-4924-a750-e61df1dc2a00", "address":"address"}
					""", 200)));

		RestAssured.get("/addresses/{addressId}", uuid)
			.then()
			.body("address", is("address"))
			.body("id", is(uuid.toString()))
			.statusCode(200);
	}

	@Test
	void callingForAddressShouldReturnWithProblemDetailIfAnExceptionOccurs() {
		UUID uuid = UUID.fromString("71264f93-349b-4924-a750-e61df1dc2a00");

		ADDRESS_SERVICE_MOCK.stubFor(WireMock.get(urlPathEqualTo("/addresses/" + uuid))
			.withBasicAuth("admin", "admin")
			.willReturn(WireMock.forbidden()));

		RestAssured.get("/addresses/{addressId}", uuid)
			.then()
			.body("title", is("Forbidden"))
			.body("detail", is("403 Forbidden: [no body]"))
			.body("instance", is("/addresses/71264f93-349b-4924-a750-e61df1dc2a00"))
			.statusCode(403);
	}

}
