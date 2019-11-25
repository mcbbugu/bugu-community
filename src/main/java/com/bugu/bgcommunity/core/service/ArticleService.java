package com.bugu.bgcommunity.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugu.bgcommunity.common.utils.ImgUtil;
import com.bugu.bgcommunity.core.mapper.ArticleMapper;
import com.bugu.bgcommunity.core.model.dto.ArticleDTO;
import com.bugu.bgcommunity.core.model.entity.Article;
import com.bugu.bgcommunity.core.model.entity.User;
import com.bugu.bgcommunity.core.model.form.QuestionForm;
import com.bugu.bgcommunity.enums.ResultEnum;
import com.bugu.bgcommunity.exception.BuguException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * .
 * Created by mcbbugu
 * 2019-11-18 21:52
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ArticleService {

    private final ArticleMapper articleMapper;
    private final UserService userService;

    public IPage<ArticleDTO> findArticleListBy(Page<ArticleDTO> page, String classify, String sort){
        IPage<ArticleDTO> questions = articleMapper.findArticleListBy(page, classify, sort);
        if(null == questions){
            throw new BuguException(ResultEnum.no_question);
        }
        return questions;
    }

    public ArticleDTO findOneArticleAndUserBy(int id){
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Article article = articleMapper.selectOne(queryWrapper);
        User user = userService.findUserBy(article.getUserId());
        ArticleDTO articleDTO = new ArticleDTO();
        BeanUtils.copyProperties(article, articleDTO);
        articleDTO.setUser(user);
        return articleDTO;
    }

    public int addQuestion(QuestionForm form, String cookie){
        User user = userService.findUserBy(cookie);
        if(user != null){
            Article article = new Article();
            article.setTitle(form.getTitle());
            article.setContent(form.getContent());
            List<String> tags = form.getTags();
            String strTags = String.join(",", tags);
            article.setTags(strTags);
            article.setClassify(form.getClassify());
            article.setUserId(user.getId());
            articleMapper.insert(article);
            int id = article.getId();
            return id;
        }
        return 0;
    }

    @Value("${markdown.save}")
    private String savePath;
    @Value("${markdown.access}")
    private String accessPath;
    public String uploadImg(MultipartFile file){
        String url = ImgUtil.upload(file, savePath, accessPath);
        log.info("图片地址：" + url);
        return url;
    }
}