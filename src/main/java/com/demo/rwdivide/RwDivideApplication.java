package com.demo.rwdivide;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableTransactionManagement
@MapperScan(value = {"com.demo.rwdivide.test.mapper"})
public class RwDivideApplication {

    public static void main(String[] args) {
        SpringApplication.run(RwDivideApplication.class, args);
    }

}
