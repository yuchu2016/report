package com.octopus.report.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luqinglin
 * Date: 2018-01-10
 * Time: 13:11
 */
@Data
public class PageInfo {

    //每页的数量
    private Integer pageSize;
    //当前页
    private Integer pageNum;
    //当前页的数量
    private Integer size;
    //总记录数
    private Integer total;
    //总页数
    private Integer pages;
    //结果集
    private List<Map> list;


}
