package com.demo.rwdivide.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.rwdivide.test.model.TOrder;

import java.util.List;

/**
 * @Description: 测试Service
 * @Date: 2020/12/03 11:13
 * @Author: wp
 **/
public interface IOrderService extends IService<TOrder> {

    /**
     * 查询订单
     * @param user_id
     * @param order_id
     * @return
     */
    List<TOrder> getOrder(Integer user_id, Integer order_id);

    /**
     * 插入订单
     * @param user_id
     * @param order_id
     * @param order_name
     */
    void insertOrder(Integer user_id, Integer order_id, String order_name);
}
