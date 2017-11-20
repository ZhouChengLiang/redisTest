package com.xsb.zbcm.repo.db.userreadlog.impl;

import com.xsb.zbcm.repo.db.userreadlog.entity.UserKeepArticle;

public interface UserKeepArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserKeepArticle record);

    int insertSelective(UserKeepArticle record);

    UserKeepArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserKeepArticle record);

    int updateByPrimaryKey(UserKeepArticle record);
}