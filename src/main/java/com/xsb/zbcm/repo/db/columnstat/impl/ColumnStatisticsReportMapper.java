package com.xsb.zbcm.repo.db.columnstat.impl;

import com.xsb.zbcm.repo.db.columnstat.entity.ColumnStatisticsReport;

public interface ColumnStatisticsReportMapper {
    int deleteByPrimaryKey(Integer columnId);

    int insert(ColumnStatisticsReport record);

    int insertSelective(ColumnStatisticsReport record);

    ColumnStatisticsReport selectByPrimaryKey(Integer columnId);

    int updateByPrimaryKeySelective(ColumnStatisticsReport record);

    int updateByPrimaryKey(ColumnStatisticsReport record);
}