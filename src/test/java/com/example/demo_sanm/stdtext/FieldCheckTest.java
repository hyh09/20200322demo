package com.example.demo_sanm.stdtext;

import com.chinatelecom.bo.flowchg.cardettyalter.CommReponseBo;
import com.chinatelecom.grp.wfact.WfDataValidationSvc;
import com.sinovate.ngrms.gcbuapp.abl.gcodemix.svc.intqry.FieldCheck;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/30.
 * @by: DELL)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FieldCheckTest {


    @Autowired
    private FieldCheck fieldCheck;

    @Autowired
    @Qualifier("wfDataValidationSvcImpl")
    private WfDataValidationSvc wfDataValidationSvc;

    //所在地点
    @Test
    public  void Test1(){
        //1.所在地点只有一个长度校验
        System.out.println("所在地点校验结果:"+fieldCheck.checkanlhtxt("1111111111111"));
        System.out.println("机房编码校验结果"+fieldCheck.checkzzJzid("机房编码"));
        System.out.println("机房名称结果"+fieldCheck.checkbasestatname("机房名称"));
        System.out.println("使用部门校验"+fieldCheck.checkzzsybm("政企客户支撑中心-经营成本(A450500501)",getlist("20GX0100318001")));

    }
//http://10.128.90.56:10217/gcodemix/ordflow/glyzgorderdetail?ordId=450000070000000000172871&posId=20GX0100318001&orderType=1001362&proInstId=711393&staffId=100000400000000000227524&wordItemIds=1502704&optStatus=1
    public List<String> getlist(String posid) {
      List  list=  wfDataValidationSvc.getPrctrList(posid);
      System.out.println("list:"+list);
      return  list;//政企客户支撑中心-经营成本(A450500501)
    }


    @Test
    public   void Test12(){
        System.out.println("使用部门校验"+fieldCheck.checkzzsybm("崇左-宁明县分公司-宁明客户支撑中心(A451406006)",getlist("20GX0100318001")));
        CommReponseBo  bo11= fieldCheck.checkzzsybm("崇左-宁明县分公司-宁明客户支撑中心(A451406006)",getlist("20GX0100318001"));
        System.out.println("====>"+bo11);
        if(bo11.getStatus()){

            CommReponseBo bo= fieldCheck.checkzzbgy("李美阳(45140137@GX)",bo11.getErrorMsg());
            System.out.println("校验的结果:"+bo);
            CommReponseBo bo1= fieldCheck.checkzzbgy("阿茹娜(71003662@GX)",bo11.getErrorMsg());
            System.out.println("校验的结果1:"+bo1);

        }
//        CommReponseBo bo= fieldCheck.checkzzbgy("李美阳(45140137@GX)","A451406006");
//        System.out.println("校验的结果:"+bo);
//        CommReponseBo bo1= fieldCheck.checkzzbgy("阿茹娜(71003662@GX)","A451406006");
//        System.out.println("校验的结果1:"+bo1);



    }
}
