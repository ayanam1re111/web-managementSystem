package com.ayanami.controller;

import com.ayanami.pojo.Clazz;
import com.ayanami.pojo.ClazzQueryParam;
import com.ayanami.pojo.ClazzResult;
import com.ayanami.pojo.Result;
import com.ayanami.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    /**
     * 分页查询
     * @param clazzQueryParam
     * @return
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("查询班级信息");
        ClazzResult clazzResult=clazzService.page(clazzQueryParam);
        return Result.success(clazzResult);
    }

    /**
     * 新增班级信息
     * @param clazz
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Clazz clazz){//注解自动将请求体中的数据封装为指定对象
        log.info("正在添加班级信息，班级为: {}",clazz.getName());
        clazzService.save(clazz);
        return Result.success();
    }

//更新班级信息
    /**1.根据ID查询回显班级
     *
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("正在查询：{}",id);
        Clazz clazz=clazzService.getById(id);
        return Result.success(clazz);

    }

    /**
     * 2.更新班级信息
     * @param clazz
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("正在修改班级信息");
        clazzService.update(clazz);
        return Result.success();
    }

    /**
     * 3.删除班级
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("正在删除班级，id为{}",id);
        clazzService.delete(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result classList(){
        log.info("查询所有班级");
        List<Clazz> clazzlist= clazzService.list();
        return Result.success(clazzlist);

    }

}
