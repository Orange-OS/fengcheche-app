package com.zsy.moudle.admin.service.impl;

import com.zsy.moudle.admin.entity.User;
import com.zsy.moudle.admin.mapper.UserMapper;
import com.zsy.moudle.admin.service.AbstractService;
import com.zsy.moudle.admin.service.IMenuService;
import com.zsy.moudle.admin.service.IRoleService;
import com.zsy.moudle.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends AbstractService<UserMapper, User> implements IUserService {
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @Override
    public boolean isAdminUser(Long userId) {
        return false;
    }

    @Override
    public List<String> getAuthList(Long userId) {
        return null;
    }
}
