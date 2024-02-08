package com.zsy.moudle.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;

/**
 * 带留痕父类
 */
@Data
public abstract class BaseRecord extends BaseId {
    @Column(name = "create_user", type = MySqlTypeConstant.BIGINT, comment = "最后操作用户ID")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected Long createUser;

    @Column(name = "create_time", type = MySqlTypeConstant.DATETIME, comment = "最后操作时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected Date createTime;
}
