package com.demo.rwdivide.test.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.rwdivide.test.mapper.TOrderMapper;
import com.demo.rwdivide.test.model.TOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 测试Service实现类
 * @Date: 2020/12/03 11:14
 * @Author: wp
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements IOrderService {


    @Override
    public List<TOrder> getOrder(Integer user_id, Integer order_id) {
        return baseMapper.selectList(new QueryWrapper<TOrder>()
                .lambda()
                .eq(user_id!=null,TOrder::getUserId,user_id)
                .eq(order_id!=null,TOrder::getOrderId,order_id));
//        List<TOrder> order = baseMapper.getOrder(user_id, order_id);
//        return order;
    }

    @Override
    public void insertOrder(Integer user_id, Integer order_id, String order_name) {
        TOrder tOrder = new TOrder().setUserId(user_id).setOrderId(order_id).setOrderName(order_name);
        baseMapper.insert(tOrder);
//        baseMapper.insertOrder(user_id,order_id,order_name);
    }
}
