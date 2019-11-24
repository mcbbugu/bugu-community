package com.bugu.bgcommunity.core.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * 问题
 * Created by mcbbugu
 * 2019-10-29 23:52
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tag {
    @TableId(value = "id", type = IdType.AUTO)
    int id;
    String name;
    int questionCount;
    int blogCount;
    int concernCount;
}