package com.demo.rwdivide.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.rwdivide.test.model.T1;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wp
 * @since 2020-12-08
 */
@Repository
public interface T1Mapper extends BaseMapper<T1> {

    @Select("SELECT * FROM t1")
    List<Integer> getList();

    @Insert("INSERT INTO t1 value(#{num})")
    Integer insert(@Param(value = "num") Integer num);

}
