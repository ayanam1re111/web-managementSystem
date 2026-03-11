package com.ayanami.service;

import com.ayanami.mapper.DeptMapper;
import com.ayanami.mapper.EmpExprMapper;
import com.ayanami.mapper.EmpMapper;
import com.ayanami.pojo.*;
import com.ayanami.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

/*    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //1.调用Mapper接口，查询总记录数
        Long total = empMapper.count();
        //2.调用Mapper,查询结果列表
        Integer start=(page-1)*pageSize;//计算起始索引，适配Mapper方法参数
        List<Emp> rows =empMapper.list(page,pageSize);
        //3.封装结果
        return new PageResult<Emp>(total,rows);*/


    /**
     * PageHelper分页查询

     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1. 设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //2. 执行查询
        List<Emp> empList = empMapper.list(empQueryParam);//MyBatis规定接口中查询方法返回值只能声明为标准JAV集合/类型
        Page<Emp> p = (Page<Emp>) empList;//当你执行 PageHelper.startPage() 后，Mapper 查询返回的实际对象是 Page 实例，只是被「向上转型」为 List 类型（父类引用指向子类对象）。
        //所以强转本质是「把父类引用转回子类类型」

        //3. 封装结果
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Transactional
    @Override
    public void save(Emp emp) {
        try {
            //1.保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //2.保存员工工作经历信息
            List<EmpExpr> exprList=emp.getExprList();

            //由于工作经历有可能为空
            if(!CollectionUtils.isEmpty(exprList)){
                //遍历expr元素，为empId赋刚才生成的主键值
                exprList.forEach(empExpr->{
                    empExpr.setEmpId(emp.getId());
                });/**
                 针对当前这个员工对象（刚生成 ID 的 emp），
                 把他的所有工作经历（exprList 里的每一个 EmpExpr 对象）
                 ，都打上「属于这个员工」的标签（赋值 empId）。*/
                empExprMapper.insertBatch(exprList);

            }
        } finally {
            EmpLog empLog=new EmpLog(null,LocalDateTime.now(),"新增员工"+emp);
            empLogService.insertLog(empLog);

        }


    }
//根据ID删除员工
    @Transactional(rollbackFor={Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //删除员工基本经历
        empMapper.deleteByIds(ids);
        //删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);

//        在方法上ALT回车再回车，可自动生成xml


    }

    @Override
    public Emp getInfo(Integer id) {

        return empMapper.getById(id);
    }

//修改员工信息
    @Transactional(rollbackFor={Exception.class})
    @Override
    public void update(Emp emp) {
//        1.根据ID修改员工信息
        emp.setUpdateTime(LocalDateTime.now());//因为此时前段传递过来的是查询回显时候的UpdateTime
        empMapper.updateById(emp);
//        2.根据员工ID删除原有工作经历，再添加新的工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));//调用之前的方法，转为集合
        //从员工对象中提取前端传的最新工作经历列表
        List<EmpExpr> exprList=emp.getExprList();
        //判空：避免空列表插入导致数据库异常
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr->empExpr.setEmpId(emp.getId()));
            //遍历集合并为里面每个对象赋值员工ID( 1. 防御性编程：覆盖前端传入的empId，避免前端传错/篡改ID导致数据归属错误  2. 统一兜底：兼容前端漏传/传临时empId的场景，确保入库的empId绝对正确）
            empExprMapper.insertBatch(exprList);
        }


    }

    /**
     * 获取全部员工信息（下拉框）
     * @return
     */
    @Override
    public List<Emp> getlist() {
        List<Emp> emp=empMapper.getlist();
        return emp;
    }

    /**
     * 员工登录
     * @param emp
     * @return
     */
    @Override
    public LoginInfo login(Emp emp) {
        //1.调用Mapper接口，根据用户名查询员工信息
        Emp e=empMapper.selectByUsernameAndPassword(emp);

        //2.判断：判断是否存在这个员工，若存在，组装登录成功信息
        if(e!=null){
            log.info("登陆成功，员工信息：{}",e);
            //生成JWT令牌
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String jwt=JwtUtils.generateJwt(claims);//将生成的JWT令牌返回前端
            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);
/*这里用emp接而不直接用loginInfo只因为LoginInfo是一个业务层DTO，
不应该承担数据库映射的职责，否则会导致数据访问层和业务层耦合，代码难以维护
e.g:直接用 LoginInfo 接 SQL → 登录响应数据要改
→ 必须改 Mapper 层的 SQL / 返回值
→ 其他调用这个 Mapper 方法的业务会受牵连 → 系统混乱
 */
        }
        return null;
    }
}
