package com.apollo.config.datasource;

/**
 * @author ：apollo
 * @since ：Created in 2019/2/23
 */
public enum DataSourceEnum {
    PG("PG"), MYSQL("MYSQL");

    private String value;

    DataSourceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
