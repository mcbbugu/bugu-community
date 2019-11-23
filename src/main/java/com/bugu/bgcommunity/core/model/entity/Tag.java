package com.bugu.bgcommunity.core.model.entity;

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
    int id;
    String name;
    int questionCount;
    int blogCount;
    int concernCount;
}