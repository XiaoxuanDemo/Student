package com.lixian.mapper;

import com.lixian.model.Student;
import com.lixian.model.StudentInfo;

public interface StudentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    StudentInfo getStudentInfo(String id);
}