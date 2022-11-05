package com.xjjlearning.alibaba.cloud.seata.order.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@TableName("t_order")
public class TOrder{
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String orderNo;
    private String userId;
    private String commodityCode;
    private Integer count;
    private Double amount;
}
