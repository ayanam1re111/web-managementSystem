package com.ayanami.service;

import com.ayanami.mapper.StudentMapper;
import com.ayanami.pojo.PageResult;
import com.ayanami.pojo.Student;
import com.ayanami.pojo.StudentQueryParam;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> list(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        List<Student> stu=studentMapper.list(studentQueryParam);
        Page<Student> page=(Page<Student>) stu;
        return new PageResult<Student>(page.getTotal(),page.getResult());
    }
}
