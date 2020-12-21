package com.demo.rwdivide.routeDataSource.route;

import com.demo.rwdivide.routeDataSource.enums.RouteEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Description: 策略注册器类
 * @Date: 2020/12/03 13:29
 * @Author: wp
 **/
@Component
public class StrategyRegister implements InitializingBean {

    private static final Map<RouteEnum, RouteStrategy> registerMap = new EnumMap<>(RouteEnum.class);

//    static {
//        registerMap.put(RouteEnum.ROUND_ROBIN,new RoundRobinStrategy());
//        registerMap.put(RouteEnum.RANDOM,new RandomStrategy());
//    }

    public static RouteStrategy getRouteStrategy(RouteEnum routeEnum){
        return registerMap.get(routeEnum);
    }

    public Collection<RouteStrategy> getRouteStrategys(){
        return  Collections.unmodifiableCollection(registerMap.values());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ServiceLoader<RouteStrategy> routeStrategies = ServiceLoader.load(RouteStrategy.class);
        routeStrategies.forEach(strategy->registerMap.put(strategy.getRouteEnum(),strategy));
        System.out.println();
    }
}
