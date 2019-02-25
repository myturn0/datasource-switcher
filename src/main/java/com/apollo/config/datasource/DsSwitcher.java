package com.apollo.config.datasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：apollo
 * @since ：Created in 2019/2/23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface DsSwitcher {
    //默认数据源是mysql
    DataSourceEnum value() default DataSourceEnum.MYSQL;
}
