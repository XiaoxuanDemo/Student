package com.lixian.mapper;

import com.lixian.model.Kecheng;

public interface KechengMapper {
    int deleteByPrimaryKey(String id);

    int insert(Kecheng record);

    int insertSelective(Kecheng record);

    Kecheng selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Kecheng record);

    int updateByPrimaryKey(Kecheng record);
}