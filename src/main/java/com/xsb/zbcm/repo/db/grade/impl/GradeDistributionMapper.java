package com.xsb.zbcm.repo.db.grade.impl;

import com.xsb.zbcm.repo.db.grade.entity.GradeDistribution;

public interface GradeDistributionMapper {
    int insert(GradeDistribution record);

    int insertSelective(GradeDistribution record);
}