package com.octopus.report.dao;

import com.octopus.report.ReportApplication;
import com.octopus.report.pojo.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

@SpringBootTest(classes = ReportApplication.class)
@RunWith(SpringRunner.class)
public class UserDaoTest {


    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void getAllUser() throws Exception{
        List<Map> list = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
        Map<String,String> map2 = new HashMap<>();
        map.put("k3","v3");
        map.put("k4","v4");
        list.add(map);
        list.add(map2);
        redisTemplate.opsForSet().add("title",list);

        System.out.println(redisTemplate.opsForValue().get("title"));
    }


}