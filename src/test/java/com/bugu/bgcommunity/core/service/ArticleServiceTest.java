package com.bugu.bgcommunity.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugu.bgcommunity.core.model.dto.ArticleDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * .
 * Created by mcbbugu
 * 2019-11-18 22:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void findQuestionBy() {
        Page<ArticleDTO> page = new Page<>(1, 2);
        IPage<ArticleDTO> questions = articleService.findQuestionBy(page, "博客", "id");
        System.out.println(questions.getRecords());
    }
}