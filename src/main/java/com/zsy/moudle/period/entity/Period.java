package com.zsy.moudle.period.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.zsy.moudle.admin.entity.BaseDept;
import com.zsy.moudle.admin.entity.BaseRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "period")
public class Period extends BaseDept {
    @Column(name = "period", type = MySqlTypeConstant.DATE, comment = "生理期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date period;

    @Column(name = "note", type = MySqlTypeConstant.VARCHAR, comment = "备注", length = 50)
    private String note;
}
