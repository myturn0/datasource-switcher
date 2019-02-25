package com.apollo.service.impl;

import com.apollo.config.datasource.DataSourceEnum;
import com.apollo.config.datasource.DsSwitcher;
import com.apollo.dao.ProvinceDao;
import com.apollo.entity.Province;
import com.apollo.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：apollo
 * @since ：Created in 2019/2/23
 */
@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDao provinceDao;

    @DsSwitcher(DataSourceEnum.MYSQL)
    @Override
    public List<Province> getAllProvince() {
        return provinceDao.selectAll();
    }
}
