package za.ac.cput.donation.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.donation.entity.Address;
import za.ac.cput.donation.factory.AddressFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/address";
    private static Address address = AddressFactory.createAddress(55,"101","Pang",919);

    @Test
    void a_save() {
        System.out.println("Initial Address: " + address);
        String url = BASE_URL + "/save";
        ResponseEntity<Address> postResponse = restTemplate.postForEntity(url, address, Address.class);
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
        String url = BASE_URL + "/update";
        ResponseEntity<Address> response = restTemplate.postForEntity(url, update, Address.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("Updated: " + response.getBody());
    }

    @Test
    void c_findById() {
        String url = BASE_URL + "/find-id/" + address.getId();
        System.out.println("URL: " + url);
        ResponseEntity<Address> response = restTemplate.getForEntity(url, Address.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void d_findByStreetName() {
        String url = BASE_URL + "/find-name/" + address.getStreetName();
        HttpHeaders header = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, header);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("GetAll: " + response.getBody());
    }
}