package com.aleksandrov.fibonacci.service;

import com.aleksandrov.fibonacci.domain.model.FibonacciRequest;

import java.util.concurrent.ExecutionException;

/**
 * @author DAleksandrov
 * @created 21.09.2022
 **/
public interface FibonacciService {

    String getFibonacciSequence(FibonacciRequest request) throws ExecutionException;

}
