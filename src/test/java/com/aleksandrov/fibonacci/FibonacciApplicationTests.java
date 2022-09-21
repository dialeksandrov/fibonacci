package com.aleksandrov.fibonacci;

import com.aleksandrov.fibonacci.domain.model.FibonacciRequest;
import com.aleksandrov.fibonacci.domain.model.RestResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class FibonacciApplicationTests {

    @Test
    void contextLoads() {
    }

}
