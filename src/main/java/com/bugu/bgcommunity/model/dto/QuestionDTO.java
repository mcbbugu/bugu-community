package com.bugu.bgcommunity.model.dto;

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
public class QuestionDTO {
    Integer id;
    String title;
    String content;
    Integer viewCount;
    Integer commentCount;
    String tag;
    Integer userId;
    Timestamp gmtCreate;
    Timestamp gmtUpdate;
    String avatarUrl;
}