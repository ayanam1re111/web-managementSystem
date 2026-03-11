package com.ayanami.mapper;

import com.ayanami.pojo.Clazz;
import com.ayanami.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ClazzMapper {
    /**
     * 分页查询班级信息
     * @param clazzQueryParam
     * @return
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     * 新增班级信息
     * @param clazz
     */
    @Options(useGeneratedKeys=true,keyProperty = "id")
    @Insert("insert clazz(name,room,begin_date,end_date,master_id,subject,create_time,update_time) " +
        "value (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void insert(Clazz clazz);
//更新班级信息
    /**
     * 根据ID查询回显
     * @param id
     * @return
     */
    @Select("select * from clazz where id=#{id} ")
    Clazz getById(Integer id);

    /**
     * 更新信息
     * @param clazz
     */
   @Update("update clazz set name=#{name},room=#{room},begin_date=#{beginDate},end_date=#{endDate},master_id=#{masterId},subject=#{subject} where id=#{id}")
    void update(Clazz clazz);

   //根据ID删除班级
    /**1.计算每个班级学生数，用于判断是否抛出异常
     *
      * @param id
     * @return
     */
    @Select("select count(id) from student where clazz_id=#{id}")
    Integer countStu(Integer id);

    /**
     * 2.删除班级
     * @param id
     */
    @Delete("delete from clazz where id=#{id}")
    void delete(Integer id);


    /**
     * 查询班级全部列表
     * @return
     */
    @Select("select * from clazz")
    List<Clazz> listAll();
}
