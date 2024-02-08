package com.zsy.moudle.admin.service.impl;

import com.zsy.moudle.admin.entity.Role;
import com.zsy.moudle.admin.mapper.RoleMapper;
import com.zsy.moudle.admin.service.AbstractService;
import com.zsy.moudle.admin.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends AbstractService<RoleMapper, Role> implements IRoleService {
    @Override
    public List<Role> getUserEnableRoleList(Long userId) {
        return null;
    }
}
