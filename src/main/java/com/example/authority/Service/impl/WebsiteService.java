package com.example.authority.Service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.authority.Service.WebsiteInterface;
import com.example.authority.mapper.WebsiteMapper;
import com.example.authority.pojo.Organization;
import com.example.authority.pojo.Website;
import com.example.authority.util.ResultUtil;
import com.example.authority.util.uuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WebsiteService implements WebsiteInterface {
@Autowired
    WebsiteMapper websiteMapper;
    @Override
    @Transactional(rollbackFor=Exception.class)//出现所有异常都会回滚
    public boolean deletePermission(String type, String id) {
        if (websiteMapper.deletePermisson(type,id)) {
            if (websiteMapper.deletePermissonRelation(type,id)) {
                return  true;
            }
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
}
