package com.example.authority.controller;

import com.example.authority.Service.WebsiteInterface;
import com.example.authority.mapper.WebsiteMapper;
import com.example.authority.pojo.Organization;
import com.example.authority.pojo.Website;
import com.example.authority.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class WebsiteController {
    @Autowired
    WebsiteMapper websiteMapper;
    @Autowired
    WebsiteInterface websiteInterface;
    @DeleteMapping("/auth/website/permission")
    ResultUtil Deletepermission(@RequestParam String type,String id){
        if (!(type.equals("api")||type.equals("route"))) return  new ResultUtil(403,"参数错误",null);
        if (websiteInterface.deletePermission(type,id)) {
            return  ResultUtil.sucess();
        }
        return  new ResultUtil(403,"id错误",null);
    }
    @GetMapping("/auth/department/list")
    ResultUtil getDepartmentList( ){
        return ResultUtil.sucess(websiteInterface.getOrganization());
    }
    @PostMapping("/auth/organization")
    ResultUtil addOrganization(@RequestBody Organization organization){
        if (((organization.getOrganizationName()!=null&&(organization.getOrganizationAvatar()!=null)))) return  new ResultUtil(403,"参数错误",null);
        if (websiteInterface.addOrganization(organization)) {
            return ResultUtil.sucess();
        }
        return ResultUtil.error();
    }
    @PostMapping("/auth/department")
    ResultUtil addDepartment(@RequestBody HashMap<String ,String> map){

        String organizationId=map.get("organizationId");
        String depaermentName=map.get("departmentName");

        if((organizationId==null)||(depaermentName==null)) return new ResultUtil(403,"参数错误",null);
        if (!(websiteMapper.OrganizationExist(organizationId))) {
            return new ResultUtil(403,"组织不存在",null);
        }
        if ((websiteMapper.DepartmentExist(depaermentName))) {
            return new ResultUtil(403,"部门已存在",null);
        }
        if ((websiteInterface.addDepartemnt(organizationId,depaermentName))) {
            return  ResultUtil.sucess();
        }
       return  ResultUtil.error();
    }
    @PostMapping("/auth/website")
    ResultUtil addWrbsite(@RequestBody Website website){
        if ((website.getWebsiteName()==null)
                ||(website.getDepartmentId()==null)
                ||(website.getWebsiteUrl()==null))
            return  new ResultUtil(403,"餐宿错误",null);
        if (!(websiteMapper.DepartmentExist(website.getDepartmentId()))) {
            return  new ResultUtil(403,"部门不存在",null);
        }
        if(websiteInterface.addWebsite(website)) return ResultUtil.sucess();
        return ResultUtil.error();
    }
    @PutMapping("/auth/website")
    ResultUtil modifyWebsite(@RequestBody Website website){
        if ((website.getDepartmentId()!=null)){
            if (!(websiteMapper.DepartmentExist(website.getDepartmentId()))) {
                return new ResultUtil(403,"更换的部门不存在",null);
            }
        }

      websiteInterface.modifyWebsite(website);
        return ResultUtil.sucess();
    }
}
