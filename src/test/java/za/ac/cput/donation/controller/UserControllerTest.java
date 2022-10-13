package za.ac.cput.donation.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.donation.entity.Donor;
import za.ac.cput.donation.entity.Student;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/user";

    @Test
    void a_find() {
        String id = "9";
        String url = BASE_URL + "/find/" + id;
        HttpHeaders header = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, header);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("find by type: " + response.getBody());
    }

    @Test
    void b_loginDonor() {
        String email = "ok@nd.com";
        String password = "qaqaqa";
        String url = BASE_URL + "/login/donor/" + email + "/" + password;
        ResponseEntity<Donor> response = restTemplate.getForEntity(url, Donor.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("found: " + response.getBody());
    }

    @Test
    void c_login() {
        String email = "a@t.com";
        String password = "innoo";
        String url = BASE_URL + "/login/student/"  + email + "/" + password;
        ResponseEntity<Student> response = restTemplate.getForEntity(url, Student.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("found: " + response.getBody());
    }
}