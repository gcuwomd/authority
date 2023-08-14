package com.example.authority.mapper;

import com.example.authority.pojo.*;
import com.example.authority.pojo.pojoPlus.WebsitePlus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WebsiteMapper {
 List<WebsitePlus> getWebsiteList(String id);

    boolean deletePermisson(String type, String id);


    boolean deletePermissonRelation(String type,String id);
    List<Department> getOrganization();

    boolean addOrganization(Organization organization);

    boolean addDepartment(String organizationId, String departmentId, String departmentName);

    boolean  addWebsite(Website website);

    boolean modifyWebsite(Website website);

    boolean deleteWebsite(String websiteId);
    boolean insertApi(List<UnitySystemApi> list);

   boolean insertRoute(List<UnityRouteList> list);
   List<UnitySystemApi> getApiList();
   List<UnityRouteList> getRouteList();
   Website getWebsiteInfo(String websiteId);
   Integer websiteApiNumber(String websiteId);
   Integer websiteRouteNumber(String websiteId);

    List<UnitySystemApi> getApiListByMethodWebsiteId(String method, String websiteId);
}
