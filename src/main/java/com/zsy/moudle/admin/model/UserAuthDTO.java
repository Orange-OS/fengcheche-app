package com.zsy.moudle.admin.model;

import lombok.Data;

import java.util.List;

@Data
public class UserAuthDTO {
    private Long userId;
    private String username;
    private Long deptId;
    private String deptName;
    private Long divisionId;
    private String divisionName;
    private boolean admin;
    /**
     * 权限列表
     */
    private List<String> authList;

    /**
     * 资源组
     */
    private String tenant;
}

