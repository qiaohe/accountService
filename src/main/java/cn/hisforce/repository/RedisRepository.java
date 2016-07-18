package cn.hisforce.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Johnson on 2016/7/18.
 */
@Component(value = "redisRepository")
public class RedisRepository {
    @Autowired
    private StringRedisTemplate template;

    public Long getNext(String key) {
        return template.opsForValue().increment(key, 1);
    }
}
