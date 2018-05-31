package com.xmpp.open.entity;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: dingchao
 * \* Date: 2018/5/30
 * \* Time: 上午8:53
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

public class WxConfig {


    private final String token="xmpp";

    private final String encodingAesKey="1234567890123456789012345678901234567890123";

    private final String wxappid="wxa4c5a4efb0d1de21";

    private final String wxappsecret="c43925732da7a9250625a5a66f4fd173";


    private String component_verify_ticket;

    private String component_access_token;

    private String pre_auth_code;



    private static WxConfig  wxConfig;

    private WxConfig(){

    }
    public static WxConfig getInstance(){
        if(wxConfig==null){
            wxConfig=new WxConfig();
        }
        return wxConfig;
    }


    public String getToken() {
        return token;
    }

    public String getEncodingAesKey() {
        return encodingAesKey;
    }

    public String getWxappid() {
        return wxappid;
    }

    public String getWxappsecret() {
        return wxappsecret;
    }

    public String getComponent_verify_ticket() {
        return component_verify_ticket;
    }

    public String getComponent_access_token() {
        return component_access_token;
    }

    public String getPre_auth_code() {
        return pre_auth_code;
    }

    public void setComponent_verify_ticket(String component_verify_ticket) {
        this.component_verify_ticket = component_verify_ticket;
    }

    public void setComponent_access_token(String component_access_token) {
        this.component_access_token = component_access_token;
    }

    public void setPre_auth_code(String pre_auth_code) {
        this.pre_auth_code = pre_auth_code;
    }
}
