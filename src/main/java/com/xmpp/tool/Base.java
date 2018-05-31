package com.xmpp.tool;

import com.alibaba.fastjson.JSON;
import com.xmpp.jedis.JedisManger;
import com.xmpp.open.entity.WxConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: dingchao
 * \* Date: 2018/5/30
 * \* Time: 上午9:07
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Base {

    protected Logger logger = LoggerFactory.getLogger(Base.class);


    protected static Map<String, Object> jsonToMap(String obj) throws Exception {
        Map maps = (Map) JSON.parse(obj);
        for (Object map : maps.entrySet()) {
            System.out.println(((Map.Entry) map).getKey() + "     " + ((Map.Entry) map).getValue());
        }
        return maps;

    }


    /**
     * 获取/重新获取 component_access_token
     * @throws Exception
     */
    public static void getAccessToken() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("component_appid", WxConfig.getInstance().getWxappid());
        map.put("component_appsecret", WxConfig.getInstance().getWxappsecret());
        String component_verify_ticket = JedisManger.getKey("ComponentVerifyTicket");
        if (component_verify_ticket == null) {
            component_verify_ticket = WxConfig.getInstance().getComponent_verify_ticket();
        }
        map.put("component_verify_ticket", component_verify_ticket);
        String result = Post.jsonPost("https://api.weixin.qq.com/cgi-bin/component/api_component_token", map);


        Map<String, Object> result2 = jsonToMap(result);
        /**
         * component_access_token的超时时间为2小时，这里设置为1小时50分钟
         */
        JedisManger.set("component_access_token", result2.get("component_access_token").toString(), 6600);
        WxConfig.getInstance().setComponent_access_token(result2.get("component_access_token").toString());
    }



    public static void getPreAuthCode() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("component_appid", WxConfig.getInstance().getWxappid());


        String component_access_token=JedisManger.getKey("component_access_token");

        if(component_access_token==null || ("").equals(component_access_token)){
            component_access_token=WxConfig.getInstance().getComponent_access_token();
        }

        String result = Post.jsonPost("https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token="+component_access_token, map);


        Map<String, Object> result2 = jsonToMap(result);
        /**
         * pre_auth_code的有效期为10分钟，默认9分钟更新一次
         */
        JedisManger.set("pre_auth_code", result2.get("pre_auth_code").toString(), 540);
        WxConfig.getInstance().setPre_auth_code(result2.get("pre_auth_code").toString());
    }


}
