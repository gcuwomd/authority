package com.example.authority.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
public class Role {
    private String roleId;
    private String roleName;
    @JsonInclude(NON_NULL)
    private String departmentId;

}
