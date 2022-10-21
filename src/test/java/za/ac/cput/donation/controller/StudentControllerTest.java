package za.ac.cput.donation.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.donation.entity.Student;
import za.ac.cput.donation.factory.StudentFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/auth/student";

    private static Student student = StudentFactory.createStudent("Athi", "Zono", "a@t.com", true, "innoo");

    @Test
    @Disabled("not under auth route anymore")
    void a_save() {
        System.out.println("Initial student: " + student);
        String url = BASE_URL + "/save";
        ResponseEntity<Student> postResponse = restTemplate.postForEntity(url, student, Student.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        student = postResponse.getBody();
        assertEquals("a@t.com", postResponse.getBody().getEmail());
        System.out.println("Created: " + postResponse.getBody());
    }

    @Test
    void b_find() {
        String id = "17";
        String url = BASE_URL + "/find/" + id;
        System.out.println("URL: " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<Student> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Student> response = restTemplate.exchange(url, HttpMethod.GET, entity, Student.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        student = response.getBody();
        System.out.println("Read: " + response.getBody());
    }

    @Test
    @Disabled("disabled for now")
    void c_delete() {
        String url = BASE_URL + "/delete/" + student.getId();
        restTemplate.delete(url);
        System.out.println("Deleted:" + student.getId() +"\nAt "+url);
    }

    @Test
    void d_update() {
        Student update = new Student.Builder().copy(student).setLastName("Prudy").build();
        String url = BASE_URL + "/update";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<Student> entity = new HttpEntity<>(update, headers);
        ResponseEntity<Student> response = restTemplate.postForEntity(url, entity, Student.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("Updated: " + response.getBody());
    }
}