package com.bugu.bgcommunity.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

/**
 * 用户
 * Created by mcbbugu
 * 2019-10-28 13:33
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    int id;
    String openId;
    String nickName;
    String token;
    String avatarUrl;
    String openType;
    Timestamp gmtCreate;
    Timestamp gmtUpdate;
}