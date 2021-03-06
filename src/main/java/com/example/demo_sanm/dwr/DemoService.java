package com.example.demo_sanm.dwr;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.chinatelecom.bo.flowchg.cardettyalter.CommReponseAllBo;
import com.chinatelecom.grp.support.bo.comm.PageRowBounds;
import com.example.demo_sanm.contro.excel.ErorerBo;
import com.example.demo_sanm.interfacesvc.ColumnConfig;
import com.example.demo_sanm.yunexcel.ExcelData;
import com.example.demo_sanm.yunexcel.ExportExcelyunSvc;
import com.example.demo_sanm.yunexcel.ExportExcelyunUtils;
import com.sinovate.ngrms.comm.ptfcore.web.pagination.Page;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.Excel2Bean;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.Excel2BeanRowMapper;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.Excel4Java;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.impl.DefaultExcel4JavaRowMapper;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.impl.Excel4JavaImpl;
import com.sinovate.ngrms.commplt.toolkits.excel4j.usermodel.ExcelRow;
import com.sinovate.ngrms.gcbscsvr.mdl.bo.itspqry.GetResourceBo;
import com.sinovate.ngrms.gcbscsvr.mdl.bo.ordflow.AssetQryBo;
import com.sinovate.ngrms.gcbscsvr.mdl.db.gitsp.rprj.RmAsset;
import com.sinovate.ngrms.gcbscsvr.mdl.vo.ResourceVo;
import com.sinovate.ngrms.gcbscsvr.mdl.vo.ordflow.ResEntityScrapExceVo;
import com.sinovate.ngrms.gcbscsvr.mdl.vo.ordflow.ResEntityVo;
import com.sinovate.ngrms.gcbscsvr.ps.support.cache.GmDataCacheService;
import com.sinovate.ngrms.gcbscsvr.support.multids.ItspProvCfgConstants;
import com.sinovate.ngrms.gcbuapp.abl.gcodemix.svc.trafixast.AblCardEntitySvc;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.io.FileTransfer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/9.
 * @by: DELL)
 *
 * https://segmentfault.com/a/1190000012705229
 */
@Service
@RemoteProxy
public class DemoService {

    Logger  logger = Logger.getLogger(getClass());


    private Logger LOGGER =Logger.getLogger(getClass());

    @RemoteMethod
    public String hello() {
        return "hello";
    }

    @RemoteMethod
    public String echo(String string) {
        return string;
    }

    @Autowired
    private ExportExcelyunSvc exportExcelyunSvc;


