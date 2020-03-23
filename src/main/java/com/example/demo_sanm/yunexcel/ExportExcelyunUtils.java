package com.example.demo_sanm.yunexcel;


import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.example.demo_sanm.interfacesvc.ColumnConfig;
import com.example.demo_sanm.interfacesvc.TableConFiG;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFBorderFormatting;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.directwebremoting.io.FileTransfer;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2019/3/4.
 * @by: DELL)
 * 用于Excel信息封装
 */
@Service
public class ExportExcelyunUtils implements  ExportExcelyunSvc{

    private static Logger logger  = Logger.getLogger(ExportExcelyunUtils.class);

    /**
     * 时间格式转换
     * @param date
     * @return
     */
    public  static String dateyuntoStr(Date date){
        if(date ==  null) return  null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return  sdf.format(date);
    }


    /**
     *
     * @param response
     * @param fileName
     * @param voList
     * @param beans
     * @param <T>
     * @return
     * @throws Exception
     */
    public   <T> FileTransfer exportExcel(HttpServletResponse response, String fileName,List<T> voList , T beans) throws Exception {
        ExcelData  data  =new ExcelData();
        String tablename =gettableNamebyBean(beans);
        data.setTableName(tablename);
        List <Object> objectList =new ArrayList<Object>();
        getFiledsbyBean(beans,objectList);
        String[] strings = new String[objectList.size()];
        data.setFields(objectList.toArray(strings));//第二行标题
        List<List<Object>> rows = new ArrayList <List <Object>>();
        if(CollectionUtils.isNotEmpty(voList)){
            torows(voList,rows);
        }
        data.setRows(rows);
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Disposition", "attachment; filename="+new String(fileName.getBytes("gbk"),"iso-8859-1"));
        byte[] bos1=  exportExcel(data);
        return new FileTransfer(fileName+".xls", "application/msexcel", bos1);

    }



    /**
     *创建Sheet
     * @param data
     * @throws Exception
     */
    public static byte[] exportExcel(ExcelData data) throws Exception {
        logger.debug("======开始导出数据===入参："+data);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        HSSFWorkbook wb = new HSSFWorkbook();//用于xls
        try {
            String sheetName = data.getName();
            if (null == sheetName) {
                sheetName = "Sheet1";
            }
            HSSFSheet sheet = wb.createSheet(sheetName);
            writeExcel(wb, sheet, data);//
            wb.write(bos);
            //   byte[] bos1 = bos.toByteArray();
            //  return  bos1;

            logger.info("写入临时日志!");
        }catch (Exception e){
            logger.error("异常信息"+e);
        }
//        } finally {
//            wb.close();
//        }
        return  bos.toByteArray();
    }

    private static void writeExcel(HSSFWorkbook wb, Sheet sheet, ExcelData data) {
//         logger.info("======开始写入======");
        int rowIndex = 0;
        rowIndex = writeFieldsDateToExcel(wb, sheet, data);//
//         logger.debug("======================"+data.getTableName());
        //写入第一行表头
        writeTableNameToExcel(wb, sheet, data);
        //写入数据
        writeRowsToExcel(wb, sheet, data.getRows(), rowIndex);
        autoSizeColumns(sheet, data.getFields().length + 1);
    }

    private  static  int writeTableNameToExcel(HSSFWorkbook wb, Sheet sheet, ExcelData data){


        int rowIndex = 0;
        int colIndex = 0;

        Font titleFont = wb.createFont();
        titleFont.setFontName("simsun");
        // titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 22);
        titleFont.setColor(IndexedColors.GREY_80_PERCENT.index);

        //合并的单元格样式
        HSSFCellStyle boderStyle = wb.createCellStyle();
        boderStyle.setFillForegroundColor((short) 36);

        //垂直居中
        boderStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        boderStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        //设置一个边框
        boderStyle.setBorderTop(HSSFBorderFormatting.BORDER_THICK);
        boderStyle.setFont(titleFont);

        // 合并单元格：参数：起始行, 终止行, 起始列, 终止列
        CellRangeAddress cra = new CellRangeAddress(0, 0, 0, data.getFields().length-1);
        sheet.addMergedRegion(cra);
        Row headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints(30);
        Cell headerCell;

        headerCell = headerRow.createCell(0);
        headerCell.setCellValue(data.getTableName()); //设备编号
        headerCell.setCellStyle(boderStyle);


        return rowIndex;

    }


