package com.ayanami.service;

import com.ayanami.pojo.Clazz;
import com.ayanami.pojo.ClazzQueryParam;
import com.ayanami.pojo.ClazzResult;

import java.util.List;

public interface ClazzService {
    ClazzResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void save(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    void delete(Integer id);

    List<Clazz> list();
}
