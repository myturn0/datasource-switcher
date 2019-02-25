package com.apollo.service.impl;

import com.apollo.config.datasource.DataSourceEnum;
import com.apollo.config.datasource.DsSwitcher;
import com.apollo.dao.WeekDao;
import com.apollo.entity.Week;
import com.apollo.service.WeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：apollo
 * @since ：Created in 2019/2/23
 */
@Service
public class WeekServiceImpl implements WeekService {

    @Autowired
    private WeekDao weekDao;

    @DsSwitcher(DataSourceEnum.PG)
    @Override
    public List<Week> getAllWork() {
        return weekDao.selectAll();
    }
}
