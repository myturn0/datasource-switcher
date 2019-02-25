package com.apollo.config.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author ：apollo
 * @since ：Created in 2019/2/23
 */
@Component
@Aspect
public class DsSwitcherAspect {

    @Pointcut("execution(* com.apollo.service..*.*(..))")
    private void serviceMethod() {}

    /**
     * 在调用前切换数据源
     */
    @Before("serviceMethod()")
    public void transServiceMethod(JoinPoint joinPoint) {
        switchDataSource(joinPoint);
    }

    /**
     * 在调用后（包括有异常的情况下），清空数据源标识
     */
    @After("serviceMethod()")
    public void clearDs(JoinPoint joinPoint) {
        clearDataSource(joinPoint);
    }

    /**
     * 根据注解改变数据源
     */
    private void switchDataSource(JoinPoint joinPoint) {
        MethodSignature signature =
                (MethodSignature) joinPoint.getSignature();
        DsSwitcher dsSwitcher =
                signature.getMethod().getAnnotation(DsSwitcher.class);
        if (!Objects.isNull(dsSwitcher) && !Objects.isNull(dsSwitcher.value())) {
            DataSourceEnum annoEnum = dsSwitcher.value();
            if (DataSourceEnum.PG == annoEnum) {
                DsContextHolder.setDataSourceType(DataSourceEnum.PG);
            } else if (DataSourceEnum.MYSQL == annoEnum) {
                DsContextHolder.setDataSourceType(DataSourceEnum.MYSQL);
            }
        }
    }

    /**
     * 在每次调用之后，清空数据源
     */
    private void clearDataSource(JoinPoint joinPoint) {
        MethodSignature signature =
                (MethodSignature) joinPoint.getSignature();
        DsSwitcher dsSwitcher =
                signature.getMethod().getAnnotation(DsSwitcher.class);
        if (!Objects.isNull(dsSwitcher) && !Objects.isNull(dsSwitcher.value())) {
            DsContextHolder.clearDataSourceType();
        }
    }
}
