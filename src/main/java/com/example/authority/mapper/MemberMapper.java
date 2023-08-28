package com.example.authority.mapper;

import com.example.authority.pojo.Role;
import com.example.authority.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<User> getMemberList(String departmentId, String searchType, String keyword);

    boolean modifyUser(User user);

    boolean deleteRelation(List list);

    boolean deleteMember(List list);

    boolean addUserRoel(List list,String username);

    List<Role> getRole(String websiteId);

}
