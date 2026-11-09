package com.kraemer.rate_limiting_bucket4j;

import java.time.Duration;

import org.springframework.stereotype.Service;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.redis.lettuce.cas.LettuceBasedProxyManager;

@Service
public class RateLimitService {
    
    private final LettuceBasedProxyManager<String> proxyManager;

    public RateLimitService(LettuceBasedProxyManager<String> proxyManager) {
        this.proxyManager = proxyManager;
    }

    public Bucket resolveBucket(String key) {
        BucketConfiguration configuration = BucketConfiguration.builder()
        .addLimit(limit -> limit.capacity(2).refillGreedy(2, Duration.ofMinutes(1)))
        .build();

        return proxyManager.getProxy(key, () -> configuration);
    }
}
