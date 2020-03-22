package com.example.demo_sanm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.chinatelecom.bo.flowchg.cardettyalter.CommReponseBo;
import com.chinatelecom.bo.flowchg.cardettyalter.ResultBos;
import com.chinatelecom.prov.strongAssetOrder.checkObject.impl.StrongMssOverallPo;
import com.example.demo_sanm.contro.rubo.AssetsCardItems;
import com.example.demo_sanm.contro.rubo.PhysicalIds;
import com.example.demo_sanm.contro.rubo.StrongMssChekbo;
import com.sinovate.ngrms.gcbuapp.abl.gcodemix.svc.intqry.FieldCheck;
import com.sinovate.ngrms.gcbuapp.abl.gcodemix.svc.intqry.StrongRelatinCheckSvc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/13.
 * @by: DELL)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrongRelatinCheckSvcTest {


//    @Autowired
//    private RestTemplate restTemplate;


    @Test
    public  void Test11111(){
        String   url ="";
       // 148cfeea9ffe51c1b8f9ee9d72a9b088
        //1b9a55db798729c872cb3555e85d421a

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("api-version", "1.0");
        //body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("roundid", "1");
        //HttpEntity
          HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(requestBody, requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
          //post
          ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
          System.out.println(responseEntity.getBody());


    }



        @Autowired
    private FieldCheck fieldCheck;


        @Autowired
        private StrongRelatinCheckSvc strongRelatinCheckSvc;

        @Test
    public  void FieldCheckTest(){
        CommReponseBo commReponseBo=  fieldCheck.CheckZzZclb("609901","123456789011","A003");
        System.out.println("===返回数据"+commReponseBo);
    }



    @Test
    public  void Test1(){
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
        System.out.println(json);
        ResultBos  resultBos =strongRelatinCheckSvc.StrongChecklogic(json);
        System.out.println("===>"+resultBos);
        String jsoresultBosn= JSON.toJSONString(resultBos);
        System.out.println("====>"+jsoresultBosn);
//        StrongMssChekbo chekbo1 = JSONArray.parseObject(json, StrongMssChekbo.class);
//
//        StrongMssOverallPo mssOverallPo = JSONArray.parseObject(json, StrongMssOverallPo.class);
//        System.out.println("A参:"+chekbo1);
//
//        System.out.println("入参:"+mssOverallPo);

        /**
         * 将
         */
    }



    public List<AssetsCardItems> getListAssetsCardItems(){
        List<AssetsCardItems>  assetsCardItems = new ArrayList<>();
        AssetsCardItems  a1  = new AssetsCardItems();
        a1.setAssetsCardCode("000000681305");
        a1.setBukrs("A001");
        List <PhysicalIds> physicalIdsList  = new ArrayList <>();
        physicalIdsList.add(new PhysicalIds("451903020000000002028743"));//实物
//        physicalIdsList.add(new PhysicalIds("1310000000000042446176566"));


        a1.setPhysicalIds(physicalIdsList);


//        AssetsCardItems  a2  = new AssetsCardItems();
//        a2.setAssetsCardCode("000016797199");
//        a2.setBukrs("A002");
//        List <PhysicalIds> physicalIdsList1 = new ArrayList <>();
//        physicalIdsList1.add(new PhysicalIds("131000000000004244617009"));
//        a2.setPhysicalIds(physicalIdsList1);
//
//        assetsCardItems.add(a2);
        assetsCardItems.add(a1);

        return  assetsCardItems;
    }
}
