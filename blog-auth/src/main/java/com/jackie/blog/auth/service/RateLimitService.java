package com.jackie.blog.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Collections;

@Service
public class RateLimitService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate; // Inject configured RedisTemplate

    // Load the Lua script
    private static final DefaultRedisScript<Long> GLOBAL_LIMIT_SCRIPT;
    private static final DefaultRedisScript<Long> PER_IP_LIMIT_SCRIPT;

    static {
        GLOBAL_LIMIT_SCRIPT = new DefaultRedisScript<>();
        GLOBAL_LIMIT_SCRIPT.setLocation(new ClassPathResource("scripts/limit_global.lua"));
        GLOBAL_LIMIT_SCRIPT.setResultType(Long.class);

        PER_IP_LIMIT_SCRIPT = new DefaultRedisScript<>();
        PER_IP_LIMIT_SCRIPT.setLocation(new ClassPathResource("scripts/limit_per_ip.lua"));
        PER_IP_LIMIT_SCRIPT.setResultType(Long.class);
    }

    /**
     * 计算明天零点的时间戳（UTC）
     */
    private long calculateExpireAtTimestamp() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        return tomorrow.atStartOfDay().toInstant(ZoneOffset.UTC).getEpochSecond();
    }

    /**
     * 检查单个IP今日操作次数是否超过限制
     * @param bizType 业务类型 "signup" 或 "login"
     * @param identifier 标识符 (IP地址、用户ID等)
     * @param limit 单个标识符每日尝试次数限制
     * @return true if allowed, false if exceeded
     */
    public boolean isOperationAllowedForIdentifier(String bizType, String identifier, long limit) {
        String today = LocalDate.now().toString();
        String key = String.format("limit:%s:%s:%s", bizType, identifier, today); // e.g., limit:login:ip:192.168.1.1:2023-10-27
        long expireAt = calculateExpireAtTimestamp();

        Long currentCount = redisTemplate.execute(
                PER_IP_LIMIT_SCRIPT,
                Collections.singletonList(key),
                expireAt, limit
        );
        return currentCount != null && currentCount <= limit && currentCount > 0;
    }

    /**
     * 检查全局操作是否超过每日限制
     * @param bizType 业务类型 "signup" 或 "login"
     * @param limit 每日限制数
     * @return true if allowed, false if exceeded
     */
    public boolean isGlobalOperationAllowed(String bizType, long limit) {
        String today = LocalDate.now().toString();
        String key = String.format("limit:%s:global:%s", bizType, today); // e.g., limit:signup:global:2023-10-27
        long expireAt = calculateExpireAtTimestamp();

        Long currentCount = redisTemplate.execute(
                GLOBAL_LIMIT_SCRIPT,
                Collections.singletonList(key),
                expireAt, limit
        );
        return currentCount != null && currentCount <= limit;
    }
}
