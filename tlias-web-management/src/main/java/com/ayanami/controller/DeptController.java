package com.ayanami.controller;

import com.ayanami.anno.Log;
import com.ayanami.pojo.Dept;
import com.ayanami.pojo.Result;
import com.ayanami.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j//记录日志
@RequestMapping("/depts")//把公共路径抽到类上
@RestController//包含了Controller(将控制层的类对象交给IOC管理）以及ResponseBody（将方法返回值转为json，并且响应给前端）
public class DeptController {



    @Autowired
    private DeptService deptService;
    @GetMapping
    public Result list(){
        //System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> deptList= deptService.findAll();
        return Result.success(deptList);//将要返回的数据传给Result进行封装
    }

    //根据id删除部门
    @Log
    @DeleteMapping
    //public Result delete(@RequestParam("id") Integer deptId)
    public Result delete(Integer id){
        //System.out.println("根据ID删除部门："+id);
        log.info("根据ID删除部门：{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    //插入数据
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        //System.out.println("新增部门："+dept);
        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }
/*更新部门

 */
    //1.根据ID查询回显
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
       // System.out.println("根据ID查询，id="+id);
        log.info("根据ID查询，id={}",id);
        Dept dept=deptService.getById(id);
        return Result.success(dept);
    }
    //2.修改部门数据
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        //System.out.println("修改部门，dept="+dept);
        log.info("修改部门，dept={}",dept);
        deptService.update(dept);
        return Result.success();
    }

}
