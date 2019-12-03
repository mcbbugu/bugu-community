package com.bugu.bgcommunity.core.model.form;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by mcbbugu
 * 2019-12-03 16:02
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentForm {
    @NotNull(message = "文章id必填")
    int articleId;
    @NotNull(message = "用户id必填")
    int userId;
    @NotBlank(message = "评论内容必填")
    String content;
    @NotNull(message = "评论等级必填")
    int level;
    int parentId;
    int replyId;
}