package com.example.authority.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.authority.Service.MemberService;
import com.example.authority.mapper.ExistMapper;
import com.example.authority.pojo.User;
import com.example.authority.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class MemberController {
    @Autowired
    MemberService memberService;
    @Autowired
    ExistMapper existMapper;
    @GetMapping("/auth/member/list")
    ResultUtil  getMemberList(@RequestParam String departmentId ,String searchType,String keyword,Integer page,Integer pageSize){
        if (!(existMapper.DepartmentExist(departmentId))) {
            return new ResultUtil(403,"部门不存在",null);
        }
        return ResultUtil.sucess(memberService.getMemberList(departmentId,searchType,keyword,page,pageSize));

    }
    @PutMapping("/auth/member")
    ResultUtil modifiyUser(@RequestBody User user){
        if (memberService.modifyUser(user)) {
            return ResultUtil.sucess();
        }
        return ResultUtil.error();
    }
    @DeleteMapping("/auth/member")
    ResultUtil deleteMember(@RequestParam List members){
        if (memberService.deleteMember(members)) {
            return ResultUtil.sucess();
        }
        return ResultUtil.error();
    }
    @PostMapping("/auth/member/role")
    ResultUtil addUserRole(@RequestBody JSONObject json){
        String username=json.getString("username");
        if (!(existMapper.UserExist(username))) {
            return new ResultUtil(403,"用户不存在",null);
        }

        List list= (List) json.get("roles");
        try {
            memberService.addUserRole(username,list);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return ResultUtil.sucess();
    }
    @GetMapping("/auth/member/role")
    ResultUtil  memberWebsiteRole(@RequestParam String websiteId){
        return ResultUtil.sucess(memberService.getRole(websiteId));

    }

}
