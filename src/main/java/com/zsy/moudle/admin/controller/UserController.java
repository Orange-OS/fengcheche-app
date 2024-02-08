package com.zsy.moudle.admin.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zsy.common.enums.StatusEnum;
import com.zsy.common.response.ResponseModel;
import com.zsy.common.utils.JwtUtil;
import com.zsy.common.utils.PublicUtil;
import com.zsy.framework.exception.BusinessException;
import com.zsy.framework.model.UserInfo;
import com.zsy.moudle.admin.entity.User;
import com.zsy.moudle.admin.model.TokenDTO;
import com.zsy.moudle.admin.model.UserAuthDTO;
import com.zsy.moudle.admin.model.UserDTO;
import com.zsy.moudle.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractAdminController {
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseModel login(@RequestBody UserDTO user) {
        User existUser = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (existUser == null) {
            throw new BusinessException("用户名或密码错误");
        }

        String password = SecureUtil.md5(user.getPassword() + (StrUtil.isEmpty(existUser.getSalt()) ? "" : existUser.getSalt()));
        if (password.equals(existUser.getPassword())) {
            if (existUser.getStatus() == StatusEnum.DISABLE) {
                throw new BusinessException("用户已禁用");
            }

            boolean isAdmin = userService.isAdminUser(existUser.getId());
            String token = JwtUtil.sign(existUser.getId(), existUser.getUsername(), isAdmin, existUser.getDeptId(), existUser.getPassword());
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);
            return ResponseModel.success(tokenDTO);
        } else {
            throw new BusinessException("用户名或密码错误");
        }
    }

    @GetMapping("/info")
    public ResponseModel info() {
        UserInfo userInfo = PublicUtil.getUserInfo();
        UserAuthDTO userAuthDTO = new UserAuthDTO();
        userAuthDTO.setUserId(userInfo.getUserId());
        userAuthDTO.setUsername(userInfo.getUserName());
        userAuthDTO.setAdmin(userInfo.isAdmin());
        userAuthDTO.setDeptId(userInfo.getDeptId());

        return ResponseModel.success(userAuthDTO);
    }
 }
