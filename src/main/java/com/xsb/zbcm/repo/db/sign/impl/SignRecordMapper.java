package com.xsb.zbcm.repo.db.sign.impl;

import com.xsb.zbcm.repo.db.sign.entity.SignRecord;

public interface SignRecordMapper {
    int insert(SignRecord record);

    int insertSelective(SignRecord record);
}