package com.xjjlearning.alibaba.cloud.seata.stock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjjlearning.alibaba.cloud.seata.stock.model.TStock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TStockMapper extends BaseMapper<TStock> {

    /**
     * 减扣商品库存
     * @param commodityCode
     * @param count
     * @return
     */
    @Update("update t_stock set count = count - #{count} where commodity_code = #{commodityCode}")
    int decreaseStock(String commodityCode, Integer count);

}
