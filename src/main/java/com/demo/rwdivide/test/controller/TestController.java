package com.demo.rwdivide.test.controller;

import com.demo.rwdivide.test.model.TOrder;
import com.demo.rwdivide.test.service.IOrderService;
import com.demo.rwdivide.test.service.ITestService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 测试Controller
 * @Date: 2020/12/03 11:12
 * @Author: wp
 **/
@RestController
@RequiredArgsConstructor
public class TestController {

    private final ITestService testService;
    private final IOrderService orderService;

    @ApiOperation(value = "读测试", notes = "读测试")
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<Integer> read() {
        return testService.getList();
    }

    @ApiOperation(value = "写测试", notes = "写测试")
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String write(Integer num) {
        testService.insert(num);
        return "test success(成功)";
    }

    @ApiOperation(value = "分库分表读测试", notes = "分库分表读测试")
    @RequestMapping(value = "/sharding_read", method = RequestMethod.GET)
    public List<TOrder> sharding_read(Integer user_id, Integer order_id) {
        return orderService.getOrder(user_id,order_id);
    }

    @ApiOperation(value = "分库分表写测试", notes = "分库分表写测试")
    @RequestMapping(value = "/sharding_write", method = RequestMethod.GET)
    public String sharding_write(Integer user_id,Integer order_id,String order_name) {
        orderService.insertOrder(user_id,order_id,order_name);
        return "test success(成功)";
    }
}