    /**
     * Excel模板的导出
     * @param userAccount
     * @return
     */
    @RemoteMethod
    @RequestMapping("/exportResourceInExcel")
    @ResponseBody
    public FileTransfer exportResourceInExcel(String userAccount){
        LOGGER.info("查询的方法："+userAccount);
        HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");


        String templateExcelFileName ="C:\\Users\\DELL\\Desktop\\TestExcel\\QueryResourcebytemplate.xls"; //GmDataCacheService.getResourceResource_newFile()  + "ResourceExce"+ File.separator+userAccount;
      //  String templateExcelFileName = filenamedir+File.separator+fileName;
        LOGGER.info("导出生成好的文件：:"+templateExcelFileName);
        Excel4Java e4j = new Excel4JavaImpl();
        e4j.createExcelBook2(new File(templateExcelFileName));
        //e4j.setSheetHidden(sheetName1, HIDDEN);   www
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        e4j.write2Stream(bos);
        LOGGER.info("返回导出信息模板");
        String agent = request.getHeader("User-Agent");
        String fileName="转固工单导出.xls";
        try {
            if(null != agent && (agent.contains("MSIE") || agent.contains("rv:11"))) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }else{
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(),e);
        }
        //具体业务逻辑
        byte[] bos1 = bos.toByteArray();
        return new FileTransfer(fileName, "application/msexcel", bos1);
    }

    private final String[] fields=  new String[]{"资源名称","资源编码","资源类型","集团编码",
            "资源GID","资源所属地区","资源型号","长度","资源厂家","安装地址","资产卡片编码",
            "卡片公司代码","资产目录","资源规格ID","虚实属性",};



    @Autowired
    @Qualifier("ablCardEntitySvcImpl")
    private AblCardEntitySvc ablCardEntitySvc;

    /**
     * 后台生成Excel
     * @param response
     * @param modelIdName
     * @return
     * @throws Exception
     */
    @RemoteMethod
    @RequestMapping("/exportExcleResourceByParam")
    @ResponseBody
    public CommReponseAllBo  exportExcleResourceByParam(HttpServletResponse response, String modelIdName) throws Exception {
        LOGGER.info("=====   Welcome to 综合查询资源信息   export ======");
        AssetQryBo assetQryBo = new AssetQryBo();
        assetQryBo.setAssetsCardCode(modelIdName);
        String relEntityType="1";
        String domain="45";
        boolean  isRelRes =false;
        boolean isRelPrj=false;
        boolean isProvince=true;
        String areaCode="324048";
        String moreConditions="ISFORSRCAP";
        CommReponseAllBo commReponseAllBo=    ablCardEntitySvc.Assetretirement( getExcelbo(),relEntityType, domain,
                false,false,
                isProvince, areaCode,moreConditions);
        System.out.println("===>getErrorMsggetErrorMsg"+commReponseAllBo.getErrorMsg());
     //   System.out.println("===>getRmAssetList"+commReponseAllBo.getRmAssetList().get(0).getAssetscardcode());

        FileTransfer fileTransfer=  new FileTransfer("111.xls", "application/msexcel", (byte[]) commReponseAllBo.getBos());// exportExcelyunSvc.exportExcel(response,"综合资源信息.xls",getlist(),new ErorerBo());
        commReponseAllBo.setBos(fileTransfer);
        return  commReponseAllBo;
    }


    @Test
    public  void Test(){
        getExcelbo();
    }


    public List<ResEntityScrapExceVo>  getExcelbo(){

        List<ResEntityScrapExceVo>  resEntityScrapExceVos = new ArrayList <>();

           File targetFile =new File("E:\\windowtoll\\test\\exportAssetTerm.xls") ;//copyFile(filename, inputStream);
            Excel2Bean e4j = new Excel4JavaImpl();
            e4j.createExcelBook(targetFile);
            Excel2BeanRowMapper<ResEntityScrapExceVo> rowMapper = new AssetDetailExcelBoResEntityScrapExceVo();
            //从excel模板中转化为excelBo
            List<ResEntityScrapExceVo> assetDetailExcelBos = e4j.toBeans("资产条件", 2, Integer.MAX_VALUE, rowMapper);
          //  System.out.println("===>"+assetDetailExcelBos);
            if(CollectionUtils.isNotEmpty(assetDetailExcelBos)){
                for(ResEntityScrapExceVo vo:assetDetailExcelBos){
                    System.out.println("====>"+vo.getAssetscardcode());
                    if(vo.getAssetscardcode() !=null && StringUtils.isNotEmpty(vo.getAssetscardcode() ) &&  vo.getAssetscardcode() !="null"  ){
                        resEntityScrapExceVos.add(vo);
                    }
                }
            }
            System.out.println("====最后结果"+resEntityScrapExceVos);

            return resEntityScrapExceVos;


    }


    class AssetDetailExcelBoResEntityScrapExceVo extends DefaultExcel4JavaRowMapper<ResEntityScrapExceVo> {

        @Override
        protected ResEntityScrapExceVo createBean() {
            return new ResEntityScrapExceVo();
        }

        @Override
        protected ResEntityScrapExceVo postRow2Bean(ResEntityScrapExceVo bean) {
            return bean;
        }

    }



    /**
     * 软件资产的导入
     */


    /**
     *其实将对象封装成Excel  和  数据库的数据  和 对象交互 orm类型
     *  ---参考  mybatis 纯手写代码
     *
     * 这是最基础的,提交
     * @return
     */


    private   List<ErorerBo> getlist(){
        ErorerBo erorerBo1  = new   ErorerBo("code1","name1");
        ErorerBo erorerBo2  = new   ErorerBo("code2","name2");
        List<ErorerBo> erorerBoList = new ArrayList <>();

        List<List<Object>> rows = new ArrayList();
//        erorerBoList.add(erorerBo1);
//        erorerBoList.add(erorerBo2);
        return   erorerBoList;
    }


    private   List<List<Object>>  toRowsbyresourceVo( ){
//        List<List<Object>> rows = new ArrayList();
//        for(int i=0;i<9;i++){
//            List<Object> row = new ArrayList();
//            row.add("资源名称"+i);//资源名称
//            row.add("资源编码"+1);//资源编码
//            row.add("第"+i+"数据");
//            row.add("第"+i+"数据");
//            row.add("第"+i+"数据");
//            row.add("第"+i+"数据");
//            row.add("第"+i+"数据");
//            row.add("第"+i+"数据");
//            row.add("第"+i+"数据");
//            row.add("第"+i+"数据");
//            row.add("第"+i+"数据");
//            row.add("第"+i+"数据");
//            row.add("第"+i+"数据");
//            row.add("第"+i+"数据");
//            //虚实属性前台"第)+i+"数据
// ;           row.add("第"+i+"数据");
//            rows.add(row);
//        }
//
//        return  rows;
        ErorerBo erorerBo1  = new   ErorerBo("code1","name1");
        ErorerBo erorerBo2  = new   ErorerBo("code2","name2");
        List<ErorerBo> erorerBoList = new ArrayList <>();

        List<List<Object>> rows = new ArrayList();
        erorerBoList.add(erorerBo1);
        erorerBoList.add(erorerBo2);
        toSheet(erorerBoList,rows);

        return  rows;


    }


    public <T> void toSheet(List<T> beans,List<List<Object>> rows) {


        for(T bean : beans) {
            Class<?> beanType = bean.getClass();
            Field[] fields = beanType.getDeclaredFields();
            logger.debug("fields"+fields);
            Field field = null;

            List<Object> objectList = new ArrayList <>();
            for (int i = 0; i < fields.length; i++) {

                field = fields[i];


                if( beanType.isMemberClass()  && "this$0".equals(field.getName())) continue;
                // 外部类的this属性判断
                if(field.getType().getSimpleName().equals(bean.getClass().getSimpleName())) {
                    // 如果是this属性就取下一个字段； 此类判断对内部类无法生效
                    continue;
                }
                // 如果是static字段，取下一个字段

                if(Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                try {
                    objectList.add(field.get(bean));
                    //   rows.add(objectList);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
            rows.add(objectList);
        }
        System.out.println("====》"+rows);
    }






    public <T> void toSheet2(T beans,List <Object> objectList ) {
        // List <Object> objectList = new ArrayList <>();
        Class <?> beanType = beans.getClass();
        Field[] fields = beanType.getDeclaredFields();
        logger.debug("fields" + fields);
        Field field = null;
        for (int i = 0; i < fields.length; i++) {
            //   List <Object> objectList = new ArrayList <>();
            field = fields[i];
            if (field.isAnnotationPresent(ColumnConfig.class)) {
                for (Annotation anno : field.getDeclaredAnnotations()) {
                    logger.info("所有注解" + anno);
                    if (anno.annotationType().equals(ColumnConfig.class)) {
                        String str = ((ColumnConfig) anno).descriptionyun();
                        logger.info("====>" + str);
                        objectList.add(str);
                    }
                }

            }

        }
    }








}
