package za.ac.cput.donation.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.donation.entity.Student;
import za.ac.cput.donation.factory.StudentFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/student";

    private static Student student = StudentFactory.createStudent("Athi", "Zono", "a@t.com", true, "innoo");

    @Test
    void a_save() {
        System.out.println("Initial student: " + student);
        String url = BASE_URL + "/save";
        ResponseEntity<Student> postResponse = restTemplate.postForEntity(url, student, Student.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        student = postResponse.getBody();
        assertEquals("lour@gmail.com", postResponse.getBody().getEmail());
        System.out.println("Created: " + postResponse.getBody());
    }

    @Test
    void b_find() {
        String url = BASE_URL + "/find/" + student.getId();
        System.out.println("URL: " + url);
        ResponseEntity<Student> response = restTemplate.getForEntity(url, Student.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    @Disabled("for now")
    void c_delete() {
        String url = BASE_URL + "/delete/" + student.getId();
        restTemplate.delete(url);
        System.out.println("Deleted:" + student.getId() + "\nAt " + url);
    }

    @Test
    void d_update() {
        Student update = new Student.Builder().copy(student).setLastName("Prudy").build();
        String url = BASE_URL + "/update";
        ResponseEntity<Student> response = restTemplate.postForEntity(url, update, Student.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("Updated: " + response.getBody());
    }
}
