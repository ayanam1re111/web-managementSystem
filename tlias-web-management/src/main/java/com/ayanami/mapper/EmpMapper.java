package com.ayanami.mapper;

import com.ayanami.pojo.Emp;
import com.ayanami.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

//操作员工信息
@Mapper
public interface EmpMapper {
/*原始写法
//查询符合条件的总记录数
    @Select("select count(*) from emp e left join dept d on e.dept_id=d.id")
    public Long count();
 //分页查询
    //deptName根据接口文档需求命名（另外要和PageResult属性名保持同步）
    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id order by e.update_time desc limit #{start},#{pageSize}")
    public List<Emp> list(Integer start,Integer pageSize);*/

//基于PageHelper插件的写法
    //@Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id order by e.update_time desc ")

    /**
     * 条件查询员工信息
     * @param empQueryParam
     * @return
     */

    public List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工信息
     * @param emp
     */
    @Options(useGeneratedKeys=true,keyProperty = "id")//自动获取数据库自增主键，指定把生成主键回填到插入对象的属性
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);


    void deleteByIds(List<Integer> ids);

    /**
     * 根据ID查询员工信息以及工作经历信息
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 根据ID更新员工基本信息
     * @param emp
     */

    void updateById(Emp emp);

    /**
     * 统计员工职位人数
     * @return
     */

    List<Map<String, Object>> countEmpJobData();
    /**
     * 统计员工性别人数
     * @return
     */
    List<Map<String, Object>> countEmpGenderData();

 @Select("select * from emp")
    List<Emp> getlist();

    /**
     * 根据用户名和密码查询员工信息
      * @param emp
     * @return
     */
    @Select("select id,username,name from emp where username=#{username} and password=#{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
