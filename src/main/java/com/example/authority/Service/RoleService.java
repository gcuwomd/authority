package com.example.authority.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.authority.pojo.PageBean;
import com.example.authority.pojo.Role;

import java.util.List;

public interface RoleService {
    PageBean getRoleList(String departmentId, Integer page, Integer pageSize);

    boolean addRole(Role role);

    boolean addApiAuthority(String roleId, String websiteId, List list);

    boolean addRouteAuthority(String roleId, String websiteId, List list);

    boolean deleteRole(String roleId);

    boolean deleteRoleApiRoute(JSONArray route, JSONArray api);
    JSONObject getRouteApiById(String roleId);
}
