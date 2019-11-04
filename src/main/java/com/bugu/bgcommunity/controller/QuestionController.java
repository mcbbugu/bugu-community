package com.bugu.bgcommunity.controller;

import com.bugu.bgcommunity.common.ResultDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * .
 * Created by mcbbugu
 * 2019-10-29 23:28
 */
@CrossOrigin
@RequestMapping("/question")
@RestController
public class QuestionController {

    @GetMapping("/find")
    public ResultDTO findQuestion(){
        return null;
    }
}