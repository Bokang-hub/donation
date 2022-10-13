package za.ac.cput.donation.controller;
//
//* author: Lukhona Tetyana
//* student number:218119321
//* ADP3 Capstone Project
//* Group 6
//

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.donation.entity.Parcel;
import za.ac.cput.donation.factory.ParcelFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParcelControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/auth/parcel";
    private static Parcel parcel = ParcelFactory.createParcel(99, "Groceries","2020-11-11","Food",true);

    @Test
    void a_save() {
        String url = BASE_URL + "/save";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<Parcel> entity = new HttpEntity<>(parcel, headers);
        ResponseEntity<Parcel> postResponse = restTemplate.postForEntity(url, entity, Parcel.class, headers);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        parcel = postResponse.getBody();
        assertEquals("Food", postResponse.getBody().getType());
        System.out.println("Created: " + postResponse.getBody());
    }

    @Test
    void b_find() {
        String url = BASE_URL + "/find/" + parcel.getId();
        System.out.println("URL: " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<Parcel> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Parcel> response = restTemplate.exchange(url, HttpMethod.GET, entity, Parcel.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void d_findAll() {
        String url = BASE_URL + "/find/all";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<Parcel> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("GetAll: " + response.getBody());
    }

    @Test
    void c_update() {
        Parcel update = new Parcel.Builder().copy(parcel).setType("Money").build();
        String url = BASE_URL + "/update";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<Parcel> entity = new HttpEntity<>(update, headers);
        ResponseEntity<Parcel> response = restTemplate.postForEntity(url, entity, Parcel.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("Updated: " + response.getBody());
    }
}