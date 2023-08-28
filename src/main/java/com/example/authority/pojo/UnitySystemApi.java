package com.example.authority.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
public class UnitySystemApi {
    @JsonInclude(NON_NULL)
    private String websiteId;
    private  String apiId;
    private  String apiUrl;
    private String apiDescription;
    @JsonInclude(NON_NULL)
    private String apiMethod;
    @JsonInclude(NON_NULL)
    private String routeId;
    @JsonInclude(NON_NULL)
    private  String apiType;
}
