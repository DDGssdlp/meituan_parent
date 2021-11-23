package com.ddg.meituan.admin.datasource.config;


import com.ddg.meituan.admin.datasource.properties.DataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;


/**
 * DruidDataSource
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public class DynamicDataSourceFactory {

    public static HikariDataSource buildDruidDataSource(DataSourceProperties properties) {
        HikariDataSource druidDataSource = new HikariDataSource();
        druidDataSource.setDriverClassName(properties.getDriverClassName());
        druidDataSource.setJdbcUrl(properties.getUrl());
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setPassword(properties.getPassword());

//        druidDataSource.setInitialSize(properties.getInitialSize());
//        druidDataSource.setMaxActive(properties.getMaxActive());
//        druidDataSource.setMinIdle(properties.getMinIdle());
//        druidDataSource.setMaxWait(properties.getMaxWait());
//        druidDataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
//        druidDataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
//        druidDataSource.setMaxEvictableIdleTimeMillis(properties.getMaxEvictableIdleTimeMillis());
//        druidDataSource.setValidationQuery(properties.getValidationQuery());
//        druidDataSource.setValidationQueryTimeout(properties.getValidationQueryTimeout());
//        druidDataSource.setTestOnBorrow(properties.isTestOnBorrow());
//        druidDataSource.setTestOnReturn(properties.isTestOnReturn());
//        druidDataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
//        druidDataSource.setMaxOpenPreparedStatements(properties.getMaxOpenPreparedStatements());
//        druidDataSource.setSharePreparedStatements(properties.isSharePreparedStatements());

//        try {
//            druidDataSource.setFilters(properties.getFilters());
//            druidDataSource.init();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return druidDataSource;
    }
}