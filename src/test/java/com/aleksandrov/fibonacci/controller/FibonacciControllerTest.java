package com.aleksandrov.fibonacci.controller;

import com.aleksandrov.fibonacci.domain.enums.Status;
import com.aleksandrov.fibonacci.domain.model.FibonacciRequest;
import com.aleksandrov.fibonacci.domain.model.RestResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author DAleksandrov
 * @created 21.09.2022
 **/

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FibonacciControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void whenApiWork(){
        FibonacciRequest request = new FibonacciRequest(1, 1, 10);
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<RestResponse> response = restTemplate.exchange(
                createURLWithPort("/fibonacci/calculate"),
                HttpMethod.POST, new HttpEntity<>(request, headers), RestResponse.class
        );
        assertNotNull(response.getBody().getData());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getStatus(), Status.SUCCESS);
    }

    @Test
    void whenNumbersNegative(){
        FibonacciRequest request = new FibonacciRequest(-1, 1, 10);
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<RestResponse> response = restTemplate.exchange(
                createURLWithPort("/fibonacci/calculate"),
                HttpMethod.POST, new HttpEntity<>(request, headers), RestResponse.class
        );
        assertNull(response.getBody().getData());
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody().getStatus(), Status.ERROR);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
