package hu.blzsaa.syscodeprofileservice;

import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
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
	void getStudentByIdShouldReturnTheStudentFromDb() {
		RestAssured.get("/students/{studentId}", "49acaf49-05a2-43c0-bf91-d12b2cfb126c")
			.then()
			.body("name", is("student-name"))
			.body("id", is("49acaf49-05a2-43c0-bf91-d12b2cfb126c"))
			.body("emailAddress", is("email-address"))
			.statusCode(200);
	}

	@Test
	void createStudentShouldSaveToDb() {
		Response post = RestAssured.given().contentType(ContentType.JSON).body("""
				{"name":"student-name2", "emailAddress":"email-address2"}
				""").post("/students");
		String location = post.header("Location");
		post.then().header("Location", Matchers.notNullValue()).statusCode(201);

		RestAssured.get(location)
			.then()
			.body("name", is("student-name2"))
			.body("id", Matchers.notNullValue())
			.body("emailAddress", is("email-address2"))
			.statusCode(200);
	}

}
