package com.example.demo_sanm.stdtext;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.chinatelecom.bo.flowchg.cardettyalter.CommReponseBo;
import com.chinatelecom.grp.support.bo.busiety.rubo.AssetDetailExcelyunBo;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.Bean2ExcelRowMapper;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.Excel2Bean;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.Excel2BeanRowMapper;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.Excel4Java;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.impl.DefaultExcel4JavaRowMapper;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.impl.Excel4JavaImpl;
import com.sinovate.ngrms.commplt.toolkits.excel4j.usermodel.ExcelSheet;
import com.sinovate.ngrms.gcbscsvr.ps.support.SysBasicConstant;
import com.sinovate.ngrms.gcbscsvr.ps.support.cache.GmDataCacheService;
import com.sinovate.ngrms.gcbscsvr.support.multids.ItspProvCfgConstants;
import com.sinovate.ngrms.gcbscsvr.support.utils.excelResult.PathExcelfileName;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/31.
 * @by: DELL)
 */
public class Excetest {


    private Logger  LOGGER  = org.apache.log4j.Logger.getLogger(getClass());


    @Test
    public  void Test999(){
        List<AssetDetailExcelyunBo> assetDetailExcelyunBos= readExcel();
        for(AssetDetailExcelyunBo bo1:assetDetailExcelyunBos){
            setstr(bo1);
            System.out.println(bo1.getZzSybmCode()+"!!!"+bo1.getZzQxgsCode());
        }
///http://10.128.90.56:10217/gcodemix/ordflow/glyzgorderdetail?
// ordId=450000070000000000172871&posId=20GX0100318001&orderType=1001362&proInstId=711393&staffId=100000400000000000227524&wordItemIds=1502704&optStatus=1
      BigInteger  ordId  = new BigInteger("450000070000000000172871");
        createErrorFile(ordId,"",assetDetailExcelyunBos,1);

    }

    private  void  setstr(AssetDetailExcelyunBo bo){
         bo.setZzQxgsCode("11");
         bo.setZzSybmCode("zzSybmCode");//zzSybmCode
        bo.setZzYytidCode("营业id");//zzYytidCode
        bo.setErrorContent("错误信息");
    }

    private  String sheetName="交资明细清单";
    private   List<AssetDetailExcelyunBo>  readExcel(){
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

//                System.out.println("解析的条数："+i);
//                System.out.println("对象为:"+assetDetailExcelBos.get(i).toString());
                System.out.println("====>"+assetDetailExcelBos.get(i).getAgreeflag());
            }
        }

        return   assetDetailExcelBos;


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
    public  void Test222(){
      String  kostl="宁明客户支撑中心.经营成本(A451406006)";
        String str = "";
        if (kostl.lastIndexOf("(") + 1 > kostl.lastIndexOf(")")) {
            System.out.println("【字典值校验】使用人格式不正确！！");

        }else {
            kostl=  kostl.substring(kostl.lastIndexOf("(") + 1, kostl.lastIndexOf(")"));
        }
    }







    private void createErrorFile(BigInteger id, String sheetName, List<AssetDetailExcelyunBo> assetDetailExcelBos, Integer sfdx) {
        LOGGER.info("导入数据出错，开始生成错误信息！");
        String templateExcelFileName = "C:\\maven_202002\\work_sm\\demo_sanm\\exportAssetDetailTemp.xls";
        LOGGER.info("模板文件为：" + templateExcelFileName);
//		File docPath = SysBasicConstant.getSysPubFileDoc(SysBasicConstant.SYS_PFILE_BUSIDOCS);
        String errorFilePath ="C:\\Users\\DELL\\Desktop\\error" ;//ItspProvCfgConstants.getErrorFilePath() + File.separator + "errorfiles";
        String filename = errorFilePath + File.separator + "ERRORINFOFILE" + File.separator + id.toString() + File.separator + id.toString() + "ERRORINFO"+".xls";
        filename =  PathExcelfileName.filenameFilter(filename);
        LOGGER.info("错误文件为" + filename);
        Excel4Java e4j = new Excel4JavaImpl();
        e4j.createExcelBook(new File(templateExcelFileName));
        Bean2ExcelRowMapper<AssetDetailExcelyunBo> mapper = new DefaultExcel4JavaRowMapper<AssetDetailExcelyunBo>();
        if (StringUtils.isEmpty(sheetName)) {
            sheetName = "交资明细清单";
        }
        e4j.toSheet(assetDetailExcelBos, sheetName, 2, mapper);
        columnHidden(e4j, sheetName, 1, sfdx,false);
        File file = new File(filename);

        String filenamedir = errorFilePath + File.separator + "ERRORINFOFILE" + File.separator + id.toString();
        filenamedir =  PathExcelfileName.filenameFilter(filenamedir);


        if (file.exists()) {
            LOGGER.info("文件存在，写入数据！");
            e4j.write2File(filename);
        } else {
            try {
                LOGGER.info("文件不存在，创建文件，写入数据！");
                new File(filenamedir).mkdirs();
                file.createNewFile();
                e4j.write2File(filename);
            } catch (IOException e) {
                LOGGER.error("无法创建目录文件，错误信息：" + e.toString());
            }
        }
    }



    /**
     * 隐藏不需要看到的模板列
     * @param e4j
     */
    private void columnHidden(Excel4Java e4j, String sheetName, int i, Integer sfdx,boolean isConfirm){
        ExcelSheet sheet = e4j.getBook().getSheet(sheetName);
        sheet.setColumnHidden(0,true);

        sheet.setColumnHidden(1,true);
        //从A列开始
        for(int j = 44; j < 80; j++) {
            sheet.setColumnHidden(j,true);
        }
        if (sfdx == 0) {
            //非大修工程
            sheet.setColumnHidden(33, true);
        } else if (sfdx == 1) {
            //大修工程
            sheet.setColumnHidden(33, false);
        }
        if(i == 1) {
            sheet.setColumnHidden(43, false);
            sheet.setColumnHidden(44, false);
        } else if(i == 0) {
            sheet.setColumnHidden(43, true);
            sheet.setColumnHidden(44, true);
        }
        if(isConfirm){
            sheet.setColumnHidden(48, false);
        }else{
            sheet.setColumnHidden(48, true);
        }
    }



}
