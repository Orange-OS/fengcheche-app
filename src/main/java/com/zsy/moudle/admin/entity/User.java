package com.zsy.moudle.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Unique;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.zsy.common.enums.StatusEnum;
import lombok.Data;

/**
 * 系统用户
 *
 * @author Mayday
 */

@Data
@TableName("sys_user")
public class User extends BaseDept {
    /**
     * 主键 自增
     */
    @Column(name = "id", type = MySqlTypeConstant.BIGINT, isKey = true, isAutoIncrement = true, comment = "ID")
    @TableId(type = IdType.AUTO)
    protected Long id;
    /**
     * 用户名
     */
    @Unique
    @Column(name = "username", type = MySqlTypeConstant.VARCHAR, comment = "用户名", length = 50)
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password", type = MySqlTypeConstant.CHAR, comment = "密码", length = 32)
    private String password;

    /**
     * oauth密码
     */
    @Column(type = MySqlTypeConstant.VARCHAR, comment = "oauth密码", length = 60)
    private String oauthPass;

    /**
     * 邮箱
     */
    @Column(name = "email", type = MySqlTypeConstant.CHAR, comment = "邮箱", length = 100)
    private String email;
    /**
     * 手机号
     */
    @Column(name = "mobile", type = MySqlTypeConstant.VARCHAR, comment = "手机号", length = 13)
    private String mobile;
    /**
     * 盐
     */
    @Column(name = "salt", type = MySqlTypeConstant.CHAR, comment = "salt", length = 6)
    private String salt;
    /**
     * 状态  0：禁用   1：正常
     */
    @Column(name = "status", type = MySqlTypeConstant.INT, comment = "状态")
    private StatusEnum status;

}
