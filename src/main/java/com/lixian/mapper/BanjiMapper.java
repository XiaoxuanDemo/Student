package com.lixian.mapper;

import com.lixian.model.Banji;

public interface BanjiMapper {
    int deleteByPrimaryKey(String classid);

    int insert(Banji record);

    int insertSelective(Banji record);

    Banji selectByPrimaryKey(String classid);

    int updateByPrimaryKeySelective(Banji record);

    int updateByPrimaryKey(Banji record);
}