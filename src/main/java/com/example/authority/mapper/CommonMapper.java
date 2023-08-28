package com.example.authority.mapper;

import com.example.authority.pojo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonMapper {
    List<Website> getaccessibleWebsite(String id);

    List<Website> getUserPassWebsite(String username);

    User getUserInfo(String username);
    User getUserInfoByChildWebsite(String username);
    List <UnitySystemApi> queryWebsiteRoute(String username,String websiteId);
    List<UnityRouteList> queryWebsiteApi(String username,String websiteId);

    UnityAdmin judgeSuper(String username);
}
