package com.apollo.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author ：apollo
 * @since ：Created in 2019/2/23
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DsContextHolder.getDataSourceType();
    }
}
