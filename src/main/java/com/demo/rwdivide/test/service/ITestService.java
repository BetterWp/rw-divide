package com.demo.rwdivide.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.rwdivide.test.model.T1;

import java.util.List;

/**
 * @Description: 测试Service
 * @Date: 2020/12/03 11:13
 * @Author: wp
 **/
public interface ITestService extends IService<T1> {

    /**
     * 测试读
     * @return
     */
    List<Integer> getList();

    /**
     * 测试写
     * @param num
     * @return
     */
    Integer insert(Integer num);

}
