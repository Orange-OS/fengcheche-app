package com.zsy.moudle.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Index;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

/**
 * 部门父类
 */
@Data
public abstract class BaseDept extends BaseRecord {
    /**
     * 部门ID
     */
    @Index
    @Column(name = "dept_id", type = MySqlTypeConstant.BIGINT, comment = "部门ID")
    @TableField(fill = FieldFill.INSERT)
    private Long deptId;
}
