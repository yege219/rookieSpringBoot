package com.rookie.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by zhangyechen on 2017/12/29.
 * 数据库源配置类
 */
@Configuration
public class DbConfig {

    @Bean(name = "testDataSource")
    @Qualifier("testDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "vpsDataSource")
    @Qualifier("vpsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.vps")
    public DataSource vpsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "docker23DataSource")
    @Qualifier("docker23DataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.docker23")
    public DataSource docker23DataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "testJdbcTemplate")
    public JdbcTemplate testJdbcTemplate(
            @Qualifier("testDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "docker23JdbcTemplate")
    public JdbcTemplate docker23JdbcTemplate(
            @Qualifier("docker23DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "vpsJdbcTemplate")
    public JdbcTemplate vpsJdbcTemplate(
            @Qualifier("vpsDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
