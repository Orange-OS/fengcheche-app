package com.zsy.framework;

import cn.hutool.core.util.StrUtil;
import com.zsy.common.utils.JwtUtil;
import com.zsy.common.utils.PublicUtil;
import com.zsy.framework.model.UserInfo;
import com.zsy.framework.shiro.JwtToken;
import com.zsy.moudle.admin.entity.User;
import com.zsy.moudle.admin.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class FengCheCheRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    private IUserService userService;

    /**
     * 必须重写此方法，不然 Shiro 会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 用户授权逻辑
     * 用户登入后自动调用该方法，获取权限相关数据赋值给当前用户
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<String> authList = userService.getAuthList(PublicUtil.getUserId());
        Set<String> permission = new HashSet<>(authList);
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }


    /**
     * 用户认证逻辑
     * 用户登入时自动调用该方法进行认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        UserInfo userInfo = JwtUtil.getUser(token);

        if (userInfo == null || StrUtil.isEmpty(userInfo.getUserName())) {
            throw new AuthenticationException("认证失败");
        }

        User user = userService.getById(userInfo.getUserId());
        if (user == null) {
            throw new AuthenticationException("认证失败");
        }

        if (!JwtUtil.verify(userInfo.getUserId(), userInfo.getUserName(), userInfo.isAdmin(), userInfo.getDeptId(), user.getPassword(), token)) {
            throw new AuthenticationException("认证失败");
        }

        return new SimpleAuthenticationInfo(token, token, this.getName());
    }


}

