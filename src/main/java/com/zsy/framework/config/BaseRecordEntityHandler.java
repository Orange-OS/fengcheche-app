package com.zsy.framework.config;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zsy.common.utils.PublicUtil;
import com.zsy.framework.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class BaseRecordEntityHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        UserInfo userInfo = PublicUtil.getUserInfo();
        Long userId = userInfo.getUserId();
        Long deptId = userInfo.getDeptId();

        if (userId != null) {
            this.strictInsertFill(metaObject, "createUser", Long.class, userId);
            this.strictInsertFill(metaObject, "deptId", Long.class, deptId);
        }
        this.strictInsertFill(metaObject, "createTime", Date.class, DateUtil.date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, DateUtil.date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update  fill ....");
        this.strictInsertFill(metaObject, "updateTime", Date.class, DateUtil.date());
    }
}
