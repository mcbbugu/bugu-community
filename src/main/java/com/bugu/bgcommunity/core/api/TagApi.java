package com.bugu.bgcommunity.core.api;

import com.bugu.bgcommunity.common.ResultDTO;
import com.bugu.bgcommunity.common.annotation.RRestController;
import com.bugu.bgcommunity.core.service.TagService;
import com.bugu.bgcommunity.core.model.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * .
 * Created by mcbbugu
 * 2019-10-29 23:28
 */
@RRestController("/tag/")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TagApi {

    private final TagService tagService;

    @GetMapping("find")
    public ResultDTO findQuestionBy(){
        List<Tag> tags = tagService.findAll();
        return ResultDTO.ok(tags);
    }
}