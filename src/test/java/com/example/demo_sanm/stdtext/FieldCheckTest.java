package com.example.demo_sanm.stdtext;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.AbstractConfig;
import com.chinatelecom.bo.flowchg.cardettyalter.CommReponseBo;
import com.chinatelecom.grp.support.bo.busiety.AssetDetailExcelBo;
import com.chinatelecom.grp.support.bo.busiety.rubo.AssetDetailExcelyunBo;
import com.chinatelecom.grp.wfact.WfDataValidationSvc;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.Excel2Bean;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.Excel2BeanRowMapper;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.impl.DefaultExcel4JavaRowMapper;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.impl.Excel4JavaImpl;
import com.sinovate.ngrms.gcbuapp.abl.gcodemix.svc.intqry.FieldCheck;
import com.sinovate.ngrms.gcbuapp.abl.gcodemix.svc.trafixast.AblAssetDetailSvc;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
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

 // '' private   Logger  logger  = Logger.getLogger(getClass());
    protected static final Logger logger = LoggerFactory.getLogger(FieldCheckTest.class);


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
      List list=  wfDataValidationSvc.getPrctrList(posid);
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



    @Test
    public  void Test13(){
        AssetDetailExcelBo  assetDetailExcelBo = new AssetDetailExcelBo();
        assetDetailExcelBo.setZzZclb("");
        assetDetailExcelBo.setZzKey("01110201");
        assetDetailExcelBo.setOrd41("本地网及其他");
    System.out.println("====>"+        fieldCheck.CheckZzZclb(assetDetailExcelBo));

    }


    @Autowired
    private AblAssetDetailSvc ablAssetDetailSvc;


    /**
     * 测试资产管理员导入的问题
     *
     欢迎您，李美阳| 个人设置 | 退出
     当前位置：预转固 > 工程编号 ：20GX0100318001
     */
//http://10.128.90.56:10217/gcodemix/ordflow/glyzgorderdetail?
// ordId=450000070000000000172871&posId=20GX0100318001&orderType=1001362&proInstId=711393&staffId=100000400000000000227524&wordItemIds=1502704&optStatus=1
    @Test
    public  void TestExcel(){
        List< AssetDetailExcelyunBo > paramList =readExcel(); new ArrayList <>();
        BigInteger orderId=new BigInteger("450000070000000000172871");
        String posid="20GX0100318001";
        /*  解析文件*/


        /* 调所需要的接口 */

        //public CommReponseBo importAuditorVisible(BigInteger orderId, String posid, List< AssetDetailExcelyunBo > paramList, int  type);

        CommReponseBo  commReponseBo =  ablAssetDetailSvc.importAuditorVisible(orderId,posid,paramList,1);
        System.out.println("===>"+commReponseBo);

    }

//C:\maven_202002\work_sm\demo_sanm
    @Test
    public  void Test999(){
         readExcel();
    }

     private  String sheetName="交资明细清单";
    private   List< AssetDetailExcelyunBo >  readExcel(){
       File targetFile = new File("C:\\maven_202002\\work_sm\\demo_sanm\\yunhuitest.xls");//copyFile(filename, inputStream);
        Excel2Bean e4j = new Excel4JavaImpl();
        e4j.createExcelBook(targetFile);
        Excel2BeanRowMapper<AssetDetailExcelyunBo> rowMapper = new AssetDetailExcelBoRowMapperExtendDefault();
        //从excel模板中转化为excelBo
        List<AssetDetailExcelyunBo> assetDetailExcelBos = e4j.toBeans(sheetName, 2, Integer.MAX_VALUE, rowMapper);
      //  System.out.println("===>"+assetDetailExcelBos);
        if (CollectionUtils.isNotEmpty(assetDetailExcelBos)) {
            for (int i = 0; i < assetDetailExcelBos.size(); i++) {
                assetDetailExcelBos.get(i).setSeqNo(i + "");

               // System.out.println("解析的条数："+i);
               System.out.println("对象为:"+assetDetailExcelBos.get(i).toString());
            }
        }

        return    assetDetailExcelBos;


    }



    class AssetDetailExcelBoRowMapperExtendDefault extends DefaultExcel4JavaRowMapper<AssetDetailExcelyunBo> {

        @Override
        protected AssetDetailExcelyunBo createBean() {
            return new AssetDetailExcelyunBo();
        }

        @Override
        protected AssetDetailExcelyunBo postRow2Bean(AssetDetailExcelyunBo bean) {
            return bean;
        }

    }



@Test
public  void Test11(){
    AssetDetailExcelyunBo  excelBo = new AssetDetailExcelyunBo();
    excelBo.setZzYxzx("保安分局");
    List<AssetDetailExcelyunBo>  excelBolist = new ArrayList <>();
    excelBolist.add(excelBo);

    checkExcelBo1(excelBolist);
    System.out.println(excelBolist.get(0).getZzYxzxCode());
}


public   void   checkExcelBo1 (List<AssetDetailExcelyunBo>  excelBo ){
    StringBuilder stringBuilder = new StringBuilder();
    for(AssetDetailExcelyunBo excelyunBo:excelBo) {
        setStringerr(excelyunBo, fieldCheck.checkzzyxzx(excelyunBo.getZzYxzx()), stringBuilder, "ZzYxzx");
        System.out.println("===>" + excelyunBo.getZzYxzxCode());
    }

}


    private  void setStringerr(AssetDetailExcelyunBo excelyunBo,CommReponseBo  bo1,StringBuilder stringBuilder,String field){
        if(!bo1.getStatus()){
            stringBuilder.append(bo1.getErrorMsg());
        }else {
            if (StringUtils.isNotEmpty(field)) {
                if (field.equals("zzsybm")) {
                    excelyunBo.setZzSybmCode(bo1.getErrorMsg());
                    logger.debug("setZzSybmCode===>"+bo1+"====>"+excelyunBo.getZzSybmCode());
                }
                if(field.equals("zzbgy")){
                    excelyunBo.setZzBgyCode(bo1.getErrorMsg());
                }
                if(field.equals("Raumn")){
                    excelyunBo.setRaumnCode(bo1.getErrorMsg());
                }
                if(field.equals("Kostl")){
                    excelyunBo.setKostlCode(bo1.getErrorMsg());
                }
                if(field.equals("ZzQxgs")){
                    excelyunBo.setZzQxgsCode(bo1.getErrorMsg());
                }
                if(field.equals("ZzYxzx")){
                    excelyunBo.setZzYxzxCode(bo1.getErrorMsg());
                }
                if(field.equals("ZZyytid")){
                    excelyunBo.setZzYytidCode(bo1.getErrorMsg());
                }

            }
        }
    }




}
