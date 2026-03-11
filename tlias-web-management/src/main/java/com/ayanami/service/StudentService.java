package com.ayanami.service;

import com.ayanami.pojo.PageResult;
import com.ayanami.pojo.Student;
import com.ayanami.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService{
    PageResult<Student> list(StudentQueryParam studentQueryParam);
}
