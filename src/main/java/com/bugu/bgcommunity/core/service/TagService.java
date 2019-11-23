package com.bugu.bgcommunity.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bugu.bgcommunity.core.mapper.TagMapper;
import com.bugu.bgcommunity.core.model.entity.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class TagService {

    private final TagMapper tagMapper;

    public List<Tag> findAll(){
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        List<Tag> tags = tagMapper.selectList(queryWrapper);
        return tags;
    }
}