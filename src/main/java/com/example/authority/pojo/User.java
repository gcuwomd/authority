package com.example.authority.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
public class User {
    private String username;
    @JsonInclude(NON_NULL)
    private String password;
    private  String name;
    private String phone;
    private String nickName;
    private String major;
    private  String grade;
    private  String classNumber;
    private String dorm;
    private String politicsStatus;
    private String avatar;
    private String position;
    private String email;

}
