package com.octopus.report.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luqinglin
 * Date: 2017-12-28
 * Time: 16:03
 */
@Data
public class Param {

    String sql;

    List<Object> param;


}
