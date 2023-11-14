package hu.blzsaa.syscodeprofileservice;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import hu.blzsaa.syscodeprofileservice.student.StudentRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.UUID;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ProfileServiceIT {

	@Autowired
	private StudentRepository studentRepository;

	@BeforeAll
	static void beforeAll(@LocalServerPort int serverPort) {
		RestAssured.baseURI = "http://localhost:" + serverPort;
	}

	@AfterEach
	void tearDown() {
		studentRepository.findAll()
			.stream()
			.filter(a -> a.getId().equals(UUID.fromString("49acaf49-05a2-43c0-bf91-d12b2cfb126c")))
			.forEach(studentRepository::delete);
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
		Response createStudentResponse = RestAssured.given().contentType(ContentType.JSON).body("""
				{"name":"student-name2", "emailAddress":"email-address2"}
				""").post("/students");
		String location = createStudentResponse.header("Location");
		createStudentResponse.then().header("Location", Matchers.notNullValue()).statusCode(201);

		RestAssured.get(location)
			.then()
			.body("name", is("student-name2"))
			.body("id", Matchers.notNullValue())
			.body("emailAddress", is("email-address2"))
			.statusCode(200);
	}

	@Test
	void deleteStudentShouldDeleteFromToDb() {
		// given
		Response createStudentResponse = RestAssured.given().contentType(ContentType.JSON).body("""
				{"name":"student-name2", "emailAddress":"email-address2"}
				""").post("/students");
		String location = createStudentResponse.header("Location");

		// when
		RestAssured.delete(location).then().statusCode(204);

		// then
		RestAssured.get(location).then().statusCode(500);
	}

	@Test
	void listStudentShouldReturnAllStudentaFromDb() {
		// given
		RestAssured.given().contentType(ContentType.JSON).body("""
				{"name":"student-name2", "emailAddress":"email-address2"}
				""").post("/students");

		// when + then
		RestAssured.get("/students")
			.then()
			.body("size()", is(2))
			.body("findAll { it.name == 'student-name' }", hasSize(1))
			.body("findAll { it.name == 'student-name2' }", hasSize(1))
			.statusCode(200);
	}

}
