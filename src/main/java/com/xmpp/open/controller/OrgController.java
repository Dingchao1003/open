package com.xmpp.open.controller;

import com.xmpp.open.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class OrgController {
    @Autowired
    private OrgService orgService;


    @ResponseBody
    @RequestMapping("/test")
    public Object getlist(HttpServletRequest request, HttpServletResponse res){

        return orgService.orgList();

    }
}
