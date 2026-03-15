package com.ayanami.controller;

import com.ayanami.pojo.PageResult;
import com.ayanami.pojo.Result;
import com.ayanami.pojo.Student;
import com.ayanami.pojo.StudentQueryParam;
import com.ayanami.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 条件分页查询接口
     * @param studentQueryParam
     * @return
     */
    @GetMapping
    public Result list(StudentQueryParam studentQueryParam){
        log.info("学生列表查询:{}",studentQueryParam);
        PageResult<Student> stu=studentService.list(studentQueryParam);
        return Result.success(stu);

    }

    /**
     * 添加学生信息
     * @param student
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Student student){
        log.info("添加学员，ID为{}",student.getName());
        studentService.add(student);
        return Result.success();

    }


}
