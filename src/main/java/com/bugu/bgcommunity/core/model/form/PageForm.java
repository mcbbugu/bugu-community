package com.bugu.bgcommunity.core.model.form;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * Created by mcbbugu
 * 2019-11-23 13:50
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageForm {
    int current = 1;
    int size = 10;
    String sort = "gmt_create";
}