package com.xmpp.jedis;

import com.xmpp.tool.Base;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPubSub;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: dingchao
 * \* Date: 2018/5/30
 * \* Time: 下午2:40
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

@Component
public class RedisEventListener extends JedisPubSub{

    private final static String component_access_token="component_access_token";


    public RedisEventListener() {
    }

    /**
     * 改方法用来订阅expired，根据expired的key执行对应操作
     * 1.component_access_token过期后需要重新获取,调用getAccessToken方法
     * @param channel
     * @param message
     */
    @Override
    public void onMessage(String channel, String message) {

        try{

            System.out.println("过期的key为"+message);
            /**
             *
             */
        if(component_access_token.equals(message)){

            Base.getAccessToken();
        }
        else if("component_access_token".equals(message)){

        }}catch (Exception e){

        }
    }
}
