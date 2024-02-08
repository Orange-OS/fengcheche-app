package com.zsy.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.zsy.common.mybatis.IBaseEnum;

/**
 * 菜单类型枚举类
 */
public enum MenuTypeEnum implements IBaseEnum<Integer> {
    FOLDER(0, "目录"), MENU(1, "菜单"), BUTTON(2, "按钮"), LINK(3, "链接"), BLANK(4, "新窗口");
    private int value;
    private String name;

    MenuTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @JsonCreator
    public static MenuTypeEnum getEnum(int value) {
        for (MenuTypeEnum e : values()) {
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
