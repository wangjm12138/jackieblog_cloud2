-- KEYS[1]: key, e.g., 'limit:signup:global:20231027'
-- ARGV[1]: expire time (unix timestamp)
-- ARGV[2]: daily limit

local current = redis.call('GET', KEYS[1])
-- If the key doesn't exist or is about to expire, we need to set the expire time
if current == false then
    -- First request of the day, set the count to 1 and set expire time
    redis.call('SET', KEYS[1], 1)
    redis.call('EXPIREAT', KEYS[1], ARGV[1])
    return 1
else
    local currentCount = tonumber(current)

    -- 先检查是否已经超过限制
    if currentCount >= tonumber(ARGV[2]) then
        return -currentCount  -- 返回负数表示已超限
    end
    -- Key exists, increment and check
    local newCount = redis.call('INCR', KEYS[1])
    return newCount
end