package com.xmpp.jedis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: dingchao
 * \* Date: 2018/5/30
 * \* Time: 上午10:07
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Component
@ConfigurationProperties(prefix = "boot.jedis")
public class JedisPoolManager {

    private  static String jhost;

    private static Integer jport;

    private static String jpassword;


    public static void setJhost(String jhost) {
        JedisPoolManager.jhost = jhost;
    }

    public static void setJport(Integer jport) {
        JedisPoolManager.jport = jport;
    }

    public static void setJpassword(String jpassword) {
        JedisPoolManager.jpassword = jpassword;
    }

    public static String getJhost() {
        return jhost;
    }

    public static Integer getJport() {
        return jport;
    }

    public static String getJpassword() {
        return jpassword;
    }

    private static redis.clients.jedis.JedisPool pool = null;

    static redis.clients.jedis.JedisPool getJPool(){
        if (pool == null) {
            redis.clients.jedis.JedisPoolConfig config = new redis.clients.jedis.JedisPoolConfig();
            //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            config.setMaxTotal(150);
            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(5);
            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMaxWaitMillis(1000 * 100);
            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(false);
            config.setTestOnReturn(true);
            pool = new redis.clients.jedis.JedisPool(config, jhost, jport);
        }
        return pool;
    }

    /**
     * jedis 授权密码登录
     * @return
     */
      static Jedis getJedis(){
        JedisPool pool=getJPool();
        Jedis jedis=pool.getResource();
        jedis.auth(jpassword);
        return jedis;
    }


}



