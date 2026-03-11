package com.ayanami.utils;

public class CurrentHolder {
    // 1. 线程本地存储
    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();
    // 2. 设置当前员工ID（登录/鉴权时调用）
    public static void setCurrentId(Integer employeeId) {
        CURRENT_LOCAL.set(employeeId);
    }
    // 3. 获取当前员工ID（日志切面等地方调用）
    public static Integer getCurrentId() {
        return CURRENT_LOCAL.get();
    }
    // 4. 清除线程本地变量（请求结束时调用，防止内存泄漏）
    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}
