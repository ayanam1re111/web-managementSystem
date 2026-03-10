package com.ayanami.exception;

import com.ayanami.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("程序出错了",e);
        return Result.error("---出错了---");

    }
@ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("程序出错了",e);
        String message=e.getMessage();
        int i=message.indexOf("Duplicate entry");
        String errMsg=message.substring(i);
        String[] arr=errMsg.split(" ");
        return Result.error(arr[2]+"已存在");

    }
    @ExceptionHandler(NumException.class)
    public Result handleNumException(NumException e){
        // 记录日志(获取抛出异常时写的提示语）
        log.error("业务异常: {}", e.getMessage(), e);
        // 返回统一的错误响应
        return Result.error(e.getMessage());
    }

}
