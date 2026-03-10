package com.ayanami.mapper;

import com.ayanami.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();

    //根据Id删除数据
    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);
    //如果Mapper接口传递过来的是一个对象，要获取对象中的属性，#{}中要写属性名（即驼峰命名法）
    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);
/*更新部门

 */
    //1.查询回显
    @Select("select id, name, create_time, update_time from dept where id=#{id}")
    Dept getById(Integer id);
    //2.修改数据，更新部门
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
