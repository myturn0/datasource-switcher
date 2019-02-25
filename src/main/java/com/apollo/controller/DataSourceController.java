package com.apollo.controller;

import com.apollo.common.ApiResult;
import com.apollo.entity.Province;
import com.apollo.entity.Week;
import com.apollo.service.ProvinceService;
import com.apollo.service.WeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：apollo
 * @since ：Created in 2019/2/23
 */
@RestController
@RequestMapping(value = "/datasource")
public class DataSourceController {

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private WeekService weekService;

    @GetMapping("/provinces")
    public ApiResult getProvinces() {
//        DsContextHolder.setDataSourceType(DataSourceEnum.MYSQL);
        List<Week> weeks = weekService.getAllWork();
        System.out.println(weeks.size());
        List<Province> provinces = provinceService.getAllProvince();
//        DsContextHolder.clearDataSourceType();
        return ApiResult.builder()
                .status(ApiResult.STATUS_SUCCESS)
                .desc("获取成功")
                .result(provinces)
                .build();
    }

    @GetMapping("/weeks")
    public ApiResult getWeeks() {
        return ApiResult.builder()
                .status(ApiResult.STATUS_SUCCESS)
                .desc("获取成功")
                .result(weekService.getAllWork())
                .build();
    }
}
