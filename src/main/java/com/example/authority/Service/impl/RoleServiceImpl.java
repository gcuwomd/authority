package com.example.authority.Service.impl;

import com.alibaba.fastjson.JSONArray;
import com.example.authority.Service.RoleService;
import com.example.authority.mapper.RoleMapper;
import com.example.authority.pojo.PageBean;
import com.example.authority.pojo.Role;
import com.example.authority.util.uuidUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Override
    public PageBean getRoleList(String departmentId, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Role> list=roleMapper.getRoleList(departmentId);
        Page<Role> p= (Page<Role>) list;
        PageBean pageBean=new PageBean(p.getTotal(),list);
        return pageBean;
    }

    @Override
    public boolean addRole(Role role) {
        String roleId= uuidUtil.generateUUID();
        role.setRoleId(roleId);
        return roleMapper.addRole(role);
    }

    @Override
    public boolean addApiAuthority(String roleId, String websiteId, List list) {
        return roleMapper.addApiAuthority(roleId,websiteId,list) ;
    }

    @Override
    public boolean addRouteAuthority(String roleId, String websiteId, List list) {
        return roleMapper.addRouteAuthority(roleId,websiteId,list) ;
    }

    @Override
    public boolean deleteRole(String roleId) {
        return roleMapper.deleteRole(roleId);
    }

    @Override
    public boolean deleteRoleApiRoute(JSONArray route, JSONArray api) {
        if ( roleMapper.deleteRoleApi(api)&&
        roleMapper.deleteRoleRoute(route)) return true;
        return false;
    }


}
