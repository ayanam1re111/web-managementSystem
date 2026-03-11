package com.ayanami.aop;
import com.ayanami.mapper.EmpMapper;
import com.ayanami.mapper.OperateLogMapper;
import com.ayanami.pojo.OperateLog;
import com.ayanami.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private EmpMapper empMapper;

    // 环绕通知
    @Around("@annotation(com.ayanami.anno.Log)")
    public Object logOpertion(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        // 执行方法
        Object result = joinPoint.proceed();
        // 当前时间
        long endTime = System.currentTimeMillis();
        // 耗时
        long costTime = endTime - startTime;

        // 构建日志对象
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getCurrentUserId()); // 需要实现 getCurrentUserId 方法
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());//getTarger:获取被代理的目标对象
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        operateLog.setReturnValue(result.toString());
        operateLog.setCostTime(costTime);
        operateLog.setOperateEmpName(getCurrentUserName(operateLog.getOperateEmpId()));



        log.info("记录操作日志：{}",operateLog);
        // 插入日志
        operateLogMapper.insert(operateLog);
        return result;
    }

    private String getCurrentUserName(Integer operateEmpId) {
        String currentName = empMapper.getById(operateEmpId).getUsername();
        return currentName;

    }


    // 获取当前用户ID
    private int getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }


}
