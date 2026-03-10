package com.ayanami.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmpQueryParam {
    private Integer page=1;
    private Integer PageSize=10;
    private String name;
    private Integer gender;
    /*由于前端传入的日期字符串类型不定，所以添加该注释将前端传入日期
    自动转换成 Java 的 Date/LocalDate 类型*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate begin;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate end;
}
