package com.jackie.blog.article.service;

import com.google.common.hash.Hashing;
import com.jackie.blog.article.utils.RateLimitResult;
import com.jackie.blog.article.utils.SlidingWindowRateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class MultiLevelRateLimitService {

    @Autowired
    private SlidingWindowRateLimiter rateLimiter;

    /**
     * 评论系统多维度限流
     */
    public RateLimitResult checkCommentRateLimit(String userId, String ip, String userAgent) {
        // 1. 用户ID限流：每分钟2次

        RateLimitResult userResult = rateLimiter.allowRequest(
                "limit:comment:user:" + userId, 60, 2
        );
        System.out.println("userResult:" + userResult);
        if (!userResult.isAllowed()) {
            return userResult;
        }

        // 2. IP地址限流：每小时50次
        RateLimitResult ipResult = rateLimiter.allowRequest(
                "limit:comment:ip:" + ip, 3600, 50
        );
        if (!ipResult.isAllowed()) {
            return ipResult;
        }

        // 3. 用户代理限流：防止自动化工具
        if (userAgent != null && userAgent.length() > 10) {
            String uaHash = Hashing.murmur3_32().hashString(userAgent, StandardCharsets.UTF_8).toString();
            RateLimitResult uaResult = rateLimiter.allowRequest(
                    "limit:comment:ua:" + uaHash, 300, 20  // 5分钟20次
            );
            if (!uaResult.isAllowed()) {
                return uaResult;
            }
        }

        // 返回最严格的剩余次数
        long minRemaining = Math.min(userResult.getRemainingRequests(),
                ipResult.getRemainingRequests());
        return new RateLimitResult(true, minRemaining, 0);
    }
}
