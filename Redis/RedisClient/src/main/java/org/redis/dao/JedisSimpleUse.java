package org.redis.dao;

import redis.clients.jedis.Jedis;

/**
 * Created by wei11 on 2017/5/9.
 *
 */
public class JedisSimpleUse {
    static Jedis jedis = new Jedis("localhost", 6379);

    public void setString(String key, String value){
        String result;
        result = jedis.set(key, value);
        System.out.println("result: " + result);
    }

    public String getString(String key){
        String result;
        result = jedis.get(key);
        System.out.println("result: " + result);
        return result;
    }

    public void delString(String key){
        long result;
        result = jedis.del(key);
        System.out.println("result: " + result);
    }
}
