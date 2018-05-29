package com.xmpp.open.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: dingchao
 * \* Date: 2018/5/29
 * \* Time: 下午2:05
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


public interface OrgMapper {


    List<Map<String,Object>> list();
}
