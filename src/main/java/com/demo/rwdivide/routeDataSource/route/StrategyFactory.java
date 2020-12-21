package com.demo.rwdivide.routeDataSource.route;

import com.demo.rwdivide.routeDataSource.enums.RouteEnum;

import java.util.Optional;

/**
 * @Description: 策略工厂类
 * @Date: 2020/12/05 10:42
 * @Author: wp
 **/
public class StrategyFactory {

    public static RouteStrategy getRouteStrategyInstance(String name){
        return Optional.ofNullable(StrategyRegister.getRouteStrategy(RouteEnum.getRouteType(name)))
                .orElseThrow(()->new IllegalArgumentException(String.format("%s strategy not support",name)));
    }
}
