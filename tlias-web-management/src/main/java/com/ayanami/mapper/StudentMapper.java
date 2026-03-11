package com.ayanami.mapper;

import com.ayanami.pojo.Student;
import com.ayanami.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> list(StudentQueryParam studentQueryParam);

}
