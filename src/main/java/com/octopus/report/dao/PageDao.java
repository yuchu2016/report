package com.octopus.report.dao;

import com.octopus.report.pojo.PageInfo;
import com.octopus.report.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luqinglin
 * Date: 2018-01-10
 * Time: 13:23
 */
@Repository
public class PageDao {

    public static final Logger log = LoggerFactory.getLogger(PageDao.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PageInfo getPageInfo(String sql, List<Object> paramsList, Integer pageNum, Integer pageSize){
        PageInfo pageInfo = new PageInfo();
        String countSql = "select count(0) from("+sql+") totalTable";
        int count = jdbcTemplate.queryForObject(countSql,paramsList.toArray(new Object[paramsList.size()]),Integer.class);
        pageInfo.setTotal(count);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPages(count/pageSize+1);
        String pageSql = sql+" limit ? offset ?";
        paramsList.add(pageSize);
        paramsList.add((pageNum-1)*pageSize);

        log.warn(pageSql);
        log.warn(paramsList.toString());
        SqlRowSet result= jdbcTemplate.queryForRowSet(pageSql,paramsList.toArray(new Object[paramsList.size()]));
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
        pageInfo.setList(resultList);
        pageInfo.setSize(resultList.size());
        return pageInfo;
    }
}
