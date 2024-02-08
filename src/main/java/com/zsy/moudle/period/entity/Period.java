package com.zsy.moudle.period.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "period")
public class Period {
    /**
     * 主键 自增
     */
    @Column(name = "id", type = MySqlTypeConstant.BIGINT, isKey = true, isAutoIncrement = true, comment = "ID")
    @TableId(type = IdType.AUTO)
    protected Long id;

    @Column(name = "period", type = MySqlTypeConstant.DATE, comment = "生理期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date period;

    @Column(name = "notes", type = MySqlTypeConstant.VARCHAR, comment = "备注", length = 50)
    private String notes;

    @Column(name = "create_time", type = MySqlTypeConstant.DATETIME, comment = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Column(name = "update_time", type = MySqlTypeConstant.DATETIME, comment = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
