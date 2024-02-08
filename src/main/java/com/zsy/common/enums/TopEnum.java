package com.zsy.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.zsy.common.mybatis.IBaseEnum;

/**
 * 菜单类型枚举类
 */
public enum TopEnum implements IBaseEnum<Integer> {
    DISABLE(0, "禁用"), SIGNBOARD(1, "实时看板"), CALL(2, "叫应中心"), VOICE(3, "声讯中心"), FAX(4, "传真中心"), SYSTEM(5, "系统管理");
    private int value;
    private String name;

    TopEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @JsonCreator
    public static TopEnum getEnum(int value) {
        for (TopEnum e : values()) {
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
        return name;
    }
}

