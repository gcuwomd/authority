package com.example.authority.Service;

import com.alibaba.fastjson.JSONArray;
import com.example.authority.pojo.Organization;
import com.example.authority.pojo.Website;

public interface WebsiteInterface {
     boolean deletePermission(String type,String id);
     JSONArray getOrganization();
     boolean addOrganization(Organization organization);
     boolean addDepartemnt(String organizationId ,String departmentName);

     boolean addWebsite(Website website);

    boolean modifyWebsite(Website website);
}
