package com.apollo.config.datasource;

/**
 * @author ：apollo
 * @since ：Created in 2019/2/23
 */
public class DsContextHolder {

    /**
     *     ThreadLocal这里的泛型一定要注意。
     *     1.这里是enum类型的DataSourceEnum，而不是String。
     *     2.在AbstractRoutingDataSource的determineTargetDataSource方法中，
     *       会通过Object lookupKey = determineCurrentLookupKey();
     *            DataSource dataSource = this.resolvedDataSources.get(lookupKey);
     *       来获取DataSource。这里的determineCurrentLookupKey的返回值，一定和ThreadLocal的泛型相同。
     *       也就是ThreadLocal<determineCurrentLookupKey返回值的类型>。
     *     3.在MultiDataSourceConfig中，配置多数据源multiDataSource时，
     *         Map<Object, Object> target = new HashMap<>();
     *         target.put(DataSourceEnum.PG, pgDataSource());//postgresql数据源
     *         target.put(DataSourceEnum.MYSQL, mysqlDataSource);//mysql数据源
     *
     *         AbstractRoutingDataSource dataSource = new DynamicDataSource();
     *         dataSource.setDefaultTargetDataSource(pgDataSource());
     *         dataSource.setTargetDataSources(target);//设置目标数据源
     *         return dataSource;
     *       注意这里的Map<Object, Object> target，他的键值类型，和ThreadLocal的泛型类型要一致
     *
     *     总结一下：需要3个地方的类型相同，
     *          第一处：在MultiDataSourceConfig中，multiDataSource实例中，setTargetDataSources设置的map的键值；
     *          第二处：AbstractRoutingDataSource中的determineTargetDataSource的返回值；
     *          第三处：ThreadLocal的泛型类型。
     *
     *
     *
     */
    private static final ThreadLocal<DataSourceEnum> contextHolder = new ThreadLocal<>();

    private DsContextHolder() throws Exception {
        throw new InstantiationException("无法实例化");
    }

    /**
     * 设置数据源标识
     */
    public static void setDataSourceType(DataSourceEnum dsEnum) {
        contextHolder.set(dsEnum);
    }

    /**
     * 获取当前数据源的标识
     */
    public static DataSourceEnum getDataSourceType() {
        return contextHolder.get();
    }

    /**
     * 清空数据源标识
     */
    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
