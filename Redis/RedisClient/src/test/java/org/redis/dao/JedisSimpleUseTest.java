package org.redis.dao;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wei11 on 2017/5/9.
 */
public class JedisSimpleUseTest {
    @Test
    public void delString() throws Exception {
        new JedisSimpleUse().delString("test");
    }

    @Test
    public void setString() throws Exception {
        new JedisSimpleUse().setString("test", "Hello");
    }

    @Test
    public void getString() throws Exception {
        new JedisSimpleUse().getString("test");
    }

}