package com.bugu.bgcommunity.model.entity;

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
public class Question {
    Integer id;
    String title;
    String content;
    Integer viewCount;
    Integer commentCount;
    Integer agreeCount;
    Integer userId;
    Timestamp gmtCreate;
    Timestamp gmtUpdate;
}