package com.ayanami.service;

import com.ayanami.mapper.EmpMapper;
import com.ayanami.pojo.JobOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    /**
     * 统计员工职位
     * @return
     */
    @Override
    public JobOption getEmpJobData() {
        //1.调用Mapper接口，获取统计数据
        List<Map<String,Object>> list=empMapper.countEmpJobData();//map: pos=教研主管，num=2
        //2.组装结果，并返回
        List<Object> jobList=list.stream().map(dataMap ->dataMap.get("pos")).toList();
        List<Object> numberList=list.stream().map(dataMap ->dataMap.get("number")).toList();
        return new JobOption(jobList,numberList);
    }

    /**
     * 统计员工性别
     * @return
     */
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }
}
