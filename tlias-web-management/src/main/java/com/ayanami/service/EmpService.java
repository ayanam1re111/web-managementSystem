package com.ayanami.service;

import com.ayanami.pojo.Emp;
import com.ayanami.pojo.EmpQueryParam;
import com.ayanami.pojo.LoginInfo;
import com.ayanami.pojo.PageResult;

import java.util.List;

public interface EmpService {

    /**
     * 分页查询
     */
    // PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin,LocalDate end);
    PageResult<Emp> page(EmpQueryParam empQueryParam);


    void save(Emp emp);

    void delete(List<Integer> ids);

    /**
     * 根据ID查询员工信息
     * @param id
     * @return
     */
    Emp getInfo(Integer id);

    void update(Emp emp);

    List<Emp> getlist();

    /**
     * y员工登录方法
      * @param emp
     * @return
     */
    LoginInfo login(Emp emp);
}

