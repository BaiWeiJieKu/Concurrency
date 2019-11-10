package com.mmall.concurrency.example.cache;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**Redis服务器
 * http://redis.cn/
 * @author Administrator
 */
@Component
public class RedisClient {

    @Resource(name = "redisPool")
    private JedisPool jedisPool;

    /**存储
     * @param key 键
     * @param value 值
     * @throws Exception
     */
    public void set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**获取
     * @param key 键
     * @return 键对应的值
     * @throws Exception
     */
    public String get(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
