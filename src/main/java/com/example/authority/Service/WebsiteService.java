package com.example.authority.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.authority.pojo.*;
import com.example.authority.pojo.pojoPlus.WebsitePlus;

import java.util.List;

public interface WebsiteService {
     boolean deletePermission(String type,String id);
     JSONArray getOrganization();
     boolean addOrganization(Organization organization);
     boolean addDepartemnt(String organizationId ,String departmentName);

     boolean addWebsite(Website website);

    boolean modifyWebsite(Website website);

     boolean deleteWebsite(String websiteId);

    List<WebsitePlus> getWebsiteList(String id);

    boolean insertApi(List<UnitySystemApi> list);

    boolean insertRoute(List<UnityRouteList> list);
    PageBean getApiList(Integer page, Integer pageSize);
    PageBean getApiListByMethodWebsite(Integer page, Integer pageSize,String method,String websiteId);
    PageBean getRouteList(Integer page, Integer pageSize);

    JSONObject getWebsiteDetail(String websiteId);

}
