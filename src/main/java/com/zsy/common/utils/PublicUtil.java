package com.zsy.common.utils;

import com.zsy.framework.model.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 公共工具类
 */
@Component
public class PublicUtil implements InitializingBean {
    private static PublicUtil instance;

    /**
     * 获取当前用户信息
     */
    public static UserInfo getUserInfo() {
        return JwtUtil.getUser(getToken());
    }


    /**
     * 获取当前登录用户ID
     */
    public static Long getUserId() {
        UserInfo userInfo = getUserInfo();
        if (userInfo != null) {
            return userInfo.getUserId();
        }
        return null;
    }

    /**
     * 获取当前登录用户Token
     */
    public static String getToken() {
        return SecurityUtils.getSubject().getPrincipal().toString();
    }

    @Override
    public void afterPropertiesSet() {
        instance = this;
    }
}
