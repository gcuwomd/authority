package com.example.authority.pojo;

import lombok.Data;
import org.bouncycastle.asn1.cms.PasswordRecipientInfo;

@Data
public class Department {
    private  String departmentId;
    private  String organizationName;
    private  String departmentName;
}
