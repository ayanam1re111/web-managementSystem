package com.ayanami.service;

import com.ayanami.pojo.LogQueryParam;
import com.ayanami.pojo.OperateLog;
import com.ayanami.pojo.PageResult;

public interface OperateLogService {
    PageResult<OperateLog> page(LogQueryParam logQueryParam);
}
