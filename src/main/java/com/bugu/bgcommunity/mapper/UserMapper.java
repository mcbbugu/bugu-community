package com.bugu.bgcommunity.mapper;

import com.bugu.bgcommunity.entity.User;
import org.springframework.stereotype.Repository;

/**
 * .
 * Created by mcbbugu
 * 2019-11-01 22:12
 */
@Repository
public interface UserMapper {
    User find(Integer id);
}