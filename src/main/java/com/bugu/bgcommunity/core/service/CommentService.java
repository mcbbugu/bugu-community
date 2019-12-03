package com.bugu.bgcommunity.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugu.bgcommunity.core.mapper.ArticleMapper;
import com.bugu.bgcommunity.core.mapper.UserMapper;
import com.bugu.bgcommunity.core.model.dto.ArticleDTO;
import com.bugu.bgcommunity.core.model.entity.Article;
import com.bugu.bgcommunity.core.model.entity.Comment;
import com.bugu.bgcommunity.core.model.entity.User;
import com.bugu.bgcommunity.core.model.form.ArticleForm;
import com.bugu.bgcommunity.core.model.form.CommentForm;
import com.bugu.bgcommunity.enums.ResultEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * .
 * Created by mcbbugu
 * 2019-11-18 21:52
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CommentService {

    private final ArticleMapper articleMapper;
    private final UserService userService;
    private final UserMapper userMapper;

    public IPage<ArticleDTO> findArticleListBy(Page<Article> page, String classify, Integer userId, String sort){
        IPage<ArticleDTO> articleDTOS = articleMapper.findArticleListBy(page, classify, userId, sort);
        return articleDTOS;
    }

    public ArticleDTO findOneArticleAndUserBy(int ArticleId){
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", ArticleId);
        Article article = articleMapper.selectOne(queryWrapper);
        User user = userService.findUserBy(article.getUserId());
        ArticleDTO articleDTO = new ArticleDTO();
        BeanUtils.copyProperties(article, articleDTO);

        //用户总访问量排名
        int ranking = userMapper.trafficRanking(user.getId());
        user.setRank(ranking);
        userMapper.updateById(user);
        articleDTO.setUser(user);
        return articleDTO;
    }

    public void addComment(CommentForm form){
        Comment comment = new Comment();
        BeanUtils.copyProperties(form, comment);
    }

    public void addViewCount(int id){
        //文章浏览量新增+1
        Article article = articleMapper.selectById(id);
        article.setViewCount(article.getViewCount() + 1);
        articleMapper.updateById(article);
        //用户的总访问量+1
        User user = userMapper.selectById(article.getUserId());
        user.setViewCount(user.getViewCount() + 1);
        userMapper.updateById(user);
    }

    public void updateArticleBy(ArticleForm form) {
        Article article = articleMapper.selectById(form.getId());
        article.setTitle(form.getTitle());
        article.setContent(form.getContent());
        article.setTags(String.join(",", form.getTags()));
        article.setGmtUpdate(new Timestamp(System.currentTimeMillis()));
        articleMapper.updateById(article);
        log.info(ResultEnum.ok_update.getMsg() + " id: {}", form.getId());
    }
}