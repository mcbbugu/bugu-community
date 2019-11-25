package com.bugu.bgcommunity.core.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(value = "id", type = IdType.AUTO)
    int id;
    String openId;
    String nickName;
    String token;
    String avatarUrl;
    String openType;
    int articleCount;
    int likeCount;
    int fansCount;
    int collectionCount;
    Timestamp gmtCreate;
    Timestamp gmtUpdate;
}