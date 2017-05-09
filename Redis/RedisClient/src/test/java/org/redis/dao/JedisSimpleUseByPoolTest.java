package org.redis.dao;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wei11 on 2017/5/9.
 */
public class JedisSimpleUseByPoolTest {
    @Test
    public void setString() throws Exception {
        new JedisSimpleUseByPool().setString("test", "Hello");
        JedisSimpleUseByPool.destoryPool();

    }

    @Test
    public void getString() throws Exception {
        new JedisSimpleUseByPool().getString("test");
        JedisSimpleUseByPool.destoryPool();
    }

    @Test
    public void delString() throws Exception {
        new JedisSimpleUseByPool().delString("test");
        JedisSimpleUseByPool.destoryPool();
    }
}