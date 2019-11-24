package com.bugu.bgcommunity.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugu.bgcommunity.core.model.dto.ArticleDTO;
import com.bugu.bgcommunity.core.model.entity.Article;
import org.apache.ibatis.annotations.Param;

/**
 * .
 * Created by mcbbugu
 * 2019-11-18 21:34
 */
public interface ArticleMapper extends BaseMapper<Article> {
    IPage<ArticleDTO> findArticleBy(Page page,
                                        @Param("classify") String classify,
                                        @Param("sort") String sort);
}