package com.xsb.zbcm.repo.db.pushinfo.impl;

import com.xsb.zbcm.repo.db.pushinfo.entity.PushMessage;

public interface PushMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PushMessage record);

    int insertSelective(PushMessage record);

    PushMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PushMessage record);

    int updateByPrimaryKeyWithBLOBs(PushMessage record);

    int updateByPrimaryKey(PushMessage record);
}