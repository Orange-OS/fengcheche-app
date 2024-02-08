package com.zsy.moudle.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsy.moudle.admin.entity.Role;

import java.util.List;

public interface IRoleService extends IService<Role> {
    List<Role> getUserEnableRoleList(Long userId);
}
