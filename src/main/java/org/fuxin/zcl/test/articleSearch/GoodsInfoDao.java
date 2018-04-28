package org.fuxin.zcl.test.articleSearch;

import java.util.List;

public interface GoodsInfoDao {
    /**
     * 插入商品信息
     * @param infos
     */
    void saveBatch(List<GoodsInfo> infos);
}