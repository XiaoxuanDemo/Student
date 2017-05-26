package com.lixian.mapper;

import com.lixian.model.Stupro;

public interface StuproMapper {
    int deleteByPrimaryKey(String id);

    int insert(Stupro record);

    int insertSelective(Stupro record);

    Stupro selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Stupro record);

    int updateByPrimaryKey(Stupro record);
}