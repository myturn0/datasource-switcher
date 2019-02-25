package com.apollo.dao;

import com.apollo.entity.Week;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：apollo
 * @since ：Created in 2019/2/23
 */
@Repository
public interface WeekDao {
    /**
     * 获取所有数据
     */
    List<Week> selectAll();
}
