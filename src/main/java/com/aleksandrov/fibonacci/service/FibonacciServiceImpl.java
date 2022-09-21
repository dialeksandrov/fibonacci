package com.aleksandrov.fibonacci.service;

import com.aleksandrov.fibonacci.domain.model.FibonacciRequest;
import com.aleksandrov.fibonacci.service.FibonacciService;
import com.aleksandrov.fibonacci.util.FibonacciUtil;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

/**
 * @author DAleksandrov
 * @created 21.09.2022
 **/
@Service
public class FibonacciServiceImpl implements FibonacciService {

    private final LoadingCache<String, String> completedSequenceCache;

    public FibonacciServiceImpl(@Qualifier("completedSequenceCache")LoadingCache<String, String> completedSequenceCache) {
        this.completedSequenceCache = completedSequenceCache;
    }

    @Override
    public String getFibonacciSequence(FibonacciRequest request) throws ExecutionException {
        String cacheKey = getCacheKey(request.getFirstNumber(), request.getSecondNumber(), request.getRange());
        String fromCache = checkInCache(cacheKey);
        if (fromCache != null){
            return fromCache;
        }
        String result = FibonacciUtil.calculateFibonacciSequence(request.getFirstNumber(), request.getSecondNumber(), request.getRange()).trim();
        completedSequenceCache.put(cacheKey, result);
        return result;
    }

    private String checkInCache(String key) throws ExecutionException {
        return completedSequenceCache.getIfPresent(key) == null ? null : completedSequenceCache.get(key);
    }

    private String getCacheKey(int firstNumber, int secondNumber, int rangeNumber){
        return String.format("%d%d%d",firstNumber, secondNumber, rangeNumber);
    }
}
