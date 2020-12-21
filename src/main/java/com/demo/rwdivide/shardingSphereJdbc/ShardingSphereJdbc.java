package com.demo.rwdivide.shardingSphereJdbc;

import com.alibaba.druid.pool.DruidDataSource;
import io.shardingsphere.api.config.MasterSlaveRuleConfiguration;
import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;


/**
 * @Description: Master-Slave功能
 * @Date: 2020/12/03 09:46
 * @Author: wp
 **/
@Configuration
@Slf4j
public class ShardingSphereJdbc {

    private final String DRIVER = ".driver-class-name";
    private final String URL = ".url";
    private final String USERNAME = ".username";
    private final String PASSWORD = ".password";
//    private final String DBS = "sharding.jdbc.datasource.names";
//    private final String PREFIX = "sharding.jdbc.datasource.";

    private final String DBS = "spring.shardingsphere.datasource.names";
    private final String PREFIX = "spring.shardingsphere.datasource.";

    @Autowired
    private Environment environment;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(createDataSource());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    DataSource createDataSource() throws SQLException {
        // 获取数据库列表
        String[] dbs = Objects.requireNonNull(environment.getProperty(DBS)).split(",");
        log.info("DBS::{}", Arrays.toString(dbs));

        // 设置主从，约定第一个为主，其他为从
        MasterSlaveRuleConfiguration configuration = new MasterSlaveRuleConfiguration(dbs[0], dbs[0],
                Arrays.asList(Arrays.copyOfRange(dbs, 1, dbs.length)));
        log.info("ShardingMasterSlaveDataSource master :: {}", configuration.getMasterDataSourceName());
        log.info("ShardingMasterSlaveDataSource slave :: {}", configuration.getSlaveDataSourceNames());

        // 设置打印SQL语句，查看主从配置和切换是否成功
        Properties properties = new Properties();
        properties.setProperty("sql.show", "true");
//        MasterSlaveDataSourceFactory.createDataSource(createDataSourceMap(dbs), configuration,new HashMap<>(0),
//                properties);

        return MasterSlaveDataSourceFactory.createDataSource(createDataSourceMap(dbs), configuration,new HashMap<>(0),
                properties);
    }

    /**
     * 返回DataSource列表
     */
    private Map<String, DataSource> createDataSourceMap(String[] dbs) {
        Map<String, DataSource> result = new HashMap<>(dbs.length);
        for (String db : dbs) {
            log.info("Create data source ::{}", db);
            result.put(db, createDataSource(PREFIX + db));
        }
        return result;
    }

    private DataSource createDataSource(String prefix) {
        log.info(DRIVER + "::{}", environment.getProperty(prefix + DRIVER));
        log.info(URL + "::{}", environment.getProperty(prefix + URL));
        log.info(USERNAME + "::{}", environment.getProperty(prefix + USERNAME));
        log.info(PASSWORD + "::{}", environment.getProperty(prefix + PASSWORD));

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(environment.getProperty(prefix + DRIVER));
        dataSource.setUrl(environment.getProperty(prefix + URL));
        dataSource.setUsername(environment.getProperty(prefix + USERNAME));
        dataSource.setPassword(environment.getProperty(prefix + PASSWORD));
        return dataSource;
    }
}
