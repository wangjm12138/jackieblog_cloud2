package com.jackie.blog.article.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RateLimitResult {
    private boolean allowed;          // 是否允许请求
    private long remainingRequests;   // 剩余请求次数
    private long retryAfterSeconds;   // 重试等待时间（秒）

    public String getMessage() {
        if (allowed) {
            return String.format("剩余 %d 次请求", remainingRequests);
        } else {
            return String.format("限流中，请 %d 秒后重试", retryAfterSeconds);
        }
    }
}
