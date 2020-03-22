package com.example.demo_sanm.testyun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.chinatelecom.bo.flowchg.cardettyalter.CommReponseBo;
import com.chinatelecom.prov.strongAssetOrder.checkObject.impl.StrongMssOverallPo;
import com.example.demo_sanm.contro.rubo.AssetsCardItems;
import com.example.demo_sanm.contro.rubo.PhysicalIds;
import com.example.demo_sanm.contro.rubo.StrongMssChekbo;
import org.apache.commons.collections.list.PredicatedList;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/10.
 * @by: DELL)
 */
public class Testbo {



    @Test
    public  void md5test(){
       String pass = md5("45000644@GX");
       System.out.println("====>"+pass);
    }

    private String md5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("md5加密异常：" + e.getMessage());
        }
        return null;
    }


    @Test
    public  void Test1222(){
       String ZzZclb="4";
        List<String> zclbs = new ArrayList <>();
        zclbs.add("1");
        zclbs.add("2");
        zclbs.add("3");
        int i = 0;
        for (String zclb : zclbs) {

            if (!ZzZclb.equals(zclb)) {
                i++;
            }
        }
        if (i == zclbs.size()) {
            System.out.println("作业成本资产类别与资产目录"+ZzZclb+"===>"+i);
          //  return new CommReponseBo(false, "【作业成本资产类别与资产目录、资产归属关系匹配错误】");
            // stringBuilder.append("," + eeOrderitemAssetdetail.getOsszseq() + ":作业成本资产类别与资产目录、资产归属关系匹配错误");
        }
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

        StrongMssChekbo chekbo1 = JSONArray.parseObject(json, StrongMssChekbo.class);

        StrongMssOverallPo mssOverallPo = JSONArray.parseObject(json, StrongMssOverallPo.class);
        System.out.println("A参:"+chekbo1);

         System.out.println("入参:"+mssOverallPo);

        /**
         * 将
         */
    }



  public  List<AssetsCardItems>  getListAssetsCardItems(){
        List<AssetsCardItems>  assetsCardItems = new ArrayList <>();
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
