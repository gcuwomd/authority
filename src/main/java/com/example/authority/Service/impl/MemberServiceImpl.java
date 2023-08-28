package com.example.authority.Service.impl;

import com.example.authority.Service.MemberService;
import com.example.authority.mapper.MemberMapper;
import com.example.authority.pojo.PageBean;
import com.example.authority.pojo.Role;
import com.example.authority.pojo.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberMapper memberMapper;
    @Override
    public PageBean getMemberList(String departmentId, String searchType, String keyword, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<User> list= memberMapper.getMemberList(departmentId,searchType,keyword);
        Page<User> p = (Page<User>) list;
        PageBean pageBean=new PageBean(p.getTotal(),list);
        return pageBean;
    }

    @Override
    public boolean modifyUser(User user) {

        return memberMapper.modifyUser(user);
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public boolean deleteMember(List list) {
         memberMapper.deleteRelation(list);
        if (memberMapper.deleteMember(list)) {
            return true;
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUserRole(String username, List list) {
        List listUser=new ArrayList<>();
        listUser.add(username);
        memberMapper.deleteRelation(listUser);

        return  memberMapper.addUserRoel(list,username);
    }

    @Override
    public List<Role> getRole(String websiteId) {

        return    memberMapper.getRole(websiteId);
    }
}
