package com.zsy.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.zsy.framework.model.UserInfo;


import java.util.Date;


/**
 * jwt工具类
 */
public class JwtUtil {
    // Token 私钥
    private static final String SECRET = "8a80c107466a0b9c01466cfd0003";
    // Token 过期时间
    private static final int EXPIRE_MINUTE = 7 * 24 * 60;

    /**
     * 校验 Token 是否正确
     *
     * @param userId   用户ID
     * @param userName 用户名
     * @param isAdmin  是否管理员
     * @param deptId   部门ID
     * @return 是否正确
     */
    public static boolean verify(Long userId, String userName, boolean isAdmin, Long deptId, String secret, String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET + secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("userId", userId)
                    .withClaim("userName", userName)
                    .withClaim("admin", isAdmin)
                    .withClaim("deptId", deptId)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得 Token 中的信息无需 secret 解密也能获得
     *
     * @return Token 中包含的用户名
     */
    public static UserInfo getUser(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(jwt.getClaim("userId").asLong());
            userInfo.setUserName(jwt.getClaim("userName").asString());
            userInfo.setDeptId(jwt.getClaim("deptId").asLong());
            userInfo.setAdmin(jwt.getClaim("admin").asBoolean());
            return userInfo;
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名,指定时间后过期,一经生成不可修改，令牌在指定时间内一直有效
     *
     * @param userId   用户ID
     * @param userName 用户名
     * @param isAdmin  是否管理员
     * @param deptId   部门ID
     * @return 加密的token
     */
    public static String sign(Long userId, String userName, boolean isAdmin, Long deptId, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_MINUTE * 60 * 1000);
        Algorithm algorithm = Algorithm.HMAC256(SECRET + secret);
        // 增加用户信息
        return JWT.create()
                .withClaim("userId", userId)
                .withClaim("userName", userName)
                .withClaim("admin", isAdmin)
                .withClaim("deptId", deptId)
                .withExpiresAt(date)
                .sign(algorithm);
    }
}
