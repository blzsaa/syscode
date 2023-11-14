package hu.blzsaa.profileservice;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import hu.blzsaa.profileservice.student.StudentEntity;
import hu.blzsaa.profileservice.student.StudentRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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
		studentRepository.deleteAll();
	}

	@Test
	void getStudentByIdShouldReturnTheStudentFromDb() {
		StudentEntity saved = studentRepository
			.save(new StudentEntity(null, "student-name", "email.address2@domain.com"));

		RestAssured.get("/students/{studentId}", saved.getId())
			.then()
			.body("name", is("student-name"))
			.body("id", is(saved.getId().toString()))
			.body("emailAddress", is("email.address2@domain.com"))
			.statusCode(200);
	}

	@Test
	void createStudentShouldSaveToDb() {
		Response createStudentResponse = RestAssured.given().contentType(ContentType.JSON).body("""
				{"name":"student-name2", "emailAddress":"email.address2@domain.com"}
				""").post("/students");
		createStudentResponse.then().header("Location", Matchers.notNullValue()).statusCode(201);

		String location = createStudentResponse.header("Location");

		RestAssured.get(location)
			.then()
			.body("name", is("student-name2"))
			.body("id", Matchers.notNullValue())
			.body("emailAddress", is("email.address2@domain.com"))
			.statusCode(200);
	}

	@Test
	void deleteStudentShouldDeleteFromToDb() {
		// given
		Response createStudentResponse = RestAssured.given().contentType(ContentType.JSON).body("""
				{"name":"student-name2", "emailAddress":"email.address2@domain.com"}
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
				{"name":"student-name", "emailAddress":"email.address@domain.com"}
				""").post("/students");
		RestAssured.given().contentType(ContentType.JSON).body("""
				{"name":"student-name2", "emailAddress":"email.address2@domain.com"}
				""").post("/students");

		// when + then
		RestAssured.get("/students")
			.then()
			.body("size()", is(2))
			.body("findAll { it.name == 'student-name' }", hasSize(1))
			.body("findAll { it.name == 'student-name2' }", hasSize(1))
			.statusCode(200);
	}

	@Test
	void updateStudentShouldUpdateStudentInDb() {
		// given
		Response createStudentResponse = RestAssured.given().contentType(ContentType.JSON).body("""
				{"name":"student-name2", "emailAddress":"email.address2@domain.com"}
				""").post("/students");
		String location = createStudentResponse.header("Location");

		// when + then
		RestAssured.given()
			.contentType(ContentType.JSON)
			.body("""
					{"name":"student-name3", "emailAddress":"email.address3@domain.com"}
					""")
			.put(location)
			.then()
			.statusCode(200)
			.body("name", is("student-name3"))
			.body("id", Matchers.notNullValue())
			.body("emailAddress", is("email.address3@domain.com"))
			.statusCode(200);
	}

	@Test
	void emailAddressShouldBeValidDuringCreateStudentRequest() {
		Response createStudentResponse = RestAssured.given().contentType(ContentType.JSON).body("""
				{"name":"student-name2", "emailAddress":"malformed-email.address2"}
				""").post("/students");
		createStudentResponse.then().statusCode(400);
	}

}
