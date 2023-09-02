package com.example.authority.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class Organization {
    private  String organizationId;
    private  String OrganizationName;

    private String OrganizationAvatar;
    private List<Department> department;
}
