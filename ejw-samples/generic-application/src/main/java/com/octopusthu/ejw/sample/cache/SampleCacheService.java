package com.octopusthu.ejw.sample.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SampleCacheService {

    private static final String cacheName = "sampleCache";

    private Random random = new Random();

    @Cacheable(value = {cacheName}, key = "#key")
    public Integer get(int key) {
        return random.nextInt();
    }

    @CacheEvict(value = {cacheName}, allEntries = true)
    public Boolean clearCache() {
        return true;
    }
}
