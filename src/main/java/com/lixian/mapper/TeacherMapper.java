package com.lixian.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lixian.model.Banji;
import com.lixian.model.Kecheng;
import com.lixian.model.StudentInfo;
import com.lixian.model.Teacher;

public interface TeacherMapper {
    int deleteByPrimaryKey(String id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
    
    List<Teacher> searchTeacher(String teachername);
    
    List<StudentInfo> searchStudent(@Param("id")String teacherid,@Param("classid")String classid,@Param("kechengid")String kechengid);
    
    List<Banji> getAllClass();
}