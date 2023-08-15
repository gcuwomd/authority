package com.example.authority.Service;

import com.example.authority.pojo.PageBean;
import com.example.authority.pojo.User;

import java.util.List;

public interface MemberService {
    PageBean getMemberList(String departmentId, String searchType, String keyword, Integer page, Integer pageSize);

    boolean modifyUser(User user);

    boolean deleteMember(List list);

    boolean addUserRole(String username, List list);
}
