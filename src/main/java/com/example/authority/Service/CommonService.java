package com.example.authority.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface CommonService {
    JSONArray getAccessibleWebsite(String id);
    JSONObject naviInfo(String username);
    JSONObject childWebsiteInfo(String username,String websiteId);
}
