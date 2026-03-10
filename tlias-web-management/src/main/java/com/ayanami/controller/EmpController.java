package com.ayanami.controller;

import com.ayanami.pojo.*;
import com.ayanami.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    //参数和前端命名一致可省略RequestParam
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询：{}",empQueryParam);
        PageResult<Emp> pageResult=empService.page(empQueryParam);
        //最终将pageResult封装到Result里
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工：{}",emp);
        empService.save(emp);
        return Result.success();
    }
//    根据ID删除员工
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){//LIST这种复杂类型不可以省略RequestParam
        log.info("删除员工id：{}", ids);
        empService.delete(ids);
        return Result.success();

    }
//根据ID查询员工信息
    @GetMapping("/{id}")
    public Result getinfo(@PathVariable Integer id){
        log.info("根据ID查询员工信息：{}", id);
        Emp emp=empService.getInfo(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息:{}",emp);
        empService.update(emp);
        return Result.success();
    }
    /**
     * 查询所有员工（下拉框）
     */
    @GetMapping("/list")
    public Result getlist(){
        log.info("获取全部员工信息");
        List<Emp> emp=empService.getlist();
        return Result.success(emp);


    }
}
