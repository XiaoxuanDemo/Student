package com.lixian.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lixian.model.Kecheng;

public interface KechengMapper {
    int deleteByPrimaryKey(String id);

    int insert(Kecheng record);

    int insertSelective(Kecheng record);

    Kecheng selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Kecheng record);

    int updateByPrimaryKey(Kecheng record);
    
    List<Kecheng> searchKecheng(String name);
    
    int getChoseCount(String kechengid);
    
    Integer getStuchoseKecheng(@Param("stuid")String stuid,@Param("kechengid")String kechengid);
    
    Integer deleteStuKecheng(String id);
    
    List<Kecheng> getStuCanKecheng(@Param("id")String id);
}