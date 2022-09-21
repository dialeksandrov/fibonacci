package com.aleksandrov.fibonacci;

import com.aleksandrov.fibonacci.controller.FibonacciController;
import com.aleksandrov.fibonacci.service.FibonacciService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FibonacciApplicationTests {

    @Autowired
    private FibonacciController controller;
    @Autowired
    private FibonacciService fibonacciService;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
        assertThat(fibonacciService).isNotNull();
    }

}
