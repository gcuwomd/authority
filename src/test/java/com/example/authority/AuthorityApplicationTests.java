package com.example.authority;

import com.example.authority.Service.impl.WebsiteServiceImpl;
import com.example.authority.mapper.WebsiteMapper;
import com.example.authority.util.OssUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthorityApplicationTests {
	@Autowired
	WebsiteMapper websiteMapper;
	@Autowired
    WebsiteServiceImpl websiteService;

@Autowired
	OssUtil ossUtil;
	@Test
	void contextLoads() {
		ossUtil.delete("https://1-1313912192.cos.ap-guangzhou.myqcloud.com/favicon.ico");
	}

}
