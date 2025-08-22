package com.jackie.blog.article.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class SlidingWindowRateLimiter {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final DefaultRedisScript<List> SLIDING_WINDOW_SCRIPT = new DefaultRedisScript<>();

    static {
        SLIDING_WINDOW_SCRIPT.setLocation(new ClassPathResource("scripts/SlidingWindowRateLimiter.lua"));
        SLIDING_WINDOW_SCRIPT.setResultType(List.class);
    }

    /**
     * 滑动窗口限流
     */
    public RateLimitResult allowRequest(String key, int windowSeconds, int maxRequests) {
        try {
            List<Long> result = redisTemplate.execute(
                    SLIDING_WINDOW_SCRIPT,
                    Collections.singletonList(key),
                    String.valueOf(windowSeconds),
                    String.valueOf(maxRequests)
            );
            if (result != null && result.size() >= 3) {
                boolean allowed = result.get(0) == 1;
                long remaining = result.get(1);
                long retryAfter = result.get(2);
                return new RateLimitResult(allowed, remaining, retryAfter);
            }
        } catch (Exception e) {
            log.error("限流检查失败: {}", e.getMessage());
            // 降级策略：在Redis故障时允许请求
            return new RateLimitResult(true, maxRequests, 0);
        }
        return new RateLimitResult(false, 0, windowSeconds);
    }
}
