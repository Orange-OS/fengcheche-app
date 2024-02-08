package com.zsy.moudle.admin.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.zsy.common.enums.RoleTypeEnum;
import com.zsy.common.enums.StatusEnum;
import lombok.Data;

/**
 * 角色
 *
 * @author Mayday
 */

@Data
@TableName("sys_role")
public class Role extends BaseRecord {
    /**
     * 主键 自增
     */
    @Column(name = "id", type = MySqlTypeConstant.BIGINT, isKey = true, isAutoIncrement = true, comment = "ID")
    @TableId(type = IdType.AUTO)
    protected Long id;
    /**
     * 角色名称
     */
    @Column(name = "role_name", type = MySqlTypeConstant.VARCHAR, length = 20, comment = "角色名称")
    private String roleName;

    /**
     * 角色类型
     */
    @Column(name = "admin", type = MySqlTypeConstant.INT, comment = "角色类型")
    private RoleTypeEnum admin;

    /**
     * 备注
     */
    @Column(name = "remark", type = MySqlTypeConstant.VARCHAR, length = 100, comment = "备注")
    private String remark;

    /**
     * 状态
     */
    @Column(name = "status", type = MySqlTypeConstant.INT, comment = "状态")
    private StatusEnum status;

}

