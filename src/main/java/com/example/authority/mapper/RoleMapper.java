package com.example.authority.mapper;

import com.alibaba.fastjson.JSONArray;
import com.example.authority.pojo.Role;
import com.example.authority.pojo.UnityRouteList;
import com.example.authority.pojo.UnitySystemApi;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    List<Role> getRoleList(String departmentId);

    boolean addRole(Role role);

    boolean addApiAuthority(String roleId,String websiteId,List list);

    boolean addRouteAuthority(String roleId, String websiteId, List list);

    boolean deleteRole(String roleId);

    boolean deleteRoleApi(JSONArray api);

    boolean deleteRoleRoute(JSONArray route);
    List<UnityRouteList> getRouteByRoleId(String roleId);
    List<UnitySystemApi> getApiByRoleId(String roleId);
}
