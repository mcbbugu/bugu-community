package com.bugu.bgcommunity.core.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

/**
 * 评论实体类
 * Created by mcbbugu
 * 2019-12-03 15:38
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {
    int id;
    String content;
    int userId;
    int articleId;
    int parentId;
    int replyId;
    //评论等级 1 or 2
    int level;
    //点赞数
    int praiseCount;
    //是否置顶 1：普通 2：置顶
    int topStatus;
    Timestamp gmtCreate;
}