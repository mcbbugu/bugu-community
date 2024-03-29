package com.bugu.bgcommunity.core.model.dto;

import com.bugu.bgcommunity.core.model.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

/**
 * .
 * Created by mcbbugu
 * 2019-11-18 21:36
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleDTO {
    int id;
    String title;
    String content;
    int viewCount;
    int commentCount;
    int agreeCount;
    int userId;
    String classify;
    String tags;
    int likeCount;
    int collectionCount;
    Timestamp gmtCreate;
    Timestamp gmtUpdate;

    String avatarUrl;
    User user;
}