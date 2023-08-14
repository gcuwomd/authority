package com.example.authority.mapper;

import com.example.authority.pojo.Website;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonMapper {
    List<Website> getaccessibleWebsite(String id);
}
