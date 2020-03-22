package com.example.demo_sanm.dwr;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.chinatelecom.grp.support.bo.comm.PageRowBounds;
import com.example.demo_sanm.yunexcel.ExcelData;
import com.example.demo_sanm.yunexcel.ExportExcelyunUtils;
import com.sinovate.ngrms.comm.ptfcore.web.pagination.Page;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.Excel4Java;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.impl.Excel4JavaImpl;
import com.sinovate.ngrms.gcbscsvr.mdl.bo.itspqry.GetResourceBo;
import com.sinovate.ngrms.gcbscsvr.mdl.vo.ResourceVo;
import com.sinovate.ngrms.gcbscsvr.ps.support.cache.GmDataCacheService;
import org.apache.log4j.Logger;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.io.FileTransfer;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
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

    private Logger LOGGER =Logger.getLogger(getClass());

    @RemoteMethod
    public String hello() {
        return "hello";
    }

    @RemoteMethod
    public String echo(String string) {
        return string;
    }


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

    @RemoteMethod
    @RequestMapping("/exportExcleResourceByParam")
    @ResponseBody
    public FileTransfer  exportExcleResourceByParam(HttpServletResponse response, String modelIdName) throws Exception {
        LOGGER.info("=====   Welcome to 综合查询资源信息   export ======");
        //LOGGER.info("==资源信息入参==={} 分页参数{}",getResourceBo,pageRequest);
        String reportName = "综合查询资源信息清单";
        LOGGER.info("调资源信息查询方法");
       // Page<ResourceVo> resourceVoPage= getResourceByParam(getResourceBo,modelIdName,pageRequest,qryType);
        //LOGGER.info("===综合查询资源信息量："+resourceVoList.size());
        LOGGER.info("开始导出");
        ExcelData data = new ExcelData();
        data.setFields(fields);//第二行标题
        data.setTableName(reportName);//第一行
        List<List<Object>> rows = new ArrayList();
            rows= toRowsbyresourceVo();

        LOGGER.info("临时文件数据条数："+rows.size());
        data.setRows(rows);

//   //     return new FileTransfer("1.xls", "application/msexcel", bos1)
//        //写出流  刷新缓冲流  关闭流对象
//        LOGGER.info("❋❋❋❋❋❋❋❋Thak you!  ❋❋❋❋❋❋❋❋❋❋❋");
//        response.getOutputStream().flush();
//        response.getOutputStream().close();

        return   ExportExcelyunUtils.exportExcel(response,"综合查询资源信息清单.xls",data);


    }


    /**
     * 这是最基础的,提交
     * @return
     */
    private   List<List<Object>>  toRowsbyresourceVo( ){
        List<List<Object>> rows = new ArrayList();
        for(int i=0;i<9;i++){
            List<Object> row = new ArrayList();
            row.add("资源名称"+i);//资源名称
            row.add("资源编码"+1);//资源编码
            row.add("第"+i+"数据");
            row.add("第"+i+"数据");
            row.add("第"+i+"数据");
            row.add("第"+i+"数据");
            row.add("第"+i+"数据");
            row.add("第"+i+"数据");
            row.add("第"+i+"数据");
            row.add("第"+i+"数据");
            row.add("第"+i+"数据");
            row.add("第"+i+"数据");
            row.add("第"+i+"数据");
            row.add("第"+i+"数据");
            //虚实属性前台"第)+i+"数据
 ;           row.add("第"+i+"数据");
            rows.add(row);
        }

        return  rows;

    }








}
