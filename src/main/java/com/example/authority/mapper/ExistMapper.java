package com.example.authority.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExistMapper {
    boolean OrganizationExist(String organizationId);
    boolean DepartmentExist(String key);
    boolean WebsiteExist(String key);
    boolean apiExist(String apiUrl);
    boolean routeExist(String routeUrl);
    boolean RoleExist(String key);
}
