package com.lixian.mapper;

import java.util.List;

import com.lixian.model.Teacher;

public interface TeacherMapper {
    int deleteByPrimaryKey(String id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
    
    List<Teacher> searchTeacher(String teachername);
}