package com.bugu.bgcommunity.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugu.bgcommunity.common.ResultDTO;
import com.bugu.bgcommunity.model.dto.QuestionDTO;
import com.bugu.bgcommunity.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    public ResultDTO findQuestionBy(@RequestParam(defaultValue = "1") int current,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(required = false) String tag,
                                    @RequestParam(defaultValue = "gmt_create") String sort){
        Page<QuestionDTO> page = new Page<>(current, size);
        IPage<QuestionDTO> questions = questionService.findQuestionBy(page, tag, sort);
        return ResultDTO.ok(questions);
    }

    @PostMapping("/add")
    public ResultDTO addQuestion(@RequestParam String title,
                                 @RequestBody List<Integer> tags,
                                 @RequestParam String content,
                                 HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        //判断是否登录
        return null
    }
}