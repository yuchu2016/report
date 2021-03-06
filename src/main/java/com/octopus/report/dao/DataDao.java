package com.octopus.report.dao;

import com.octopus.report.pojo.PageInfo;
import com.octopus.report.util.ResultUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.rowset.JdbcRowSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luqinglin
 * Date: 2017-12-28
 * Time: 10:53
 */
@Repository
public class DataDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private RedisTemplate<String,List> redisTemplate;
    private static final Logger log = LoggerFactory.getLogger(DataDao.class);

    public List<Map> get(String sql,List<Object> paramsList) throws Exception{

        log.warn("参数为:{}",paramsList.toString());


        Object[] params=paramsList.toArray(new Object[paramsList.size()]);
//        String =matcher.group(1);
//        sql = sql.replaceAll("WITH\\(NOLOCK\\)","");
        log.warn("sql:{}",sql);

        SqlRowSet result= jdbcTemplate.queryForRowSet(sql,params);
//        SqlRowSetMetaData sqlRsmd = result.getMetaData();
//        int columnCount = sqlRsmd.getColumnCount();
//        List<String> columnName = new ArrayList<>();
//        for (int i = 1; i <= columnCount; i++) {
//            columnName.add(sqlRsmd.getColumnName(i));
//        }
//        List<Map> resultList = new ArrayList<>();
//
//        while (result.next()){
//            Map<String,Object> column = new HashMap<>();
//            for (int i=1;i<=columnName.size();i++){
//                column.put(columnName.get(i-1),result.getObject(i));
//            }
//            resultList.add(column);
//        }
        List<Map>resultList = ResultUtil.result(result);
        return resultList;
    }


    private void addRedis(String sql,List<Map> resultList){
        String key = DigestUtils.md5Hex(sql);
        redisTemplate.opsForList().rightPush(key,resultList);
    }

}
