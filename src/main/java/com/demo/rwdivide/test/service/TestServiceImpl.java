package com.demo.rwdivide.test.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.rwdivide.test.mapper.T1Mapper;
import com.demo.rwdivide.test.model.T1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 测试Service实现类
 * @Date: 2020/12/03 11:14
 * @Author: wp
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class TestServiceImpl extends ServiceImpl<T1Mapper, T1> implements ITestService {

//    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Integer> getList() {
        String sql = "SELECT * FROM t1";
//        List<Integer> list = jdbcTemplate.queryForList(sql, int.class);
        return baseMapper.getList();
    }

    @Override
    @Transactional(transactionManager = "transactionManager",rollbackFor = Exception.class)
    public Integer insert(Integer num) {
//        String sql = "INSERT INTO t1 value(?)";
//        int update = jdbcTemplate.update(sql, num);
        return baseMapper.insert(num);
    }
}
