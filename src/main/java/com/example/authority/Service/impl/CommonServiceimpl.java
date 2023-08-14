package com.example.authority.Service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.authority.Service.CommonService;
import com.example.authority.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceimpl implements CommonService {
    @Autowired
    CommonMapper commonMapper;
    @Override
    public JSONArray getAccessibleWebsite(String id) {
        JSONArray infoArray=JSONArray.parseArray(JSON.toJSONString( commonMapper.getaccessibleWebsite(id)));
        return infoArray;
    }
}
