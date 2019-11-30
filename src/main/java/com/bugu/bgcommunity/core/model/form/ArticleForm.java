package com.bugu.bgcommunity.core.model.form;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Created by mcbbugu
 * 2019-11-23 14:04
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleForm {
    int id;
    @NotBlank(message = "标题必填")
    String title;
    @NotEmpty(message = "标签必填")
    List<String> tags;
    @NotBlank(message = "内容必填")
    String content;
    String classify;
}