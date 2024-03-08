package com.zsy.moudle.period.controller;

import com.zsy.common.request.PageInfo;
import com.zsy.common.response.PageModel;
import com.zsy.common.response.ResponseModel;
import com.zsy.common.utils.PublicUtil;
import com.zsy.moudle.period.entity.Period;
import com.zsy.moudle.period.model.PeriodDTO;
import com.zsy.moudle.period.service.IPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/period")
public class PeriodController {
    @Autowired
    private IPeriodService periodService;

    @RequestMapping("/page")
    public ResponseModel page(@RequestBody PageInfo<PeriodDTO> pageInfo) {
        PageModel<Period> page = periodService.pagePeriod(pageInfo, PublicUtil.getUserInfo());
        return ResponseModel.success(page);
    }

    @PostMapping("/add")
    public ResponseModel add(@RequestBody Period period) {
        periodService.addPeriod(period, PublicUtil.getUserInfo());
        return ResponseModel.success();
    }

    @GetMapping("/del/{id}")
    public ResponseModel del(@PathVariable Long id) {
        periodService.delPeriod(id);
        return ResponseModel.success();
    }
 }
