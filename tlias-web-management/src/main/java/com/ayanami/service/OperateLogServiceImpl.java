package com.ayanami.service;

import com.ayanami.mapper.OperateLogMapper;
import com.ayanami.pojo.LogQueryParam;
import com.ayanami.pojo.OperateLog;
import com.ayanami.pojo.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class OperateLogServiceImpl implements OperateLogService{
   @Autowired
   private OperateLogMapper operateLogMapper;

    @Override
    public PageResult<OperateLog> page(LogQueryParam logQueryParam) {
        PageHelper.clearPage();
        PageHelper.startPage(logQueryParam.getPage(),logQueryParam.getPageSize());
        List<OperateLog> operateLogList=operateLogMapper.selectAll();
        log.info("查询到{}条数据",operateLogList.size());

        Page<OperateLog> logPage=(Page<OperateLog>)operateLogList;

        return new PageResult<OperateLog>(logPage.getTotal(),logPage.getResult());
    }
}
