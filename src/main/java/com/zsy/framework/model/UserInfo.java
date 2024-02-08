package com.zsy.framework.model;

import lombok.Data;

/**
 * 用户信息
 */
@Data
public class UserInfo {
    private Long userId;
    private String userName;
    private Long deptId;
    private Long unitId;
    private boolean admin;
}
