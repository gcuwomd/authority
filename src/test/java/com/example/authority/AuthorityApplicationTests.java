package com.example.authority;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.authority.mapper.WebsiteMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class AuthorityApplicationTests {
	@Autowired
	WebsiteMapper websiteMapper;
@Autowired
PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
		System.out.println(passwordEncoder.encode("admin123"));
//		System.out.println(websiteMapper.getOrganization());
//		String jsoon= JSON.toJSONString(websiteMapper.getOrganization());
//		JSONArray JA=JSONArray.parseArray(jsoon);
//		System.out.println(JA);
//		System.out.println(jsoon);
	}

}
