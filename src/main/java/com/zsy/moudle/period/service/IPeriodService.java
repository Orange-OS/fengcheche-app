package com.zsy.moudle.period.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsy.common.request.PageInfo;
import com.zsy.common.response.PageModel;
import com.zsy.framework.model.UserInfo;
import com.zsy.moudle.period.entity.Period;
import com.zsy.moudle.period.model.PeriodDTO;

public interface IPeriodService extends IService<Period> {
    PageModel<Period> pagePeriod(PageInfo<PeriodDTO> pageInfo, UserInfo userInfo);

    void addPeriod(Period period, UserInfo userInfo);

    boolean delPeriod(Long id);
}

