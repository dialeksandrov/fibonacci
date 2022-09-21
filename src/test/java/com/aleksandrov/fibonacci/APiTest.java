package com.aleksandrov.fibonacci;

import com.aleksandrov.fibonacci.domain.enums.Status;
import com.aleksandrov.fibonacci.domain.model.FibonacciRequest;
import com.aleksandrov.fibonacci.domain.model.RestResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author DAleksandrov
 * @created 21.09.2022
 **/

public class APiTest {

    @Test
    void whenApiWork(){
        FibonacciRequest request = new FibonacciRequest(1, 1, 10);
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<RestResponse> response = testRestTemplate.exchange(
                createURLWithPort("/fibonacci/calculate"),
                HttpMethod.POST, new HttpEntity<>(request, headers), RestResponse.class
        );
        assertNotNull(response.getBody().getData());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getStatus(), Status.SUCCESS);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:8080" + uri;
    }
}
