package com.zsy.moudle.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsy.moudle.admin.entity.User;

import java.util.List;

public interface IUserService extends IService<User> {
    boolean isAdminUser(Long userId);

    List<String> getAuthList(Long userId);
}
