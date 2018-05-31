package com.xmpp.jedis;

import com.xmpp.tool.Base;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import sun.nio.ch.Net;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: dingchao
 * \* Date: 2018/5/30
 * \* Time: 下午2:55
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

@Component
public class JedisTest  implements InitializingBean{


    @Override
    public void afterPropertiesSet() throws Exception {

        new Thread(new Runnable() {
            @Override
            public void run() {


                System.out.println("开始监听");
                RedisEventListener redisEventListener=new RedisEventListener();

                Jedis jedis2=JedisPoolManager.getJedis();

                jedis2.subscribe(redisEventListener,"__keyevent@0__:expired");
            }
        }).start();

    }


}
