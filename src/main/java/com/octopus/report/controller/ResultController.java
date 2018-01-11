package com.octopus.report.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.octopus.report.common.ResponseCode;
import com.octopus.report.common.ServerResponse;
import com.octopus.report.dao.DataDao;
import com.octopus.report.dao.PageDao;
import com.octopus.report.pojo.PageParam;
import com.octopus.report.pojo.Param;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: luqinglin
 * Date: 2017-12-28
 * Time: 13:17
 */
@RestController
@RequestMapping("/api")
@Api("/api")
public class ResultController {

    private static final Logger log = LoggerFactory.getLogger(ResultController.class);
    @Autowired
    private DataDao dataDao;
    @Autowired
    private PageDao pageDao;

    @ApiOperation(value = "获取结果")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param", value = "参数类", required = true, dataType = "Param"),

    })
    @PostMapping("/result")
    public ServerResponse result(@RequestBody Param param){
        try {
            return ServerResponse.createBySuccess(dataDao.get(param.getSql(),param.getParam()));
        }catch (Exception e){
            if(e instanceof JsonParseException){
                return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
            }
            log.error(e.getMessage());
            e.printStackTrace();
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),e.getMessage());
        }
    }
    @ApiOperation(value = "获取分页结果")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageParam", value = "参数类", required = true, dataType = "PageParam"),
    })
    @PostMapping("/pageResult")
    public ServerResponse pageResult(@RequestBody PageParam pageParam){
        try {
            return ServerResponse.createBySuccess(pageDao.getPageInfo(pageParam.getSql(),pageParam.getParam(),pageParam.getPageNum(),pageParam.getPageSize()));
        }catch (Exception e){
            if(e instanceof JsonParseException){
                return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
            }
            log.error(e.getMessage());
            e.printStackTrace();
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),e.getMessage());
        }
    }

}
