package com.example.authority.pojo;

import lombok.Data;

import java.util.List;

/**
 * 适应前端字段
 *
* */
@Data
public class OrganizationType {
    private  String value;
    private  String label;
    private List<Department> children;
}
