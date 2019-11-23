package com.bugu.bgcommunity.core.model.entity;

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
    int id;
    String title;
    String content;
    int viewCount;
    int commentCount;
    int agreeCount;
    int userId;
    String tags;
    Timestamp gmtCreate;
    Timestamp gmtUpdate;
}