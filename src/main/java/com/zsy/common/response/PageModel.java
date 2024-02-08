package com.zsy.common.response;

import lombok.Data;

import java.util.List;

/**
 * 分页列表结果
 *
 * @param <T>
 */
@Data
public class PageModel<T> {
    /**
     * 当前页
     */
//    @JsonSerialize(using = LongJsonSerializer.class)
//    @JsonDeserialize(using = LongJsonDeserializer.class)
    private long current;
    /**
     * 每页数目
     */
//    @JsonSerialize(using = LongJsonSerializer.class)
//    @JsonDeserialize(using = LongJsonDeserializer.class)
    private long size;
    /**
     * 总记录数
     */
//    @JsonSerialize(using = LongJsonSerializer.class)
//    @JsonDeserialize(using = LongJsonDeserializer.class)
    private long total;
    /**
     * 总页数
     */
//    @JsonSerialize(using = LongJsonSerializer.class)
//    @JsonDeserialize(using = LongJsonDeserializer.class)
    private long pages;
    /**
     * 记录列表
     */
    private List<T> list;
}
