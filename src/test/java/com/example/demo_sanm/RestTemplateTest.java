package com.example.demo_sanm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.chinatelecom.prov.strongAssetOrder.checkObject.impl.StrongMssOverallPo;
import com.example.demo_sanm.beanentity.HttpBean;
import com.example.demo_sanm.contro.rubo.AssetsCardItems;
import com.example.demo_sanm.contro.rubo.PhysicalIds;
import com.example.demo_sanm.contro.rubo.StrongMssChekbo;
import com.example.demo_sanm.service.HttclienService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/16.
 * @by: DELL)
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateTest {

    @Autowired
    private HttclienService httclienService;

    @Resource
    private RestTemplate restTemplate;
//    @Test
//    public  void Test1(){
//        //url
//        String url="http://10.128.86.64:8000/serviceAgent/rest/entity/v1/entity/queryEntityByKeys";
//        //headers
//          HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json");
//        headers.add("X-APP-ID","29e9446abda53df57268fcc8e327a944");//
//        headers.add("X-APP-KEY","bfad002d63e3b17a0f3cfc8e4537d61a");//setAppSecret
//
//        HttpEntity<String> request = new HttpEntity<>(content(), headers);
//
//        ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, request, String.class);
//        String body = postForEntity.getBody();
//
//        System.out.println(body);
//
//
//    }

    /**
     * APPKey：c5118d611d0dfe8fbf040100a1c7581b

     AppSecret：48f78f556d5fd40e0e4b94dbeb1f5d91

     10227
     */
    @Test
    public   void Testser(){
        String url="http://10.128.91.38:20501/serviceAgent/rest/smrh/smrh/strongSourceCheck/strongSourceCheck";
        String  id="48f78f556d5fd40e0e4b94dbeb1f5d91";
        String appKey="48f78f556d5fd40e0e4b94dbeb1f5d91";//setAppSecret
        String  json ="{\n" +
                "\"account\": \"13020359@HE\",\n" +
                "\"appDate\": \"2019-01-01\",\n" +
                "\"appSystemId\": \"100001\",\n" +
                "\"assetsCardItems\": [{\n" +
                "\"assetsCardCode\": \"000016797199\",\n" +
                "\"bukrs\": \"A002\",\n" +
                "\"physicalIds\": [{\n" +
                "\"physicalId\": \"131000000000004244617009\"}]}, \n" +
                "{\"assetsCardCode\": \"000016797196\",\n" +
                "\"bukrs\": \"A001\",\n" +
                "\"physicalIds\": [{\"physicalId\": \"131000000000004244617656\"\n" +
                "}, {\"physicalId\": \"1310000000000042446176566\"}]\n" +
                "                }],\n" +
                "\"endMark\": \"1\",\n" +
                "\"instanceId\": \"8a0c988364bdd340016541f3eb4438c3\",\n" +
                "\"moveIn\": \"2713010039\",\n" +
                "\"moveOut\": \"2713010115\",\n" +
                "\"note\": \"备注2020.03.10\",\n" +
                "\"orgCode\": \"2713010115\",\n" +
                "\"orgName\": \"网络运营部\",\n" +
                "\"password\": \"123\",\n" +
                "\"principalName\": \"张秀君\",\n" +
                "\"processCode\": \"zc_tc_bf\",\n" +
                "\"reason\": \"模拟数据\",\n" +
                "\"sapCompanyName\": \"B003\",\n" +
                "\"userId\": \"sunwuk@123\"\n" +
                "}\n";
        HttpBean  httpBean = new HttpBean(url,id,appKey,json);
        String str =httclienService.postToSend(httpBean);
        System.out.println("====>"+str);
    }



    public  String getbo(){
        StrongMssChekbo strongMssChekbo = new StrongMssChekbo();
        strongMssChekbo.setAppSystemId("100001");//应用系统ID
        strongMssChekbo.setUserId("sunwuk@123");
        strongMssChekbo.setPassword("123");//密码
        strongMssChekbo.setProcessCode("zc_tc_bf");//
        strongMssChekbo.setInstanceId("8a0c988364bdd340016541f3eb4438c3");//流程
        strongMssChekbo.setOrgCode("2713010115");//orgCode
        strongMssChekbo.setEndMark("1");//结束
        strongMssChekbo.setOrgName("网络运营部");//发起组织名称
        strongMssChekbo.setAppDate("2019-01-01");//申请日期 必填
        strongMssChekbo.setAccount("13020359@HE"); //发起人账号
        strongMssChekbo.setPrincipalName("张秀君");
        strongMssChekbo.setMoveOut("2713010115");//调出部门
        strongMssChekbo.setMoveIn("2713010039");
        strongMssChekbo.setSapCompanyName("B003"); //sapCompanyName
        strongMssChekbo.setReason("模拟数据");
        strongMssChekbo.setNote("备注2020.03.10");
        strongMssChekbo.setAssetsCardItems(getListAssetsCardItems());

        String json= JSON.toJSONString(strongMssChekbo);
        System.out.println("json"+json);

//        StrongMssChekbo chekbo1 = JSONArray.parseObject(json, StrongMssChekbo.class);
//
//        StrongMssOverallPo mssOverallPo = JSONArray.parseObject(json, StrongMssOverallPo.class);
//        System.out.println("A参:"+chekbo1);
//
//        System.out.println("入参:"+mssOverallPo);
        return json;

        /**
         * 将
         */
    }



    public List<AssetsCardItems> getListAssetsCardItems(){
        List<AssetsCardItems>  assetsCardItems = new ArrayList<>();
        AssetsCardItems  a1  = new AssetsCardItems();
        a1.setAssetsCardCode("000016797196");
        a1.setBukrs("A001");
        List <PhysicalIds> physicalIdsList  = new ArrayList <>();
        physicalIdsList.add(new PhysicalIds("131000000000004244617656"));
        physicalIdsList.add(new PhysicalIds("1310000000000042446176566"));


        a1.setPhysicalIds(physicalIdsList);


        AssetsCardItems  a2  = new AssetsCardItems();
        a2.setAssetsCardCode("000016797199");
        a2.setBukrs("A002");
        List <PhysicalIds> physicalIdsList1 = new ArrayList <>();
        physicalIdsList1.add(new PhysicalIds("131000000000004244617009"));
        a2.setPhysicalIds(physicalIdsList1);

        assetsCardItems.add(a2);
        assetsCardItems.add(a1);

        return  assetsCardItems;
    }
}
