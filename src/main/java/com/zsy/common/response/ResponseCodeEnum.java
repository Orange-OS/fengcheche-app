package com.zsy.common.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.zsy.common.mybatis.IBaseEnum;


/**
 * 响应业务码枚举类
 */
public enum ResponseCodeEnum implements IBaseEnum<Integer> {
    OK(0, "操作成功"),
    FAIL(-1, "操作失败"),
    TOKEN_EXCEPTION(10001, "认证失败"),
    AUTH_EXCEPTION(10002, "无权操作"),
    CAPTCHA_EXCEPTION(10003, "验证码错误"),
    BUS_EXCEPTION(20001, "业务异常");


    private int value;
    private String name;

    ResponseCodeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @JsonCreator
    public static ResponseCodeEnum getEnum(int value) {
        for (ResponseCodeEnum e : values()) {
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
