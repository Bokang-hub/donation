package za.ac.cput.donation.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.donation.entity.Donation;
import za.ac.cput.donation.factory.DonationFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DonationControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/auth/donation";
    private static Donation donation = DonationFactory.createDonation(8, "Hungry kids", "Food");

    @Test
    void a_save() {
        System.out.println("Initial donation: " + donation);
        String url = BASE_URL + "/save";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<Donation> entity = new HttpEntity<>(donation, headers);
        ResponseEntity<Donation> postResponse = restTemplate.postForEntity(url, entity, Donation.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        donation = postResponse.getBody();
        assertEquals("Food", postResponse.getBody().getDonationType());
        System.out.println("Created: " + postResponse.getBody());
    }

    @Test
    void b_findByDonor() {
        String url = BASE_URL + "/find-by/donor/" + donation.getDonorId();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<Donation> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("GetAll: " + response.getBody());
    }

    @Test
    void c_findByType() {
        String url = BASE_URL + "/find-by/type/" + donation.getDonationType();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<Donation> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("find by type: " + response.getBody());
    }
}