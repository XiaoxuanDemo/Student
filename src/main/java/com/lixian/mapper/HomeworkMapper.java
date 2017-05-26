package com.lixian.mapper;

import com.lixian.model.Homework;

public interface HomeworkMapper {
    int deleteByPrimaryKey(String id);

    int insert(Homework record);

    int insertSelective(Homework record);

    Homework selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Homework record);

    int updateByPrimaryKey(Homework record);
}