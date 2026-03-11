package com.ayanami.controller;

import com.ayanami.pojo.PageResult;
import com.ayanami.pojo.Result;
import com.ayanami.pojo.Student;
import com.ayanami.pojo.StudentQueryParam;
import com.ayanami.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public Result list(StudentQueryParam studentQueryParam){
        log.info("学生列表查询:{}",studentQueryParam);
        PageResult<Student> stu=studentService.list(studentQueryParam);
        return Result.success(stu);

    }


}
