package com.example.authority.pojo.pojoPlus;

import com.alibaba.fastjson.JSONObject;
import com.example.authority.pojo.UnityRouteList;
import com.example.authority.pojo.UnitySystemApi;
import lombok.Data;

import java.util.List;

@Data
public class WebsitePageApi extends UnityRouteList {
    private List<UnitySystemApi> pageApi;
    private Integer apiCount;
}
