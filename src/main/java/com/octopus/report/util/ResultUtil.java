package com.octopus.report.util;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luqinglin
 * Date: 2018-01-11
 * Time: 9:10
 */
public class ResultUtil {

    public static List<Map> result(SqlRowSet result){
        SqlRowSetMetaData sqlRsmd = result.getMetaData();
        int columnCount = sqlRsmd.getColumnCount();
        List<String> columnName = new ArrayList<>();
        for (int i = 1; i <= columnCount; i++) {
            columnName.add(sqlRsmd.getColumnName(i));
        }
        List<Map> resultList = new ArrayList<>();

        while (result.next()){
            Map<String,Object> column = new HashMap<>();
            for (int i=1;i<=columnName.size();i++){
                column.put(columnName.get(i-1),result.getObject(i));
            }
            resultList.add(column);
        }
        return resultList;
    }
}
