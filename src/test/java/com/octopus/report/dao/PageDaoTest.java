package com.octopus.report.dao;

import com.octopus.report.ReportApplication;
import com.octopus.report.pojo.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest(classes = ReportApplication.class)
@RunWith(SpringRunner.class)
public class PageDaoTest {

    @Autowired
    private PageDao pageDao;

    @Test
    public void PageDaoTest(){
        String sql = "select username, password from tb_test";
        List<Object> params = new ArrayList<>();
        int pageSize = 5;
        int pageNum = 1;
        PageInfo pageInfo = pageDao.getPageInfo(sql,params,pageNum,pageSize);
        System.out.println(pageInfo);
    }
}