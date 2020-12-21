package com.demo.rwdivide.shardingSphereProxy;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @Description: Master-Slave功能
 * @Date: 2020/12/03 14:49
 * @Author: wp
 **/
@Configuration
public class ShardingSphereProxy {

    @Bean
    public DataSource createShardingSphereProxyDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //rw_db 读写分离DB
        //sharding_db 分库分表DB
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3307/sharding_db?characterEncoding=utf8&serverTimezone=GMT%2B8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactoryBean() throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //适配Mybatis-Plus baseMapper
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(createShardingSphereProxyDataSource());
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis.xml"));
        return sqlSessionFactoryBean.getObject();
    }
}
