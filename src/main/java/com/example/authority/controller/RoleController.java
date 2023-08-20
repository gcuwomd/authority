package com.example.authority.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.authority.Service.RoleService;
import com.example.authority.mapper.ExistMapper;
import com.example.authority.pojo.Role;
import com.example.authority.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    ExistMapper existMapper;
    @Autowired
    RoleService roleService;

    @GetMapping("/auth/role/list")
    ResultUtil getRoleList(@RequestParam String departmentId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        if (!(existMapper.DepartmentExist(departmentId))) return new ResultUtil(403, "部门不存在", null);
        return ResultUtil.sucess(roleService.getRoleList(departmentId, page, pageSize));

    }

    @PostMapping("/auth/role")
    ResultUtil addRole(@RequestBody Role role) {
        if (!(existMapper.DepartmentExist(role.getDepartmentId()))) return new ResultUtil(403, "部门不存在", null);
        if (existMapper.RoleExist(role.getRoleName())) return new ResultUtil(403, "角色已存在", null);
        if (roleService.addRole(role)) {
            return ResultUtil.sucess();
        }
        return ResultUtil.error();

    }

    @PostMapping("/auth/role/permission")
    ResultUtil addAuthority(@RequestBody JSONObject info) {
        String roleId = info.getString("roleId");
        JSONArray permissions = info.getJSONArray("permissions");
        for (Object permission : permissions) {
            JSONObject json = (JSONObject) JSONObject.toJSON(permission);
            String type = json.getString("type");
            String websiteId = json.getString("websiteId");
            List list = json.getJSONArray("websitePermissions");
            if (type.equals("api")) {
                if (!(roleService.addApiAuthority(roleId, websiteId, list))) {
                    return ResultUtil.error();
                }
            } else if (type.equals("route")) {
                if (!(roleService.addRouteAuthority(roleId, websiteId, list))) {
                    return ResultUtil.error();
                }
            }

        }
        return ResultUtil.sucess();
    }

    @DeleteMapping("/auth/role")
    ResultUtil deleteRole(@RequestParam String roleId) {
        if (roleService.deleteRole(roleId)) {
            return ResultUtil.sucess();
        }
        return new ResultUtil(403,"角色不存在",null);
    }

    @PutMapping("/auth/role/permission")
    ResultUtil deleteAuthority(@RequestBody JSONObject json) {
        String roleId = json.getString("roleId");
        if (existMapper.RoleExist(roleId)) {
            return new ResultUtil(403,"角色不存在",null);
        }
        JSONArray route = json.getJSONArray("route");
        JSONArray api = json.getJSONArray("api");
        if (roleService.deleteRoleApiRoute(route, api)) {
            return ResultUtil.sucess();
        }
        return new ResultUtil(403,"route或api不存在",null);

    }
}
