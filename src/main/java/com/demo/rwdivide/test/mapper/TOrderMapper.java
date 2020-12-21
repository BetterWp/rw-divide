package com.demo.rwdivide.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.rwdivide.test.model.TOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wp
 * @since 2020-12-20
 */
@Repository
public interface TOrderMapper extends BaseMapper<TOrder> {

//    @Select("SELECT * FROM t_order where user_id=#{user_id} and order_id=#{order_id}")
    List<TOrder> getOrder(@Param(value = "user_id") Integer user_id, @Param(value = "order_id") Integer order_id);

    @Insert("INSERT INTO t_order(user_id,order_id,order_name) value(#{user_id},#{order_id},#{order_name})")
    Integer insertOrder(@Param(value = "user_id") Integer user_id,@Param(value = "order_id") Integer order_id
            ,@Param(value = "order_name") String order_name);


}
