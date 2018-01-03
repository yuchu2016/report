package com.octopus.report.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.octopus.report.common.ResponseCode;
import com.octopus.report.common.ServerResponse;
import com.octopus.report.dao.UserDao;
import com.octopus.report.pojo.Param;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserDao userDao;


    @ApiOperation(value = "获取结果")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param", value = "参数类", required = true, dataType = "Param"),

    })
    @PostMapping("/result")
    public ServerResponse result(@RequestBody Param param){
        try {
            return ServerResponse.createBySuccess(userDao.get(param.getSql(),param.getParam()));
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
