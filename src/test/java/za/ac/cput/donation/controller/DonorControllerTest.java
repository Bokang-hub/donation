package za.ac.cput.donation.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.donation.entity.Donor;
import za.ac.cput.donation.factory.DonorFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DonorControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/auth/donor";
    private static Donor donor = DonorFactory.createDonor("Mike", "Tose", "mt@abc.io","qwerty");

    @Test
    @Disabled("no longer under auth")
    void a_save() {
        System.out.println("Initial donor: " + donor);
        String url = BASE_URL + "/save";
        ResponseEntity<Donor> postResponse = restTemplate.postForEntity(url, donor, Donor.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        donor = postResponse.getBody();
        assertEquals("Tose", postResponse.getBody().getLastName());
        System.out.println("Created: " + postResponse.getBody());
    }

    @Test
    void b_find() {
        String id = "1";
        String url = BASE_URL + "/find/" + id;
        System.out.println("URL: " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<Donor> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Donor> response = restTemplate.exchange(url, HttpMethod.GET, entity, Donor.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        donor = response.getBody();
        System.out.println("Read: " + response.getBody());
    }

    @Test
    @Disabled("Not needed")
    void c_findAll() {
        String url = BASE_URL + "/find/all";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<Donor> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("GetAll: " + response.getBody());
    }

    @Test
    void d_update() {
         Donor update = new Donor.Builder().copy(donor).setLastName("Jesus").build();
         String url = BASE_URL + "/update";
         HttpHeaders headers = new HttpHeaders();
         headers.setBasicAuth("azzo","athi");
         HttpEntity<Donor> entity = new HttpEntity<>(update, headers);
         ResponseEntity<Donor> response = restTemplate.postForEntity(url, entity, Donor.class);
         assertNotNull(response);
         assertNotNull(response.getBody());
         System.out.println("Updated: " + response.getBody());
    }

    @Test
    @Disabled("Not needed")
    void e_delete() {
         String url = BASE_URL + "/delete/" + donor.getId();
         restTemplate.delete(url);
         System.out.println("Deleted:" + donor.getId() +"\nAt "+url);
    }
}