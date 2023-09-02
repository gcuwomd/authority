package com.example.authority.Service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.authority.Service.CommonService;
import com.example.authority.mapper.CommonMapper;
import com.example.authority.pojo.*;
import com.qcloud.cos.model.ciModel.auditing.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommonServiceimpl implements CommonService {
    @Autowired
    CommonMapper commonMapper;
    @Override
    public JSONArray getAccessibleWebsite(String id) {
        JSONArray infoArray=JSONArray.parseArray(JSON.toJSONString( commonMapper.getaccessibleWebsite(id)));
        return infoArray;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject naviInfo(String username) {
        User userInfo = commonMapper.getUserInfo(username);
        List<Website> userPassWebsite = commonMapper.getUserPassWebsite(username);
        JSONObject json = (JSONObject) JSON.toJSON(userInfo);
        json.put("websiteList",userPassWebsite);


        return json;
    }

    @Override
    public JSONObject childWebsiteInfo(String username, String websiteId) {
        User userInfoByChildWebsite = commonMapper.getUserInfoByChildWebsite(username);
        List<UnityRouteList> unityRouteLists = commonMapper.queryWebsiteApi(username, websiteId);
        List<UnitySystemApi> apiList = commonMapper.queryWebsiteRoute(username, websiteId);

        JSONObject json = (JSONObject) JSON.toJSON(userInfoByChildWebsite);
        UnityAdmin unityAdmin = commonMapper.judgeSuper(username);
        if(unityAdmin!=null) json.put("super",true);
        json.put("super",false);


            json.put("route",unityRouteLists);
        json.put("api",apiList);

        return  json;
    }
}
