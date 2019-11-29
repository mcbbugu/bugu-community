package com.bugu.bgcommunity.core.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugu.bgcommunity.common.ResultDTO;
import com.bugu.bgcommunity.common.annotation.RRestController;
import com.bugu.bgcommunity.core.model.dto.ArticleDTO;
import com.bugu.bgcommunity.core.model.entity.Article;
import com.bugu.bgcommunity.core.model.form.QuestionForm;
import com.bugu.bgcommunity.core.service.ArticleService;
import com.bugu.bgcommunity.enums.ResultEnum;
import com.bugu.bgcommunity.exception.BuguException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by mcbbugu
 * 2019-10-29 23:28
 */
@Slf4j
@RRestController("/article/")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ArticleApi {

    private final ArticleService articleService;

    @GetMapping("find")
    public ResultDTO findby(@RequestParam(defaultValue = "1") int current,
                            @RequestParam(defaultValue = "10") int size,
                            @RequestParam(required = false) String classify,
                            @RequestParam(required = false) Integer userId,
                            @RequestParam(defaultValue = "gmt_create") String sort){
        Page<Article> page = new Page<>(current, size);
        IPage<ArticleDTO> articleDTOIPage = articleService.findArticleListBy(page, classify, userId, sort);
        return ResultDTO.ok(articleDTOIPage);
    }

    @GetMapping("find/{id}")
    public ResultDTO findBy(@PathVariable int id){
        articleService.addViewCount(id);
        ArticleDTO articleDTO = articleService.findOneArticleAndUserBy(id);
        return ResultDTO.ok(articleDTO);
    }

    @PostMapping("add")
    public ResultDTO add(@Valid QuestionForm form,
                                 BindingResult result,
                                 HttpServletRequest request){
        if(result.hasErrors()) {
            log.info(ResultEnum.param_error, form);
            throw new BuguException(result.getFieldError().getDefaultMessage());
        }
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("bgcommunity-token")){
                String cookieValue = cookie.getValue();
                int id = articleService.addQuestion(form, cookieValue);
                return ResultDTO.ok(id);
            }
        }
        return ResultDTO.error(ResultEnum.no_login);
    }

    @PostMapping("/img/upload")
    public ResultDTO upload(@RequestParam MultipartFile img){
        String url = articleService.uploadImg(img);
        return ResultDTO.ok(url);
    }
}