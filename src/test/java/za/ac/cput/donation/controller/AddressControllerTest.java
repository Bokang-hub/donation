package za.ac.cput.donation.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.donation.entity.Address;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/auth/address";
    private static Address address;

    @Test
    @Disabled("No longer under auth")
    void a_save() {
        System.out.println("Initial Address: " + address);
        String url = BASE_URL + "/save/";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<Address> entity = new HttpEntity<>(address, headers);
        ResponseEntity<Address> postResponse = restTemplate.postForEntity(url, entity, Address.class, headers);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        address = postResponse.getBody();
        assertEquals("101", postResponse.getBody().getStreetNumber());
        System.out.println("Created: " + postResponse.getBody());
    }

    @Test
    void b_update() {
        Address update = new Address.Builder().copy(address).setPostalCode(5005).build();
        String url = BASE_URL + "/update/";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<Address> entity = new HttpEntity<>(update, headers);
        ResponseEntity<Address> response = restTemplate.exchange(url, HttpMethod.POST, entity, Address.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("Updated: " + response.getBody());
    }

    @Test
    void a_findById() {
        String id = "";
        String url = BASE_URL + "/find-id/" + id;
        System.out.println("URL: " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<Address> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Address> response = restTemplate.exchange(url, HttpMethod.GET, entity, Address.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        address = response.getBody();
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void c_findByStreetName() {
        String url = BASE_URL + "/find-name/" + address.getStreetName();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<Address> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("findByStreetName: " + response.getBody());
    }

}