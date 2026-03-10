package com.ayanami.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//限定这个自定义注解可以用在哪些目标元素上
@Retention(RetentionPolicy.RUNTIME)//控制这个注解的生命周期，也就是它在哪个阶段还能被读取到。
public @interface Log {
}
