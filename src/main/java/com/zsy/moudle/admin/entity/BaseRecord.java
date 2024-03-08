package com.zsy.moudle.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;

/**
 * 带留痕父类
 */
@Data
public abstract class BaseRecord extends BaseId {
    @Column(name = "create_user", type = MySqlTypeConstant.BIGINT, comment = "创建用户")
    @TableField(fill = FieldFill.INSERT)
    protected Long createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time", type = MySqlTypeConstant.DATETIME, comment = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    protected Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time", type = MySqlTypeConstant.DATETIME, comment = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected Date updateTime;
}
