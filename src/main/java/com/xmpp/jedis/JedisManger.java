package com.xmpp.jedis;

import redis.clients.jedis.Jedis;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: dingchao
 * \* Date: 2018/5/30
 * \* Time: 上午10:46
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class JedisManger {


    /**
     * 封装jedis，set方法，无超时时间
     */
    public static void set(String key,String value){
        Jedis jedis=JedisPoolManager.getJedis();
        jedis.set(key,value);
        jedis.close();
    }

    /**
     * 封装set string 设置超时时间
     * @param key 键
     * @param value 值
     * @param timeout 超时时间（s）
     */
    public static void set(String key,String value,int timeout){
        Jedis jedis=JedisPoolManager.getJedis();
        jedis.set(key,value);
        jedis.expire(key,timeout);
        jedis.close();

    }


    public static String getKey(String key){
        Jedis jedis=JedisPoolManager.getJedis();
        String result=jedis.get(key);
        jedis.close();
        return result;
    }


    /**
     * list添加方法
     */
    public  static  void addListItem(String key,String... value){
        Jedis jedis=JedisPoolManager.getJedis();
        jedis.lpush(key,value);
        jedis.close();
    }

    /**
     * list添加方法 带有超时时间
     * @param key key
     * @param timeout 超时时间
     * @param value value
     */
    public  static  void addListItem(String key,int timeout,String... value){
        Jedis jedis=JedisPoolManager.getJedis();
        jedis.lpush(key,value);
        jedis.expire(key,timeout);
        jedis.close();
    }




}
