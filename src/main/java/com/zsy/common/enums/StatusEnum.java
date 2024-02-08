package com.zsy.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.zsy.common.mybatis.IBaseEnum;

/**
 * 启用状态枚举类
 */
public enum StatusEnum implements IBaseEnum<Integer> {
    ENABLE(1, "启用"), DISABLE(0, "禁用");

    private int value;
    private String name;

    StatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @JsonCreator
    public static StatusEnum getEnum(int value) {
        for (StatusEnum e : values()) {
            if (e.getValue() == value) {
                return e;
            }
        }
        return null;
    }

    @Override
    @JsonValue
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static StatusEnum getEnumByName(String value) {
        for (StatusEnum e : values()) {
            if (e.getName().equals(value)) {
                return e;
            }
        }
        return null;
    }
}
