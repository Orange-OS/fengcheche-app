package com.zsy.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一响应类
 *
 * @author Mayday
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseModel<T> implements Serializable {
    private ResponseCodeEnum code;
    private String msg;
    private T data;
    @JsonProperty("actionID")
    private String actionId;

    public static ResponseModel success() {
        return ResponseModel.builder().code(ResponseCodeEnum.OK).msg(ResponseCodeEnum.OK.getName()).build();
    }

    public static ResponseModel success(String msg) {
        return ResponseModel.builder().code(ResponseCodeEnum.OK).msg(msg).build();
    }

    public static ResponseModel success(Object data) {
        return success(data, ResponseCodeEnum.OK.getName());
    }

    public static ResponseModel successId(Object data, String actionId) {
        return success(data, ResponseCodeEnum.OK.getName(), actionId);
    }

    public static ResponseModel success(Object data, String msg) {
        return ResponseModel.builder().data(data).msg(msg).code(ResponseCodeEnum.OK).build();
    }

    public static ResponseModel success(Object data, String msg, String actionId) {
        return ResponseModel.builder().data(data).msg(msg).code(ResponseCodeEnum.OK).actionId(actionId).build();
    }

    public static ResponseModel fail(String msg) {
        return fail(ResponseCodeEnum.BUS_EXCEPTION, msg);
    }

    public static ResponseModel fail(ResponseCodeEnum code) {
        return fail(code, code.getName());
    }

    public static ResponseModel fail(ResponseCodeEnum code, String msg) {
        return ResponseModel.builder().msg(msg).code(code).build();
    }

    public static ResponseModel fail(String msg, String actionId) {
        return fail(ResponseCodeEnum.BUS_EXCEPTION, msg, actionId);
    }

    public static ResponseModel fail(ResponseCodeEnum code, String msg, String actionId) {
        return ResponseModel.builder().msg(msg).code(code).actionId(actionId).build();
    }

}
