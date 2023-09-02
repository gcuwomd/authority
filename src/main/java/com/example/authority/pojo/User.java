package com.example.authority.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
public class User {
    @JsonInclude(NON_NULL)
    private String username;
    @JsonInclude(NON_NULL)
    private String password;
    private  String name;
    @JsonInclude(NON_NULL)
    private String phone;
    @JsonInclude(NON_NULL)
    private String nickName;
    @JsonInclude(NON_NULL)
    private String major;
    @JsonInclude(NON_NULL)
    private  String grade;
    @JsonInclude(NON_NULL)
    private  String classNumber;
    @JsonInclude(NON_NULL)
    private String dorm;
    @JsonInclude(NON_NULL)
    private String politicsStatus;
    @JsonInclude(NON_NULL)
    private String avatar;
    @JsonInclude(NON_NULL)
    private String position;
    @JsonInclude(NON_NULL)
    private String email;
    @JsonInclude(NON_NULL)
    private  String organizationId;
    @JsonInclude(NON_NULL)
    private  String departmentId;
    @JsonInclude(NON_NULL)
    private  String roleId;
    @JsonInclude(NON_NULL)
    private  String roleName;


}
