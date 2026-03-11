package com.ayanami.controller;

import com.ayanami.pojo.LogQueryParam;
import com.ayanami.pojo.OperateLog;
import com.ayanami.pojo.PageResult;
import com.ayanami.pojo.Result;
import com.ayanami.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/log")
@RestController
public class OperateLogController {
    @Autowired
    private OperateLogService operateLogService;

    @GetMapping("/page")
    public Result getLog(LogQueryParam logQueryParam){
        log.info("分页查询操作日志：{}",logQueryParam);
        PageResult<OperateLog> page=operateLogService.page(logQueryParam);
        return Result.success(page);

    }
}
