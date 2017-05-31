package com.lixian.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lixian.model.HomeWorkInfo;
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
    Integer insertStuKc(@Param("id")String id,@Param("stuid")String stuid,@Param("kechengid")String kechenegid);
    Integer getStudentHomeWorkNum(@Param("stuid")String stuid);
    Integer getStudentCommitNum(@Param("stuid")String stuid);
    Integer getStudentKechenNum(@Param("stuid")String stuid);
    List<HomeWorkInfo> getHomeWork(@Param("stuid")String stuid);
    Integer addStuHomeWrok();
}