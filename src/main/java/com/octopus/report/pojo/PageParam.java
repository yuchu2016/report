package com.octopus.report.pojo;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luqinglin
 * Date: 2018-01-10
 * Time: 14:30
 */
@Data
public class PageParam {
    private String sql;

    private List<Object> param;

    private Integer pageNum;

    private Integer pageSize;
}
