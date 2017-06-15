package com.lixian.mapper;

import com.lixian.model.HomeworkData;

public interface HomeworkDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HomeworkData record);

    int insertSelective(HomeworkData record);

    HomeworkData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomeworkData record);

    int updateByPrimaryKey(HomeworkData record);
}