package com.example.demo_sanm.filetext;

import com.example.demo_sanm.interfacesvc.TableConFiG;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/28.
 * @by: DELL)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateXLS {

    private static final String SELECT_EMORDER_SQL = "select t.id,t.status_id, " +
            " t.type_id, " +
            " tt.dept_code, " +
            " tt.dept_name, " +
            " tt.application_date, " +
            " tt.principal_account, " +
            " tt.principal_name, " +
            " tt.reason,t.downsteam_order_id, " +
            " t.notes from em_order t,ee_order_assetentityalter tt where t.id = tt.order_id and t.spec_id = 3010100010" +
            " and t.upsteam_order_id = ? ";

    private static final String SELECT_ORDERITEM_ASSET_SQL = "select t.id,t.parent_orderitem_id,tt.move_out_dept,tt.move_in_dept,tt.bukrs,tt.assets_card_code," +
            "tt.is_idle,ttt.inactivedate,ttt.status,ttt.modify_time from em_orderitem t,ee_orderitem_asset tt,ee_orderitem_strongasset ttt where t.id = tt.orderitem_id and t.id = ttt.orderitem_id " +
            " and t.spec_id = 3020100009 and t.order_id = ? ";

    @Test
    public  void  Test1(){
        System.out.println(""+SELECT_ORDERITEM_ASSET_SQL);
    }


}
