package com.example.authority.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
public class Website {

    private String websiteId;
    @JsonInclude(NON_NULL)
    private String departmentId;
    @JsonInclude(NON_NULL)
    private  String departmentName;
    private  String websiteUrl;
    private  String websiteName;
    private  String websiteLogo;
}
