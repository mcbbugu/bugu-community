package com.bugu.bgcommunity.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugu.bgcommunity.common.utils.ImgUtil;
import com.bugu.bgcommunity.core.mapper.ArticleMapper;
import com.bugu.bgcommunity.core.mapper.UserMapper;
import com.bugu.bgcommunity.core.model.dto.ArticleDTO;
import com.bugu.bgcommunity.core.model.entity.Article;
import com.bugu.bgcommunity.core.model.entity.User;
import com.bugu.bgcommunity.core.model.form.QuestionForm;
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
    private final UserMapper userMapper;

    public IPage<ArticleDTO> findArticleListBy(Page<Article> page, String classify, String sort){
        IPage<ArticleDTO> articleDTOS = articleMapper.findArticleListBy(page, classify, sort);
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

    public int addQuestion(QuestionForm form, String token){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("token", token);
        User user = userMapper.selectOne(queryWrapper);
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
}