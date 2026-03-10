package com.ayanami.mapper;

import com.ayanami.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//操作员工工作经历
@Mapper
public interface EmpExprMapper {
    /**
     * 批量保存员工工作经历信息
     * @param exprList
     */

    public void insertBatch(List<EmpExpr> exprList);


    void deleteByEmpIds(List<Integer> empIds);
}
