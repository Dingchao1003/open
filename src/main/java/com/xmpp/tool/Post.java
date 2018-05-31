package com.xmpp.tool;



import com.alibaba.fastjson.JSON;
import org.kopitubruk.util.json.JSONUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: dingchao
 * \* Date: 2018/5/25
 * \* Time: 下午1:12
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Post {


    public static String jsonPost(String strURL, Map<String, String> params) {
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(JSONUtil.toJSON(params));
            out.flush();
            out.close();

            int code = connection.getResponseCode();
            InputStream is = null;
            if (code == 200) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }

            // 读取响应
            int length = (int) connection.getContentLength();// 获取长度
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                String result = new String(data, "UTF-8"); // utf-8编码
                System.out.println(result);
                return result;
            }

        } catch (IOException e) {
        }
        return "error"; // 自定义错误信息
    }

    public static Map<String,Object> tomap(String obj){
        Map maps = (Map)JSON.parse(obj);
        System.out.println("这个是用JSON类来解析JSON字符串!!!");
        for (Object map : maps.entrySet()){
            System.out.println(((Map.Entry)map).getKey()+"     " + ((Map.Entry)map).getValue());
        }
        return maps;

    }

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<String, String>();
//        map.put("component_appid", "wx7fc8a7ed1e394ab8");
//        map.put("component_appsecret", "05dd18043c6c9ef4f58f13f3e06dcf4f");
//        map.put("component_verify_ticket","sdadsa—sdasda-sdadsadsa" );
        map.put("component_appid","wxa4c5a4efb0d1de21" );
       // map.put("component_access_token","sdadas-213-213-21-31dsadsa" );

        Post.jsonPost("https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=dsamdnsa",map);


    }
}
