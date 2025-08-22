-- KEYS[1]: key for the IP limit (e.g., 'limit:signup:ip:192.168.1.1:20231027')
-- ARGV[1]: expire time (unix timestamp for tomorrow)
-- ARGV[2]: daily limit per IP

local current = redis.call('GET', KEYS[1])
if current == false then
    redis.call('SET', KEYS[1], 1)
    redis.call('EXPIREAT', KEYS[1], ARGV[1])
    return 1
else
    local currentCount = tonumber(current)

    -- 先检查是否已经超过限制
    if currentCount >= tonumber(ARGV[2]) then
        return -currentCount  -- 返回负数表示已超限
    end
    local newCount = redis.call('INCR', KEYS[1])
    return newCount
end