package com.zsy.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.zsy.common.mybatis.IBaseEnum;

/**
 * 角色类型枚举
 */
public enum RoleTypeEnum implements IBaseEnum<Integer> {
    COMMON(0, "普通角色"), ADMIN(1, "管理角色");
    private int value;
    private String name;

    RoleTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @JsonCreator
    public static RoleTypeEnum getEnum(int value) {
        for (RoleTypeEnum e : values()) {
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
}
