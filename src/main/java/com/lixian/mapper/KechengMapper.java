package com.lixian.mapper;

import java.util.List;

import com.lixian.model.Kecheng;

public interface KechengMapper {
    int deleteByPrimaryKey(String id);

    int insert(Kecheng record);

    int insertSelective(Kecheng record);

    Kecheng selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Kecheng record);

    int updateByPrimaryKey(Kecheng record);
    
    List<Kecheng> searchKecheng(String name);
   
}