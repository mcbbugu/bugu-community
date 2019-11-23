package com.bugu.bgcommunity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bugu.bgcommunity.core.mapper.UserMapper;
import com.bugu.bgcommunity.core.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BgcommunityApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectOne(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id", "1992855");
        userMapper.selectOne(queryWrapper);
    }

    @Test void updateById(){
        User user = new User();
        user.setId(1);
        user.setNickName("1233333");
        userMapper.updateById(user);
    }
}
