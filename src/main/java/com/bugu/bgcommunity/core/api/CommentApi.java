package com.bugu.bgcommunity.core.api;

import com.bugu.bgcommunity.common.ResultDTO;
import com.bugu.bgcommunity.common.annotation.RRestController;
import com.bugu.bgcommunity.core.model.form.CommentForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * .
 * Created by mcbbugu
 * 2019-10-29 23:28
 */
@RRestController("/comment/")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CommentApi {

    private final
    @PostMapping("/add")
    public ResultDTO add(@Valid CommentForm form){

        return null;
    }
}