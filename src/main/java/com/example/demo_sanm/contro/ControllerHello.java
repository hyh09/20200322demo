package com.example.demo_sanm.contro;

import com.example.demo_sanm.contro.rubo.StrongMssChekbo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/3.
 * @by: DELL)
 */

@RestController
public class ControllerHello {


    /**
     * 1.先将入参转换为json
     * @param chekbo
     * @return
     */
    @RequestMapping("/hello")
    public  ResultBos hello(@RequestBody StrongMssChekbo chekbo){
        ResultBos  resultBos = new ResultBos();
        return  resultBos;
    }
}
