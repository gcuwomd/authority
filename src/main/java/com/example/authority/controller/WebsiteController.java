package com.example.authority.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.authority.Service.WebsiteService;
import com.example.authority.mapper.ExistMapper;
import com.example.authority.pojo.Organization;
import com.example.authority.pojo.UnityRouteList;
import com.example.authority.pojo.UnitySystemApi;
import com.example.authority.pojo.Website;
import com.example.authority.util.OssUtil;
import com.example.authority.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
public class WebsiteController {
    @Autowired
    OssUtil ossUtil;
    @Autowired
    ExistMapper existMapper;
    @Autowired
    WebsiteService websiteService;

    @DeleteMapping("/auth/website/permission")
    ResultUtil Deletepermission(@RequestParam String type, String id) {
        if (!(type.equals("api") || type.equals("route"))) return new ResultUtil(403, "参数错误", null);
        if (websiteService.deletePermission(type, id)) {
            return ResultUtil.sucess();
        }
        return new ResultUtil(403, "id错误", null);
    }

    @GetMapping("/auth/department/list")
    ResultUtil getDepartmentList() {
        return ResultUtil.sucess(websiteService.getOrganization());
    }

    @PostMapping("/auth/organization")
    ResultUtil addOrganization(@RequestBody Organization organization) {
        if (((organization.getOrganizationName() != null && (organization.getOrganizationAvatar() != null))))
            return new ResultUtil(403, "参数错误", null);
        if (websiteService.addOrganization(organization)) {
            return ResultUtil.sucess();
        }
        return ResultUtil.error();
    }

    @PostMapping("/auth/department")
    ResultUtil addDepartment(@RequestBody HashMap<String, String> map) {

        String organizationId = map.get("organizationId");
        String depaermentName = map.get("departmentName");

        if ((organizationId == null) || (depaermentName == null)) return new ResultUtil(403, "参数错误", null);
        if (!(existMapper.OrganizationExist(organizationId))) {
            return new ResultUtil(403, "组织不存在", null);
        }
        if ((existMapper.DepartmentExist(depaermentName))) {
            return new ResultUtil(403, "部门已存在", null);
        }
        if ((websiteService.addDepartemnt(organizationId, depaermentName))) {
            return ResultUtil.sucess();
        }
        return ResultUtil.error();
    }

    @PostMapping("/auth/website")
    ResultUtil addWrbsite(@RequestBody Website website) {
        if ((website.getWebsiteName() == null)
                || (website.getDepartmentId() == null)
                || (website.getWebsiteUrl() == null))
            return new ResultUtil(403, "参数错误", null);
        else if (!(existMapper.DepartmentExist(website.getDepartmentId()))) {
            return new ResultUtil(403, "部门不存在", null);
        } else if (existMapper.WebsiteExist(website.getWebsiteName())) {
            return new ResultUtil(403, "站点名重复", null);
        }
        if (websiteService.addWebsite(website)) return ResultUtil.sucess();
        return ResultUtil.error();
    }

    @PutMapping("/auth/website")
    ResultUtil modifyWebsite(@RequestBody Website website) {
        if ((website.getDepartmentId() != null)) {
            if (!(existMapper.DepartmentExist(website.getDepartmentId()))) {
                return new ResultUtil(403, "更换的部门不存在", null);
            }
        }

        websiteService.modifyWebsite(website);
        return ResultUtil.sucess();
    }

    @DeleteMapping("/auth/website")
    ResultUtil deleteWebsite(@RequestParam String websiteId) {
        if (websiteService.deleteWebsite(websiteId)) {
            return ResultUtil.sucess();
        }
        return new ResultUtil(403, "删除的站点不存在", null);
    }

    @GetMapping("/auth/website/list")
    ResultUtil getWebsiteList(@RequestParam String id) {
        if ((id == null)) return new ResultUtil(403, "参数值不能为空", null);
        return ResultUtil.sucess(websiteService.getWebsiteList(id));

    }

    @PostMapping("/auth/website/permission/api")
    ResultUtil insertAPI(@RequestBody List<UnitySystemApi> list) {
        for (UnitySystemApi unitySystemApi : list) {
            if (!(existMapper.WebsiteExist(unitySystemApi.getWebsiteId())))
                return new ResultUtil(403, "站点不存在", null);
        }
        for (UnitySystemApi unitySystemApi : list) {
            if (existMapper.apiExist(unitySystemApi.getApiUrl()))
                return new ResultUtil(403, "此api已经存在", null);
        }
        if ((websiteService.insertApi(list))) return ResultUtil.sucess();

        return ResultUtil.error();
    }

    @PostMapping("/auth/website/permission/route")
    ResultUtil insertRoute(@RequestBody List<UnityRouteList> list) {
        for (UnityRouteList UnityRouteList : list) {
            if (!(existMapper.WebsiteExist(UnityRouteList.getWebsiteId())))
                return new ResultUtil(403, "站点不存在", null);
        }
        for (UnityRouteList UnityRouteList : list) {
            if ((existMapper.routeExist(UnityRouteList.getRouteUrl())))
                return new ResultUtil(403, "route已经存在", null);
        }
        if (websiteService.insertRoute(list)) return ResultUtil.sucess();
        return ResultUtil.error();
    }

    @GetMapping("/auth/website/permission")
    ResultUtil getList(@RequestParam Integer page, Integer pageSize, String type, String websiteId, String method) {
//        if (websiteId.equals("null")) websiteId = null;
//        if (method.equals("null")) method = null;
        if (!((type.equals("api")) || (type.equals("route")) || (type.equals("all"))))
            return new ResultUtil(403, "type类型错误", null);
        if (type.equals("api"))
            return ResultUtil.sucess(websiteService.getApiListByMethodWebsite(page, pageSize, method, websiteId));
        if (type.equals("route"))
            return ResultUtil.sucess(websiteService.getRouteList(page, pageSize));
        JSONObject list = new JSONObject();
        list.put("api", websiteService.getApiList(page, pageSize));
        list.put("route", websiteService.getRouteList(page, pageSize));
        return ResultUtil.sucess(list);
    }

    @GetMapping("/auth/website/detail")
    ResultUtil getDetail(@RequestParam String websiteId) {
        if (!(existMapper.WebsiteExist(websiteId))) return new ResultUtil(403, "站点不存在", null);
        return ResultUtil.sucess(websiteService.getWebsiteDetail(websiteId));
    }




}
