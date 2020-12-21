package com.demo.rwdivide.routeDataSource.route;

import com.demo.rwdivide.routeDataSource.enums.RouteEnum;

/**
 * @Description: 其他路由策略
 * @Date: 2020/12/05 10:29
 * @Author: wp
 **/
public class OtherStrategy implements RouteStrategy {

    @Override
    public Object route(int num) {
        //TODO 待实现
        return new Object();
    }

    @Override
    public RouteEnum getRouteEnum() {
        return RouteEnum.OTHER;
    }
}