    private  static  int writeFieldsDateToExcel(HSSFWorkbook wb, Sheet sheet, ExcelData data){


        int rowIndex = 1;
        int colIndex = 0;

        Font titleFont = wb.createFont();
        titleFont.setFontName("simsun");
        // titleFont.setBold(true);
        // titleFont.setFontHeightInPoints((short) 14);
        titleFont.setColor(IndexedColors.BLACK.index);

//        XSSFCellStyle titleStyle = wb.createCellStyle();
        HSSFCellStyle titleStyle = wb.createCellStyle();

        titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        titleStyle.setFillForegroundColor((short) 17);//
        titleStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        titleStyle.setFont(titleFont);
        setBorder(titleStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)),wb);

        Row titleRow = sheet.createRow(rowIndex);
        // titleRow.setHeightInPoints(25);
        colIndex = 0;

        for (String field : data.getFields()) {
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(field);
            cell.setCellStyle(titleStyle);
            colIndex++;
        }

        rowIndex++;
        return rowIndex;

    }






    private static int writeRowsToExcel(HSSFWorkbook wb, Sheet sheet, java.util.List<java.util.List<Object>> rows, int rowIndex) {
        int colIndex = 0;

        Font dataFont = wb.createFont();
        dataFont.setFontName("simsun");
        // dataFont.setFontHeightInPoints((short) 14);
        dataFont.setColor(IndexedColors.BLACK.index);

        HSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        dataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        dataStyle.setFont(dataFont);
        setBorder(dataStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)),wb);

        for (java.util.List<Object> rowData : rows) {
            Row dataRow = sheet.createRow(rowIndex);
            // dataRow.setHeightInPoints(25);
            colIndex = 0;

            for (Object cellData : rowData) {
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null) {
                    cell.setCellValue(cellData.toString());
                } else {
                    cell.setCellValue("");
                }

                cell.setCellStyle(dataStyle);
                colIndex++;
            }
            rowIndex++;
        }
        return rowIndex;
    }

    private static void autoSizeColumns(Sheet sheet, int columnNumber) {

        //2019.03.12 如果字符存在超过255的处理下
        for (int i = 0; i < columnNumber; i++) {
            int orgWidth = sheet.getColumnWidth(i);
//            logger.info("====orgwidth:"+orgWidth);
            sheet.autoSizeColumn(i, true);
            int newWidth = (int) (sheet.getColumnWidth(i) + 100);
//            logger.info("====newWidth:"+newWidth);

//            if(colWidth<255*256){
//                sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
//            }else{
//                sheet.setColumnWidth(i,6000 );
//            }

            if (newWidth > orgWidth) {
                if(newWidth>255*256){
                    sheet.setColumnWidth(i, newWidth < 3000 ? newWidth : 12000);
                }else {
                    sheet.setColumnWidth(i, newWidth);
                }
            } else {
                sheet.setColumnWidth(i, orgWidth);
            }
        }
    }

    private static void setBorder(HSSFCellStyle style, BorderStyle border, XSSFColor color, HSSFWorkbook wb) {
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

//        HSSFFont font = wb.createFont();
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
//        font.setFontHeightInPoints((short) 16);//设置字体大小
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

    }



    private static   <T> String  gettableNamebyBean(T beans) {
        Class <?> beanType = beans.getClass();
        TableConFiG tableConFiG = beanType.getAnnotation(TableConFiG.class);
        String str= tableConFiG.value();
        logger.info("===>"+str);
        return str;

    }

    public static  <T> void getFiledsbyBean(T beans,List<Object> objectList ) {
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
                        logger.debug("====>" + str);
                        objectList.add(str);
                    }
                }

            }

        }
    }



    public  static  <T> void torows(List<T> beans,List<List<Object>> rows) {


        for(T bean : beans) {
            Class<?> beanType = bean.getClass();
            Field[] fields = beanType.getDeclaredFields();
            logger.debug("fields"+fields);
            Field field = null;

            List<Object> objectList = new ArrayList <Object>();
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


}
