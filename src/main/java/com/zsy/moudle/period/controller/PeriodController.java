package com.zsy.moudle.period.controller;

import com.zsy.common.request.PageInfo;

import com.zsy.moudle.period.entity.Period;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/period")
public class PeriodController {
    @RequestMapping("/page")
    public String page(@RequestBody PageInfo<Period> pageInfo) {
        System.out.println(pageInfo.getSize());
        return "page";
    }

    @GetMapping("/test")
    public String test() {
        return "R.success()";
    }
 }
