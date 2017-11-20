package com.xsb.zbcm.repo.db.userreadlog.impl;

import com.xsb.zbcm.repo.db.userreadlog.entity.RoleInfo;

public interface RoleInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);
}