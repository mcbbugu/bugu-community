package com.bugu.bgcommunity.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bugu.bgcommunity.core.model.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * .
 * Created by mcbbugu
 * 2019-11-01 22:12
 */
public interface UserMapper extends BaseMapper<User> {
    //总访问量排第几
    int trafficRanking(@Param("userId") int userId);
}