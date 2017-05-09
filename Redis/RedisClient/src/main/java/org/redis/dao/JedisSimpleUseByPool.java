package org.redis.dao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by wei11 on 2017/5/9.
 * 使用JedisPool操作Redis
 */
public class JedisSimpleUseByPool {

//    static JedisPool pool = new JedisPool("localhost", 6379);
    static JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");

    // 使用普通的try-catch语句
    public void setString(String key, String value){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = pool.getResource();
            result = jedis.set(key, value);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (jedis != null){
                jedis.close();
            }
        }
        System.out.println("result: " + result);
    }

    // 使用Java7之后出现的 try-with-resource
    public String getString(String key){
        String result = null;
        try (Jedis jedis = pool.getResource()) {
            result = jedis.get(key);
            System.out.println("result: " + result);
            return result;
        }
    }

    public void delString(String key){
        long result;
        try (Jedis jedis = pool.getResource()) {
            result = jedis.del(key);
            System.out.println("result: " + result);
        }
    }

    public static void destoryPool(){
        pool.destroy();
    }

}
