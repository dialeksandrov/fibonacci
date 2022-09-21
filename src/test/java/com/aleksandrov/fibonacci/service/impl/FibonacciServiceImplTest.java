package com.aleksandrov.fibonacci.service.impl;

import com.aleksandrov.fibonacci.domain.model.FibonacciRequest;
import com.aleksandrov.fibonacci.service.FibonacciService;
import com.google.common.cache.LoadingCache;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author DAleksandrov
 * @created 21.09.2022
 **/
@ExtendWith(MockitoExtension.class)
public class FibonacciServiceImplTest {

    public static final String RIGHT_RESULT = "First number: 1, Second number: 1, Sequence: 2 3 5 8 13 21 34 55 89 144";

    private FibonacciService fibonacciService;
    @Mock
    @Qualifier("completedSequenceCache")
    private LoadingCache<String, String> completedSequenceCache;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        fibonacciService = new FibonacciServiceImpl(completedSequenceCache);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void whenResultIsNotNull() throws ExecutionException {
        String result = fibonacciService.getFibonacciSequence(new FibonacciRequest(1, 1, 10));
        assertNotNull(result);
    }

    @Test
    void whenResultIsRight() throws ExecutionException {
        String result = fibonacciService.getFibonacciSequence(new FibonacciRequest(1, 1, 10));
        assertEquals(result, RIGHT_RESULT);
    }
}