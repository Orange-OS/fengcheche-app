package com.zsy.common.request;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class PageInfo<T> {
    /**
     * 当前页
     */
    private long current = 1;
    /**
     * 每页数目
     */
    private long size = 10;
    /**
     * 查询条件
     */
    private T condition;

    private List<OrderItem> orders;

    @JsonIgnore
    public List<OrderItem> getOrders() {
        return orders;
    }
}
