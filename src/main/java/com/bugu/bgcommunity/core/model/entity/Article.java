package com.bugu.bgcommunity.core.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

/**
 * 问题
 * Created by mcbbugu
 * 2019-10-29 23:52
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Article {
    @TableId(value = "id", type = IdType.AUTO)
    int id;
    String title;
    String content;
    int viewCount;
    int commentCount;
    int agreeCount;
    int userId;
    String classify;
    String tags;
    Timestamp gmtCreate;
    Timestamp gmtUpdate;
}