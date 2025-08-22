-- KEYS[1]: 限流key
-- ARGV[1]: 时间窗口（秒）
-- ARGV[2]: 最大请求数
-- ARGV[3]: 当前时间戳（可选）

local key = KEYS[1]
local window = tonumber(ARGV[1])
local maxRequests = tonumber(ARGV[2])
local currentTime

if ARGV[3] then
    currentTime = tonumber(ARGV[3])
else
    local time = redis.call('TIME')
    currentTime = tonumber(time[1]) + tonumber(time[2]) / 1000000
end

local windowStart = currentTime - window

-- 1. 清理过期请求（窗口外的数据）
redis.call('ZREMRANGEBYSCORE', key, 0, windowStart)

-- 2. 获取当前窗口内的请求数量
local currentCount = redis.call('ZCARD', key)

if currentCount >= maxRequests then
    -- 3. 超过限制，计算最早请求的时间
    local oldestRequests = redis.call('ZRANGE', key, 0, 0, 'WITHSCORES')
    if oldestRequests and #oldestRequests >= 0 then
        local oldestTime = tonumber(oldestRequests[2])
        local retryAfter = math.ceil(window - (currentTime - oldestTime))
        return {0, currentCount, retryAfter}
    end
    return {0, currentCount, window}
else
    -- 4. 在限制内，添加当前请求
    redis.call('ZADD', key, currentTime, currentTime)

    -- 5. 设置过期时间（避免内存泄漏）
    local ttl = redis.call('TTL', key)
    if ttl == -1 then  -- 没有设置过期时间
        redis.call('EXPIRE', key, window + 60)  -- 额外多60秒缓冲
    end

    return {1, maxRequests - currentCount - 1, 0}
end