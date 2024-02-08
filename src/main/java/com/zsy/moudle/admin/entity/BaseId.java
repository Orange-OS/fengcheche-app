package com.zsy.moudle.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * 自增ID父类
 */
@Data
public abstract class BaseId implements Serializable {
    /**
     * 主键 自增
     */
    @Column(name = "id", type = MySqlTypeConstant.BIGINT, isKey = true, comment = "ID")
    @TableId(type = IdType.ASSIGN_ID)
    protected Long id;
}
