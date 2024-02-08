package com.zsy.moudle.admin.model;

import com.zsy.common.enums.StatusEnum;
import com.zsy.framework.validation.InsertAction;
import com.zsy.framework.validation.LoginAction;
import com.zsy.framework.validation.UpdateAction;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    private String username;
    /**
     * 密码
     */
    @NotNull(groups = {LoginAction.class, InsertAction.class}, message = "密码不能为空")
    private String password;
    /**
     * 邮箱
     */
    @NotNull(groups = {InsertAction.class, UpdateAction.class}, message = "邮箱不能为空")
    private String email;
    /**
     * 手机号
     */
    @NotNull(groups = {InsertAction.class, UpdateAction.class}, message = "手机号不能为空")
    private String mobile;
    /**
     * 状态  0：禁用   1：正常
     */
    @NotNull(groups = {InsertAction.class, UpdateAction.class}, message = "用户状态不能为空")
    private StatusEnum status;
    /**
     * 验证码token
     */
    @NotNull(groups = {LoginAction.class}, message = "验证码不能为空")
    private String captchaToken;
    /**
     * 验证码
     */
    @NotNull(groups = {LoginAction.class}, message = "验证码不能为空")
    private String captcha;
}
