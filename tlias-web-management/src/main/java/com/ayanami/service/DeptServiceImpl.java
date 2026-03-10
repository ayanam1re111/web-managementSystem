package com.ayanami.service;

import com.ayanami.mapper.DeptMapper;
import com.ayanami.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service//自动创建对象并保存到容器
public class DeptServiceImpl implements DeptService{
    //修改部门
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);

    }
    //用ID查询回显
    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    //插入数据
    @Override
    public void add(Dept dept) {
        //1.补全基础属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用Mapper接口方法插入数据，无需返回值
        deptMapper.insert(dept);
    }
    //查找数据
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    //根据ID删除数据
    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);


    }
}
