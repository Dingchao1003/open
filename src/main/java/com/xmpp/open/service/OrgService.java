package com.xmpp.open.service;

import com.xmpp.open.mapper.OrgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: dingchao
 * \* Date: 2018/5/29
 * \* Time: 下午2:14
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

@Service
public class OrgService {

    @Autowired
    private OrgMapper orgMapper;

    public List<Map<String,Object>>  orgList(){
        return orgMapper.list();
    }
}
