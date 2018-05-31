package com.xmpp.open.controller;

import com.xmpp.jedis.JedisManger;
import com.xmpp.open.entity.WxConfig;
import com.xmpp.open.service.OrgService;
import com.xmpp.tool.Base;
import com.xmpp.tool.WXBizMsgCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.lang.model.element.NestingKind;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.logging.Logger;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: dingchao
 * \* Date: 2018/5/29
 * \* Time: 下午2:17
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

@Controller
public class OrgController extends Base {
    @Autowired
    private OrgService orgService;


    @ResponseBody
    @RequestMapping("/test")
    public Object getlist(HttpServletRequest request, HttpServletResponse res) throws Exception{

        JedisManger.set("dingc","1992");

        JedisManger.set("dingc3","1992",2);
        System.out.println(JedisManger.getKey("dingc"));
        System.out.println(JedisManger.getKey("dingc3"));

        Thread.sleep(2500);

        System.out.println(JedisManger.getKey("dingc3"));


        return orgService.orgList();

    }

    /**
     * 微信服务器每10分钟会发送参数到开放平台服务器，此方法用来获取component_verify_ticket，并将component_verify_ticket转换成component_access_token
     * 1.注意component_access_token为三方平台最重要的基础参数，此后每个api调用基本都带有component_access_token，
     * 2.component_access_token拥有过期时间，默认时间为2小时，保存最近component_verify_ticket,以用来换取component_access_token
     *
     * @param req
     * @param res
     * @return
     */
    @ResponseBody
    @RequestMapping("/event/auth")
    public Object wxEvent(HttpServletRequest req, HttpServletResponse res) {


        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(req.getInputStream()));
            StringBuilder stb = new StringBuilder();

            String line = null;

            WXBizMsgCrypt pc = new WXBizMsgCrypt(WxConfig.getInstance().getToken(), WxConfig.getInstance().getEncodingAesKey(), WxConfig.getInstance().getWxappid());

            while ((line = in.readLine()) != null) {
                stb.append(line);
            }

            logger.info(stb.toString());
            StringReader sr = new StringReader(stb.toString());
            InputSource is = new InputSource(sr);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document document = db.parse(is);

            Element root = document.getDocumentElement();


            NodeList nodelist1 = root.getElementsByTagName("Encrypt");


            String encrypt = nodelist1.item(0).getTextContent();
            String msgSignature = req.getParameter("msg_signature");
            String timestamp = req.getParameter("timestamp");
            String nonce = req.getParameter("nonce");


            String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
            String fromXML = String.format(format, encrypt);

            String result2 = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);

            logger.info(result2);
            StringReader sr2 = new StringReader(result2);
            InputSource is2 = new InputSource(sr2);


            Document document2 = db.parse(is2);

            Element root2 = document2.getDocumentElement();

            NodeList code = root2.getElementsByTagName("ComponentVerifyTicket");

            String ComponentVerifyTicket = code.item(0).getTextContent();


            System.out.println(stb);
            WxConfig wxConfig = WxConfig.getInstance();
            JedisManger.set("ComponentVerifyTicket",ComponentVerifyTicket);
            wxConfig.setComponent_verify_ticket(ComponentVerifyTicket);
            logger.info("------ComponentVerifyTicket获取成功---------" + ComponentVerifyTicket);
            logger.info("---------------当前微信配置状态--------------" + wxConfig);

            String accessToken=JedisManger.getKey("component_access_token");
            if (accessToken == null||"".equals(accessToken)) {
                    getAccessToken();
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("获取AccessToken过程中出错了");
        }
        return "success";
    }


    @RequestMapping("/auth/page")
    public String authPage(Model model,HttpServletRequest request, HttpServletResponse res) throws Exception{


        System.out.println("sdadsa");
        model.addAttribute("pre_auth_code","1992edas");

        return "auth";


    }







}
