package za.ac.cput.donation.controller;
//
//* Author:Lukhona Tetyana
//* Student number: 218119321
//* ADP3 Capstone Project
//*
//

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.donation.entity.ParcelRequest;
import za.ac.cput.donation.factory.ParcelRequestFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParcelRequestControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/auth/request";
    private static ParcelRequest parcelRequest = ParcelRequestFactory.createParcelRequest("2021-01-01", 125, "Money", false);

    @Test
    void a_save() {
        String url = BASE_URL + "/save";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<ParcelRequest> entity = new HttpEntity<>(parcelRequest, headers);
        ResponseEntity<ParcelRequest> postResponse = restTemplate.postForEntity(url, entity, ParcelRequest.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        parcelRequest = postResponse.getBody();
        assertEquals(125, postResponse.getBody().getStudentId());
        System.out.println("Created: " + postResponse.getBody());
    }

    @Test
    void b_find() {
        String url = BASE_URL + "/find/" + parcelRequest.getId();
        System.out.println("URL: " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<ParcelRequest> entity = new HttpEntity<>(null, headers);
        ResponseEntity<ParcelRequest> response = restTemplate.exchange(url, HttpMethod.GET, entity, ParcelRequest.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void c_findAll() {
        String url = BASE_URL + "/find/all";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<ParcelRequest> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("GetAll: " + response.getBody());
    }

    @Test
    @Disabled("for now")
    void d_delete() {
        String url = BASE_URL + "/delete/" + parcelRequest.getId();
        restTemplate.delete(url);
        System.out.println("Deleted:" + parcelRequest.getId() +"\nAt "+url);
    }
}