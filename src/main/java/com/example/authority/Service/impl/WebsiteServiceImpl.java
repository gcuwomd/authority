package com.example.authority.Service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.authority.Service.WebsiteService;
import com.example.authority.mapper.WebsiteMapper;
import com.example.authority.pojo.*;
import com.example.authority.pojo.pojoPlus.WebsitePageApi;
import com.example.authority.pojo.pojoPlus.WebsitePlus;
import com.example.authority.util.uuidUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class WebsiteServiceImpl implements WebsiteService {
    @Autowired
    WebsiteMapper websiteMapper;
    @Override
    @Transactional(rollbackFor=Exception.class)//出现所有异常都会回滚
    public boolean deletePermission(String type, String id) {
        websiteMapper.deletePermisson(type,id);
            if (websiteMapper.deletePermissonRelation(type,id)) {
                return  true;
            }

        return false;
    }

    @Override
    public JSONArray getOrganization() {
        String jsoon= JSON.toJSONString(websiteMapper.getOrganization());
        JSONArray info=JSONArray.parseArray(jsoon);
        return  info;
    }

    @Override
    public boolean addOrganization(Organization organization) {
        String organizationId= uuidUtil.generateUUID();
        organization.setOrganizationId(organizationId);
        if (websiteMapper.addOrganization(organization)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addDepartemnt(String organizationId, String departmentName) {
        String departmentId=uuidUtil.generateUUID();


        return  websiteMapper.addDepartment(organizationId,departmentId,departmentName);
    }

    @Override
    public boolean addWebsite(Website website) {
        String websiteId=uuidUtil.generateUUID();
        website.setWebsiteId(websiteId);
       return websiteMapper.addWebsite(website);
    }

    @Override
    public boolean modifyWebsite(Website website) {

    return  websiteMapper.modifyWebsite(website);
    }

    @Override
    public boolean deleteWebsite(String websiteId) {

        return  websiteMapper.deleteWebsite(websiteId);
    }

    @Override
    public List<WebsitePlus> getWebsiteList(String id) {
        return websiteMapper.getWebsiteList(id);
    }

    @Override
    public boolean insertApi(List<UnitySystemApi> list) {
        for (UnitySystemApi item : list) {
            item.setApiId(uuidUtil.generateUUID());
        }

        return websiteMapper.insertApi(list);
    }

    @Override
    public boolean insertRoute(List<UnityRouteList> list) {
        for (UnityRouteList unityRouteList : list) {
            unityRouteList.setRouteId(uuidUtil.generateUUID());
        }
        return websiteMapper.insertRoute(list);
    }

    @Override
    public PageBean getApiList(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<UnitySystemApi> apiList=websiteMapper.getApiList();
        Page<UnitySystemApi> p= (Page<UnitySystemApi>) apiList;
        PageBean pageBean = new PageBean(p.getTotal(), apiList);
        return pageBean;
    }

    @Override
    public PageBean getApiListByMethodWebsite(Integer page, Integer pageSize, String method, String websiteId) {
        PageHelper.startPage(page,pageSize);
        List<UnitySystemApi> apiList=websiteMapper.getApiListByMethodWebsiteId(method,websiteId);
        Page<UnitySystemApi> p= (Page<UnitySystemApi>) apiList;
        PageBean pageBean = new PageBean(p.getTotal(), apiList);
        return pageBean;
    }

    @Override
    public PageBean getRouteList(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<UnityRouteList> apiList=websiteMapper.getRouteList();
        Page<UnityRouteList> p= (Page<UnityRouteList>) apiList;
        PageBean pageBean = new PageBean(p.getTotal(), apiList);
        return pageBean;
    }

    @Override
    public JSONObject getWebsiteDetail(String websiteId) {

        JSONObject websiteInfo = (JSONObject) JSONObject.toJSON(websiteMapper.getWebsiteInfo(websiteId));
        Integer apiNum=websiteMapper.websiteApiNumber(websiteId);
        System.out.println(apiNum);
        Integer routeNum=websiteMapper.websiteRouteNumber(websiteId);

        websiteInfo.put("apiNum",apiNum);
        websiteInfo.put("routeNumber",routeNum);
        return websiteInfo;
    }

    @Override
    public JSONObject getWebsitePermissions(String websiteId){
        JSONObject permissions = new JSONObject();
        List<WebsitePageApi> pageApis = websiteMapper.queryWebsitePageApi(websiteId);
        List<UnitySystemApi> globalApi = websiteMapper.queryWebsiteGlobalApi(websiteId);
        permissions.put("routes",pageApis);
        permissions.put("globalApi",globalApi);
        return permissions;
    }

}
