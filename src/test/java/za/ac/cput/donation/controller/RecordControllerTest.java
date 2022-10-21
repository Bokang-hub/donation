package za.ac.cput.donation.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.donation.entity.Record;
import za.ac.cput.donation.factory.RecordFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecordControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/auth/record";
    private static Record record = RecordFactory.createRecord(15, "11/11/2021", "Some type", true);

    @Test
    void a_save() {
        String url = BASE_URL + "/save";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<Record> entity = new HttpEntity<>(record, headers);
        ResponseEntity<Record> postResponse = restTemplate.postForEntity(url, entity, Record.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        record = postResponse.getBody();
        assertEquals("11/11/2021", postResponse.getBody().getDate());
        System.out.println("Created: " + postResponse.getBody());
    }

    @Test
    @Disabled("No longer exist")
    void b_find() {
        String url = BASE_URL + "/find/" + record.getId();
        System.out.println("URL: " + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<Record> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Record> response = restTemplate.exchange(url, HttpMethod.GET, entity, Record.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void c_findAll() {
        String url = BASE_URL + "/find/all";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("azzo","athi");
        HttpEntity<Record> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("GetAll: " + response.getBody());
    }

    @Test
    @Disabled("Not needed")
    void d_update() {
        /** Record update = new Appointment.Builder().copy(appointment).setAppointmentType("Disability").build();
        String url = BASE_URL + "/update";
        ResponseEntity<Appointment> response = restTemplate.postForEntity(url, update, Appointment.class);
        assertNotNull(response);
        assertNotNull(response.getBody());
        System.out.println("Updated: " + response.getBody()); **/
    }

    @Test
    @Disabled("Not needed")
    void e_delete() {
        /** String url = BASE_URL + "/delete/" + appointment.getAppointmentId();
        restTemplate.delete(url);
        System.out.println("Deleted:" + appointment.getAppointmentId() +"\nAt "+url); **/
    }

}