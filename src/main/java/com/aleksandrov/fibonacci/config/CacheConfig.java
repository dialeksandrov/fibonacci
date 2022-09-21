package com.aleksandrov.fibonacci.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author DAleksandrov
 * @created 21.09.2022
 **/
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new SimpleCacheManager();
    }

    @Bean
    @Qualifier("completedSequenceCache")
    public LoadingCache<String, String> getCompletedSequenceCache() {
        CacheLoader<String, String> loader = CacheLoader.from(o -> null);
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.HOURS)
                .build(loader);
        return loadingCache;
    }
}
