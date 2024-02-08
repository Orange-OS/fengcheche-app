package com.zsy.moudle.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Index;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.zsy.common.enums.MenuTypeEnum;
import com.zsy.common.enums.StatusEnum;
import com.zsy.common.enums.TopEnum;
import lombok.Data;

import java.util.Objects;

@Data
@TableName("sys_menu")
public class Menu extends BaseRecord {
    /**
     * 主键 自增
     */
    @Column(name = "id", type = MySqlTypeConstant.BIGINT, isKey = true, isAutoIncrement = true, comment = "ID")
    @TableId(type = IdType.AUTO)
    protected Long id;
    /**
     * 父菜单ID，一级菜单为0
     */
    @Index
    @Column(name = "parent_id", type = MySqlTypeConstant.BIGINT, comment = "父菜单ID")
    private Long parentId;
    /**
     * 菜单名称
     */
    @Column(name = "name", type = MySqlTypeConstant.VARCHAR, length = 30, comment = "菜单名称")
    private String name;
    /**
     * 菜单URL
     */
    @Column(name = "url", type = MySqlTypeConstant.VARCHAR, length = 200, comment = "菜单URL")
    private String url;
    /**
     * 菜单授权码
     */
    @Column(name = "code", type = MySqlTypeConstant.VARCHAR, length = 50, comment = "菜单授权码")
    private String code;
    /**
     * 菜单路径
     */
    @Column(name = "path", type = MySqlTypeConstant.VARCHAR, length = 50, comment = "菜单路径")
    private String path;
    /**
     * 类型
     */
    @Column(name = "type", type = MySqlTypeConstant.INT, comment = "类型")
    private MenuTypeEnum type;
    /**
     * 菜单图标
     */
    @Column(name = "icon", type = MySqlTypeConstant.VARCHAR, length = 30, comment = "菜单图标")
    private String icon;
    /**
     * 序号
     */
    @Column(name = "serial_no", type = MySqlTypeConstant.INT, comment = "序号")
    private Integer serialNo;
    /**
     * 菜单状态
     */
    @Column(name = "status", type = MySqlTypeConstant.INT, comment = "菜单状态")
    private StatusEnum status;
    /**
     * 是否在顶栏展示
     */
    @Column(name = "top", type = MySqlTypeConstant.INT, comment = "是否在顶栏展示")
    private TopEnum top;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Menu menu = (Menu) o;
        return this.getId().equals(menu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
