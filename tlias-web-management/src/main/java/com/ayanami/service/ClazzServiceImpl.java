package com.ayanami.service;

import com.ayanami.exception.NumException;
import com.ayanami.mapper.ClazzMapper;
import com.ayanami.pojo.Clazz;
import com.ayanami.pojo.ClazzQueryParam;
import com.ayanami.pojo.ClazzResult;
import com.ayanami.pojo.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService{

    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 分页查询班级信息
     * @param clazzQueryParam
     * @return
     */
    @Override
    public ClazzResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        //2.执行查询
        List<Clazz> clazzList=clazzMapper.list(clazzQueryParam);
        /*PageHelper.startPage() 会 “标记” 后续的第一次 MyBatis 查询，
        查询执行时会被 PageHelper 拦截，
        结果会被自动封装成 Page 对象（而非普通 List），
        可以通过 getResult() 拿到分页后的数据列表，getTotal() 拿到总记录数
         */
        //但必须先进行类型转换才能调用getResult方法获取数据列表
        Page<Clazz> c=(Page<Clazz>) clazzList;
        //3.封装数据
        return new ClazzResult<Clazz>(c.getTotal(),c.getResult());


    }

    /**
     * 增加班级
     * @param clazz
     */
    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);

    }
//更新班级信息
    /**
     * 1.根据ID查询回显班级信息
     */
    @Override
    public Clazz getById(Integer id) {
        Clazz clazz= clazzMapper.getById(id);
        return clazz;
    }

    /**
     * 更新班级信息
     * @param clazz
     */
    @Override
    public void update(Clazz clazz) {
        clazzMapper.update(clazz);


    }

    /**
     * 根据ID删除班级
     * @param id
     */
    @Override
    public void delete(Integer id) {
        Integer count=clazzMapper.countStu(id);
        if(count >0){
            throw new NumException("对不起，该班级下学生数大于0，无法删除");
        }
        clazzMapper.delete(id);



    }

    }



