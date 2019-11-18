package com.bugu.bgcommunity.web;

import com.bugu.bgcommunity.common.ResultDTO;
import com.bugu.bgcommunity.model.dto.QuestionDTO;
import com.bugu.bgcommunity.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * .
 * Created by mcbbugu
 * 2019-10-29 23:28
 */
@CrossOrigin
@RestController
@RequestMapping("/question")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/find")
    public ResultDTO findQuestionBy(@RequestParam(value = "tag", required = false) String tag){
        List<QuestionDTO> list = questionService.findQuestionBy(tag);
        return ResultDTO.ok(list);
    }
}