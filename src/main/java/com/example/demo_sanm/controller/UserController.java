package com.example.demo_sanm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinatelecom.prov.strongAssetOrder.checkObject.impl.StrongMssOverallPo;
import com.example.demo_sanm.contro.ResultBos;
import com.example.demo_sanm.contro.rubo.StrongMssChekbo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/10.
 * @by: DELL)
 */

@Controller
@RequestMapping(value = "/user")
public class UserController {

    //url http://127.0.0.1:8080/swagger-ui.html#/

    private  Logger  logger = Logger.getLogger(getClass());



    @ApiOperation(value="添加用户信息", notes="添加用户信息")
    @ApiImplicitParam(name = "chekbo", value = "用户详细实体user", required = true)
    @ResponseBody
    @RequestMapping(value = "/addUser", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public ResultBos addUser(@RequestBody  StrongMssChekbo chekbo){
        String json= JSON.toJSONString(chekbo);
        StrongMssChekbo chekbo1 = JSONArray.parseObject(json, StrongMssChekbo.class);
        logger.info("A参"+chekbo);
        logger.info("B参"+chekbo1);

        StrongMssOverallPo strongMssOverallPo = new  StrongMssOverallPo();

        ResultBos  resultBos = new ResultBos();
        resultBos.setErrorMsg("=====");
        resultBos.setInstanceId("====");
        return resultBos;
    }


}
