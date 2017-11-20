package com.xsb.zbcm.repo.db.pushinfo.impl;

import com.xsb.zbcm.repo.db.pushinfo.entity.MyTest;

public interface MyTestMapper {
    int insert(MyTest record);

    int insertSelective(MyTest record);
}