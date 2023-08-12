package com.example.authority.mapper;

import com.example.authority.pojo.Department;
import com.example.authority.pojo.Organization;
import com.example.authority.pojo.Website;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WebsiteMapper {
    boolean deletePermisson(String type,String id);


    boolean deletePermissonRelation(String type,String id);
    List<Department> getOrganization();

    boolean addOrganization(Organization organization);

    boolean addDepartment(String organizationId, String departmentId, String departmentName);
    boolean OrganizationExist(String organizationId);
    boolean DepartmentExist(String key);

    boolean  addWebsite(Website website);

    boolean modifyWebsite(Website website);
}
