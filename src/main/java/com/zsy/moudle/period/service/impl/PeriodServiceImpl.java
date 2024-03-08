package com.zsy.moudle.period.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zsy.common.request.PageInfo;
import com.zsy.common.response.PageModel;
import com.zsy.framework.model.UserInfo;
import com.zsy.moudle.period.entity.Period;
import com.zsy.moudle.period.mapper.PeriodMapper;
import com.zsy.moudle.period.model.PeriodDTO;
import com.zsy.moudle.period.service.AbstractService;
import com.zsy.moudle.period.service.IPeriodService;
import org.springframework.stereotype.Service;

@Service
public class PeriodServiceImpl extends AbstractService<PeriodMapper, Period> implements IPeriodService {

    @Override
    public PageModel<Period> pagePeriod(PageInfo<PeriodDTO> pageInfo, UserInfo userInfo) {
        PeriodDTO condition = pageInfo.getCondition();
        PageModel<Period> page = this.page(pageInfo, new LambdaQueryWrapper<Period>()
                .ge(StrUtil.isNotEmpty(condition.getStartTime()), Period::getPeriod, condition.getStartTime())
                .lt(StrUtil.isNotEmpty(condition.getEndTime()), Period::getPeriod, condition.getEndTime())
                .orderByDesc(Period::getPeriod)
        );
        return page;
    }

    @Override
    public void addPeriod(Period period, UserInfo userInfo) {
        saveOrUpdate(period);
    }

    @Override
    public boolean delPeriod(Long id) {
        return this.removeById(id);
    }
}
