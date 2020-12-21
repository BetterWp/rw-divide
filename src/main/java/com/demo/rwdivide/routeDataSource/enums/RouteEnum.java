package com.demo.rwdivide.routeDataSource.enums;

/**
 * @Description: 路由枚举
 * @Date: 2020/12/03 09:47
 * @Author: wp
 **/
public enum RouteEnum {

    ROUND_ROBIN("round-robin","轮询算法","com.demo.rwdivide.routeDataSource.route.RoundRobinStrategy"),
    RANDOM("random","随机算法","com.demo.rwdivide.routeDataSource.route.RandomStrategy"),
    OTHER("other","其他算法","com.demo.rwdivide.routeDataSource.route.OtherStrategy");

    private String name;
    private String desc;
    private String clazz;

    private RouteEnum(String name,String desc,String clazz){
        this.name = name;
        this.desc = desc;
        this.clazz = clazz;
    }

    public static RouteEnum getRouteType(String name){
        RouteEnum[] var1 = values();
        for (RouteEnum var : var1) {
            if (var.getName().equals(name)){
                return var;
            }
        }
        return null;
    }


    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getClazz() {
        return clazz;
    }
}
