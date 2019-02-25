package com.apollo.config;

import com.apollo.config.datasource.DataSourceEnum;
import com.apollo.config.datasource.DynamicDataSource;
import com.apollo.config.datasource.MyMetaObjectHandler;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：apollo
 * @since ：Created in 2019/2/23
 */
@Configuration
public class MultiDataSourceConfig {

    //实体类位置
    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage;

    //mapper的位置
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    /**
     * postgresql数据源
     */
    @Bean(name = "pgDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pg")
    public DataSource pgDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * mysql数据源
     */
    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 多数据源动态切换
     */
    @Bean
    public DataSource multiDataSource(
            @Qualifier("mysqlDataSource") DataSource mysqlDataSource) {
        Map<Object, Object> target = new HashMap<>();
        target.put(DataSourceEnum.PG, pgDataSource());
        target.put(DataSourceEnum.MYSQL, mysqlDataSource);

        AbstractRoutingDataSource dataSource = new DynamicDataSource();
        dataSource.setDefaultTargetDataSource(mysqlDataSource);
        dataSource.setTargetDataSources(target);
        return dataSource;
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(
            PaginationInterceptor paginationInterceptor,
            @Qualifier("multiDataSource") DataSource multiDataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(multiDataSource);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{
                paginationInterceptor           //添加分页功能
        });
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setMapperLocations(resolver.getResources(mapperLocations));  //配置mapper位置
        sqlSessionFactory.setGlobalConfig(globalConfiguration());
        sqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage);//配置实体类位置
        return sqlSessionFactory.getObject();

    }

    /**
     * mybatis-plus中SQL执行效率插件，生产环境可以关闭
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    @Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
        conf.setLogicDeleteValue("-1");
        conf.setLogicNotDeleteValue("1");
        conf.setIdType(0);
        conf.setMetaObjectHandler(new MyMetaObjectHandler());
        conf.setDbColumnUnderline(true);
        conf.setRefresh(true);
        return conf;
    }
}
