package com.example.demo_sanm.testyun;

import com.chinatelecom.grp.support.bo.busiety.*;
import com.example.demo_sanm.yunexcel.yunfileutil.SysBasicConstant;
import com.example.demo_sanm.yunexcel.yunfileutil.SysBasicConstantSVC;
import com.google.common.cache.Cache;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.Bean2ExcelRowMapper;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.Excel4Java;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.impl.DefaultExcel4JavaRowMapper;
import com.sinovate.ngrms.commplt.toolkits.excel4j.binding.impl.Excel4JavaImpl;
import com.sinovate.ngrms.commplt.toolkits.excel4j.usermodel.ExcelBook;
import com.sinovate.ngrms.gcbscsvr.das.udao.SpecEntityMapper;
import com.sinovate.ngrms.gcbscsvr.mdl.db.gcdm.stauth.DmStaff;
import com.sinovate.ngrms.gcbscsvr.mdl.db.gmdata.*;
import com.sinovate.ngrms.gcbscsvr.ps.support.cache.GmDataCacheService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/30.
 * @by: DELL)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GmDataExcelServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GmDataExcelServiceTest.class);



    @Autowired
    private SysBasicConstantSVC sysBasicConstantSVC;


    public static final String EXCELTEMPFILENAME = "exportAssetDetailTemp.xls";
    private static final String ASSETFILE_PRM = "assetDetailTemp_prm.xls";
    private static final String ASSETFILE_BAK = "assetDetailTemp_bak.xls";

    public static final String SYS_PFILE_ROOT = "/CODEMIX_APP_DICT";
    public static final String SYS_PFILE_MEMDOCS = "MEMDOCS";
    public static final String SYS_PFILE_SOAPDOCS = "SOAPDOCS";
    public static final String SYS_PFILE_BUSIDOCS = "BUSIDOCS";
    public static final String SYS_PFILE_OTHDOCS = "OTHDOCS";

    @Test
    public  void Test2() throws Exception {
        copyTplFile();
        changeExcTmpFile(CHETYPE_ALL, CHESTS_INIT);

    }






    @Test
    public  void copyTplFile() throws Exception {
        sysBasicConstantSVC.preInitialize();//C:\maven_202002\work_sm\demo_sanm\target\CODEMIX_APP_DICT\BUSIDOCS


        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        System.out.println("path"+path);
        String sysFilePath = URLDecoder.decode(path, "UTF-8");
        String excelFilePath = sysFilePath.substring(0, sysFilePath.lastIndexOf("/"));
        System.out.println("excelFilePath"+excelFilePath);
        if("\\".equals(File.separator)) {
            excelFilePath = excelFilePath.substring(0, excelFilePath.lastIndexOf("/"));
        }
        excelFilePath = excelFilePath.substring(0, excelFilePath.lastIndexOf("/")) + "/" + EXCELTEMPFILENAME;
        System.out.println("==="+excelFilePath);
        File origFile = new File(excelFilePath);
        if(origFile.exists()) {
            System.out.println("文件存在");
            String oldAssetTplfName = SysBasicConstant.getSysPubFileDoc(SysBasicConstant.SYS_PFILE_BUSIDOCS).getAbsolutePath() +
                    File.separator + EXCELTEMPFILENAME;
            File oldAssetFile = new File(oldAssetTplfName);
            if(!oldAssetFile.exists()) {
                FileUtils.copyFile(origFile, oldAssetFile);
            }
        }else {
            System.out.println("文件不存在");

        }

        }


    private static final String CHETYPE_ALL = "ALL";
    private static final String CHETYPE_ORIG = "ORIG";
    private static final String CHETYPE_BAK = "BAK";

    private static final int CHESTS_INIT = 0;
    private static final int CHESTS_ORIG = 1;
    private static final int CHESTS_BACK = 2;
    /**
     * 变更模板文件
     */
    protected void changeExcTmpFile(String type, int status) {

        String oldAssetTplfName = SysBasicConstant.getSysPubFileDoc(com.sinovate.ngrms.gcbscsvr.ps.support.SysBasicConstant.SYS_PFILE_BUSIDOCS) +
                File.separator + EXCELTEMPFILENAME;
        String prmAssetTplfName = SysBasicConstant.getSysPubFileDoc(com.sinovate.ngrms.gcbscsvr.ps.support.SysBasicConstant.SYS_PFILE_BUSIDOCS) +
                File.separator + ASSETFILE_PRM;
        String bakAssetTplfName = SysBasicConstant.getSysPubFileDoc(com.sinovate.ngrms.gcbscsvr.ps.support.SysBasicConstant.SYS_PFILE_BUSIDOCS) +
                File.separator + ASSETFILE_BAK;
        //@yun


        File oldAssetFile = new File(oldAssetTplfName);
        if(!oldAssetFile.exists()) {
            System.out.println("[字典数据缓存-excel组件]系统初始化时，文件拷贝有问题，请检查确认！！！");
            return;
        }
        File prmAssetFile = new File(prmAssetTplfName);
        File bakAssetFile = new File(bakAssetTplfName);

        try {
            if(null!=type&&!"".equals(type)) {
                if(type.contentEquals(CHETYPE_ALL) && status==CHESTS_INIT) {
                    Excel4Java prme4j = new Excel4JavaImpl();
                    boolean isCreated1 =  prme4j.createExcelBook2(oldAssetFile);
                    //changed by wang.jie 16/12/14 修复模板加载bug
                    if(isCreated1){
                        //数据初始化主-备
                        if(!prmAssetFile.exists())
                        {
                            prmAssetFile.createNewFile();
                        }
                        prme4j = writeDict(prme4j, GmDataCacheService.getPrmGmdataCache());
                        prme4j.write2File(prmAssetTplfName);
                    }
                    prme4j = null;
                    LOGGER.info("[字典数据缓存-excel组件]主模板字典数据更新成功！！！");

                    if(!bakAssetFile.exists())
                    {
                        bakAssetFile.createNewFile();
                        FileUtils.copyFile(prmAssetFile, bakAssetFile);
                        FileUtils.copyFile(prmAssetFile, oldAssetFile);
                    }
                    LOGGER.info("[字典数据缓存-excel组件]备模板字典数据更新成功！！！");
                } else if (type.contentEquals(CHETYPE_ORIG) && status==CHESTS_ORIG){
                    Excel4Java prme4j = new Excel4JavaImpl();
                    boolean isCreated2 = prme4j.createExcelBook2(oldAssetFile);
                    //changed by wang.jie 16/12/14 修复模板加载bug
                    if(isCreated2){
                        //主数据变更
                        if(!prmAssetFile.exists())
                        {
                            prmAssetFile.createNewFile();
                        } else {
                            prmAssetFile.delete();
                            prmAssetFile = new File(prmAssetTplfName);
                            prmAssetFile.createNewFile();
                        }
                        prme4j = writeDict(prme4j,GmDataCacheService.getPrmGmdataCache());
                        prme4j.write2File(prmAssetTplfName);
                    }
                    prme4j = null;
                    FileUtils.copyFile(prmAssetFile, oldAssetFile);
                    LOGGER.info("[字典数据缓存-excel组件]主模板字典数据更新成功！！！");
                } else if (type.contentEquals(CHETYPE_BAK) && status==CHESTS_BACK) {
                    Excel4Java bake4j = new Excel4JavaImpl();
                    boolean isCreated3 = bake4j.createExcelBook2(oldAssetFile);
                    if(isCreated3){
                        //备数据变更
                        if(!bakAssetFile.exists())
                        {
                            bakAssetFile.createNewFile();
                        } else {
                            bakAssetFile.delete();
                            bakAssetFile = new File(bakAssetTplfName);
                            bakAssetFile.createNewFile();
                        }
                        bake4j = writeDict(bake4j,GmDataCacheService.getBakGmdataCache());
                        bake4j.write2File(bakAssetTplfName);
                    }
                    bake4j = null;
                    FileUtils.copyFile(bakAssetFile, oldAssetFile);
                    LOGGER.info("[字典数据缓存-excel组件]备模板字典数据更新成功！！！");
                } else {
                    LOGGER.warn("业务参数有误，请检查确认！！！");
                }
            } else {
                LOGGER.warn("参数传入有误，请确认！！！！");
            }
        } catch (IOException e) {
            LOGGER.error(e.toString());
        } catch (ExecutionException e) {
            LOGGER.error(e.toString());
        } finally {
            oldAssetFile = null;
            prmAssetFile = null;
            bakAssetFile = null;
        }
    }

    private static final int HIDDEN = ExcelBook.SHEET_STATE_HIDDEN;

   //pri
    private   List<DeOmVocassetzj>  getdeOmVocassetzjList(){
        List<DeOmVocassetzj> deOmVocassetzjList = new ArrayList<DeOmVocassetzj>();
        DeOmVocassetzj  zj = new DeOmVocassetzj();
        zj.setDfcode("code");
        zj.setDfname("胡云辉");
        zj.setMdataId(new BigInteger("111"));
        zj.setProvinceName("祁门县");

        deOmVocassetzjList.add(zj);

        return  deOmVocassetzjList;

    }


    private Excel4Java writeDict(Excel4Java e4j,Cache<String,List<? extends Object>> cache) throws ExecutionException {

//        List<DeOmVosunit> deOmVosunitList = null;
//        List<DeOmVocepct> deOmVocepctList = null;
        Cache<String, List<? extends Object>> map = GmDataCacheService.getAvaliableCache();
        List<DeOmVosareacode> deOmVosareacodeList = new ArrayList<DeOmVosareacode>();
        List<AreaCode> areaCodeList = new ArrayList<AreaCode>();
        List<DeOmVocassetqybm> deOmVocassetqybmList = new ArrayList<DeOmVocassetqybm>();
        List<QyCode> qyCodeList = new ArrayList<QyCode>();
        List<DeOmVocsapcostcenter> deOmVocsapcostcenterList = new ArrayList<DeOmVocsapcostcenter>();
        List<CostCenter> costCenterList = new ArrayList<CostCenter>();
        List<DeOmVocepct> deOmVocepctList = new ArrayList<DeOmVocepct>();
        List<PrctrCode> prctrCodeList = new ArrayList<PrctrCode>();

        List<DeOmVocassetgldept> deOmVocassetgldeptList = new ArrayList<DeOmVocassetgldept>();
        List<GlDept> glDeptList = new ArrayList<GlDept>();

        Set<StaffExcelBo> staffExcelBoSet = new TreeSet<StaffExcelBo>();
        List<StaffExcelBo> staffExcelBoList = new ArrayList<StaffExcelBo>();

        List<DeOmVocassetsydept> deOmVocassetsydeptList = new ArrayList<DeOmVocassetsydept>();
        List<SyDept> syDeptList = new ArrayList<SyDept>();

        Set<StaffExcelBo> staffExcelBoSet1 = new TreeSet<StaffExcelBo>();
        List<StaffExcelBo> staffExcelBoList1 = new ArrayList<StaffExcelBo>();
        List<StaffExcelBo> staffExcelBoList2 = new ArrayList<StaffExcelBo>();

        List<DeOmVocassetqxgs> deOmVocassetqxgsList = new ArrayList<DeOmVocassetqxgs>();
        List<DeOmVocassetzj> deOmVocassetzjList = new ArrayList<DeOmVocassetzj>();
        deOmVocassetzjList=  getdeOmVocassetzjList();

        List<DeOmVocassetyyt> deOmVocassetyytList = new ArrayList<DeOmVocassetyyt>();
//        List<MmAssetSpec> mmAssetSpecList = null;
//        List<DeOmVoankt> deOmVoanktList = null;
//        List<DeOmVocjobcostclass> deOmVocjobcostclassList = null;

        //获取字典主数据缓存
//        if (null != prmGmdataCache.getIfPresent("deOmVosunitList")) {
//            deOmVosunitList = (List<DeOmVosunit>) prmGmdataCache.getIfPresent("deOmVosunitList");
//        }
//        if (null != prmGmdataCache.getIfPresent("deOmVocepctList")) {
//            deOmVocepctList = (List<DeOmVocepct>) prmGmdataCache.getIfPresent("deOmVocepctList");
//        }
//        if (null != prmGmdataCache.getIfPresent("deOmVosareacodeList")) {
//            deOmVosareacodeList = (List<DeOmVosareacode>) prmGmdataCache.getIfPresent("deOmVosareacodeList");
//        }
//        if (null != prmGmdataCache.getIfPresent("deOmVocsapcostcenterList")) {
//            deOmVocsapcostcenterList = (List<DeOmVocsapcostcenter>) prmGmdataCache.getIfPresent("deOmVocsapcostcenterList");
//        }
//        if (null != prmGmdataCache.getIfPresent("deOmVocassetgldeptList")) {
//            deOmVocassetgldeptList = (List<DeOmVocassetgldept>) prmGmdataCache.getIfPresent("deOmVocassetgldeptList");
//        }

        /**
         * 交资excle主数据下拉排序
         * 从主数据取值改为查询数据库排序
         * modified by ouyang on 2017/7/1.
         */
//
//        //使用单位
//        String deOmVosareacodeSql = "SELECT t.MDATA_ID as mdataId,t.AREACODE as areacode,t.AREA as area,t.SHORTEN as shorten,t.FULLNAME as fullname,t.AREALEVEL as arealevel,t.AREANO as areano,t.CAREALEVEL as carealevel,t.PROVINCECODE as provincecode,t.DISPSORT as dispsort,t.ORGANIZEID as organizeid,t.AREADNO as areadno,t.PROVINCENUM as provincenum,t.OU as ou,t.LASTUPDATETIME as lastupdatetime,t.YWCBAREALEVEL as ywcbarealevel " +
//                " FROM DE_OM_V_O_SAREACODE t JOIN DM_OMDATA t2 on t.MDATA_ID = t2.ID  where t2.IS_VALID = 100383 order by NLSSORT(t.AREA,'NLS_SORT =SCHINESE_PINYIN_M')";
//        deOmVosareacodeList = jdbcTemplate.query(deOmVosareacodeSql,new SpecEntityMapper(DeOmVosareacode.class));
//        //deOmVosareacodeList = (List<DeOmVosareacode>) map.getIfPresent("deOmVosareacodeList");
//        if (CollectionUtils.isNotEmpty(deOmVosareacodeList)) {
//            for (DeOmVosareacode deOmVosareacode : deOmVosareacodeList) {
//                AreaCode areaCode = new AreaCode();
//                areaCode.setAreaNameCode(deOmVosareacode.getArea() + "(" + deOmVosareacode.getOu() + ")");
//                areaCodeList.add(areaCode);
//            }
//        }
//        //区域编码
//        String deOmVocassetqybmSql = "SELECT t.MDATA_ID as mdataId,t.DFCODE as dfcode, t.DFNAME as dfname,t.PROVINCE_NAME as provinceName " +
//                " FROM DE_OM_V_O_CASSETQYBM t JOIN DM_OMDATA t2 on t.MDATA_ID = t2.ID  where t2.IS_VALID = 100383 order by NLSSORT(t.DFNAME,'NLS_SORT =SCHINESE_PINYIN_M')";
//        deOmVocassetqybmList = jdbcTemplate.query(deOmVocassetqybmSql,new SpecEntityMapper(DeOmVocassetqybm.class));
//        // deOmVocassetqybmList =(List<DeOmVocassetqybm>)map.getIfPresent("deOmVocassetqybmList");
//        if (CollectionUtils.isNotEmpty(deOmVocassetqybmList)) {
//            for (DeOmVocassetqybm deOmVocassetqybm : deOmVocassetqybmList) {
//                QyCode qybm = new QyCode();
//                qybm.setAreaNameCode(deOmVocassetqybm.getDfname() + "(" + deOmVocassetqybm.getDfcode() + ")");
//                qyCodeList.add(qybm);
//            }
//        }
//        //利润中心
//        String deOmVocepctSql ="SELECT t.MDATA_ID as mdataId, t.MANDT as mandt, t.SPRAS as spras, t.PRCTR as prctr, t.DATBI as datbi, t.KOKRS as kokrs, t.KTEXT as ktext, t.LTEXT as ltext, t.MCTXT as mctxt " +
//                " FROM DE_OM_V_O_CEPCT t JOIN DM_OMDATA t2 on t.MDATA_ID = t2.ID where t2.IS_VALID = 100383 order by NLSSORT(t.KTEXT,'NLS_SORT =SCHINESE_PINYIN_M')";
//        deOmVocepctList = jdbcTemplate.query(deOmVocepctSql,new SpecEntityMapper(DeOmVocepct.class));
//        // deOmVocepctList = (List<DeOmVocepct>)map.getIfPresent("deOmVocepctList");
//        if (CollectionUtils.isNotEmpty(deOmVocepctList)) {
//            for (DeOmVocepct deOmVocepct : deOmVocepctList) {
//                PrctrCode prctrCode = new PrctrCode();
//                String ktext = deOmVocepct.getKtext();
//                String prctr = deOmVocepct.getPrctr();
//                prctrCode.setPrctrNameCode(ktext + "(" + prctr + ")");
//                prctrCodeList.add(prctrCode);
//            }
//        }
//        //成本中心
//        String  deOmVocsapcostcenterSql ="SELECT t.MDATA_ID as mdataId, t.COSTCENTER as costcenter, t.COSTCENTERREMARK as costcenterremark, t.PROVINCE_NAME as provinceName, t.PRCTR as prctr, t.BUKRS as bukrs " +
//                "   FROM DE_OM_V_O_CSAPCOSTCENTER t JOIN DM_OMDATA t2 on t.MDATA_ID = t2.ID where t2.IS_VALID = 100383 order by NLSSORT(t.COSTCENTERREMARK,'NLS_SORT =SCHINESE_PINYIN_M')";
//        deOmVocsapcostcenterList = jdbcTemplate.query(deOmVocsapcostcenterSql,new SpecEntityMapper(DeOmVocsapcostcenter.class));
//        // deOmVocsapcostcenterList = (List<DeOmVocsapcostcenter>)map.getIfPresent("deOmVocsapcostcenterList");
//        if (CollectionUtils.isNotEmpty(deOmVocsapcostcenterList)) {
//            for (DeOmVocsapcostcenter deOmVocsapcostcenter : deOmVocsapcostcenterList) {
//                CostCenter costCenter = new CostCenter();
//                costCenter.setCostCenterNameCode(deOmVocsapcostcenter.getCostcenterremark()
//                        + "(" + deOmVocsapcostcenter.getCostcenter() + ")");
//                costCenterList.add(costCenter);
//            }
//        }
//        //资产管理部门
//        String deOmVocassetgldeptSql = "SELECT t.MDATA_ID as mdataId,t.GLDEPTCODE as gldeptcode,t.GLDEPTNAME as gldeptname,t.PROVINCE_NAME as provinceName,t.PRCTR as prctr,t.BUKRS as bukrs,t.PTEXT as ptext " +
//                " FROM DE_OM_V_O_CASSETGLDEPT t JOIN DM_OMDATA t2 on t.MDATA_ID = t2.ID where t2.IS_VALID = 100383 order by NLSSORT(t.GLDEPTNAME,'NLS_SORT =SCHINESE_PINYIN_M')";
//        deOmVocassetgldeptList = jdbcTemplate.query(deOmVocassetgldeptSql,new SpecEntityMapper(DeOmVocassetgldept.class));
//        //deOmVocassetgldeptList =(List<DeOmVocassetgldept>)map.getIfPresent("deOmVocassetgldeptList");
//        if (CollectionUtils.isNotEmpty(deOmVocassetgldeptList)) {
//            for (DeOmVocassetgldept deOmVocassetgldept : deOmVocassetgldeptList) {
//                GlDept glDept = new GlDept();
//                glDept.setGlDeptNameCode(deOmVocassetgldept.getGldeptname()
//                        + "(" + deOmVocassetgldept.getGldeptcode() + ")");
//                glDeptList.add(glDept);
//            }
//        }
//        //使用人
//        String dmStaffSql = "select ID as id,NAME as name,ACCOUNT as account from DM_STAFF WHERE USER_STATUS_ID != 2 order by NLSSORT(NAME,'NLS_SORT =SCHINESE_PINYIN_M')";
//        List<DmStaff> dmStaffList = jdbcTemplate.query(dmStaffSql,new SpecEntityMapper(DmStaff.class));
//        // List<DmStaff> dmStaffList = (List<DmStaff>)map.getIfPresent("dmstaffList");
//        if (null != dmStaffList && dmStaffList.size() > 0) {
//            for (DmStaff dmStaff : dmStaffList) {
//                StaffExcelBo staffExcelBo = new StaffExcelBo();
//                staffExcelBo.setNameAccount(dmStaff.getName() + "(" + dmStaff.getAccount() + ")");
//                staffExcelBoList2.add(staffExcelBo);
//            }
//        }
//        //资产管理员
//        String deOmVocassetgluserSql = "select ID as id,NAME as name, ACCOUNT as account from DM_STAFF WHERE USER_STATUS_ID != 2 and ACCOUNT in(select distinct t.GLUSERID as gluserid " +
//                " FROM DE_OM_V_O_CASSETGLUSER t JOIN DM_OMDATA t2 on t.MDATA_ID = t2.ID where t2.IS_VALID = 100383) order by NLSSORT(NAME,'NLS_SORT =SCHINESE_PINYIN_M')";
//        List<DmStaff> deOmVocassetgluserList =jdbcTemplate.query(deOmVocassetgluserSql,new SpecEntityMapper(DmStaff.class));
//        //List<DeOmVocassetgluser> deOmVocassetgluserList = (List<DeOmVocassetgluser>)map.getIfPresent("deOmVocassetgluserList");
//        if (CollectionUtils.isNotEmpty(deOmVocassetgluserList)&&CollectionUtils.isNotEmpty(dmStaffList)) {
//            for (DmStaff dmStaff : deOmVocassetgluserList) {
//                StaffExcelBo staffExcelBo = new StaffExcelBo();
//                staffExcelBo.setNameAccount(dmStaff.getName() + "(" + dmStaff.getAccount() + ")");
//                staffExcelBoList.add(staffExcelBo);
//
//            }
//
//            // staffExcelBoList = new ArrayList<StaffExcelBo>(staffExcelBoSet);
//              /*  DmStaff dmStaff = new DmStaff();
//                dmStaff.setAccount(deOmVocassetgluser.getGluserid());
//                List<DmStaff> dmStaffList = iUniSpecDao.findByParamObj(dmStaff, "ENTERPRISESTAFF");
//                StaffExcelBo staffExcelBo = new StaffExcelBo();
////            staffExcelBo.setName(dmStaff.getName());
////            staffExcelBo.setAccount(dmStaff.getAccount());
//                if (null != dmStaffList && dmStaffList.size() > 0) {
//                    staffExcelBo.setNameAccount(dmStaffList.get(0).getName() + "(" + dmStaffList.get(0).getAccount() + ")");
//                    staffExcelBoSet.add(staffExcelBo);
//                }
//                staffExcelBoList = new ArrayList<StaffExcelBo>(staffExcelBoSet);*/
//
//        }
//        //资产使用部门
////        if (null != prmGmdataCache.getIfPresent("deOmVocassetsydeptList")) {
////            deOmVocassetsydeptList = (List<DeOmVocassetsydept>) prmGmdataCache.getIfPresent("deOmVocassetsydeptList");
////        }
//        //保管员
//        String deOmVocassetsyusersSql = "select ID as id,NAME as name,ACCOUNT as account from DM_STAFF WHERE USER_STATUS_ID != 2 and ACCOUNT in(select distinct t.SYUSERID as gluserid " +
//                " FROM DE_OM_V_O_CASSETSYUSER t JOIN DM_OMDATA t2 on t.MDATA_ID = t2.ID where t2.IS_VALID = 100383) order by NLSSORT(NAME,'NLS_SORT =SCHINESE_PINYIN_M')";
//        List<DmStaff> deOmVocassetsyusers =jdbcTemplate.query(deOmVocassetsyusersSql,new SpecEntityMapper(DmStaff.class));
//        //List<DeOmVocassetsyuser> deOmVocassetsyusers = (List<DeOmVocassetsyuser>)map.getIfPresent("deOmVocassetsyuserList");
//        if (CollectionUtils.isNotEmpty(deOmVocassetsyusers)&&CollectionUtils.isNotEmpty(dmStaffList)) {
//            for(DmStaff dmStaff:deOmVocassetsyusers){
//                StaffExcelBo staffExcelBo = new StaffExcelBo();
//                staffExcelBo.setNameAccount(dmStaff.getName() + "(" + dmStaff.getAccount() + ")");
//                staffExcelBoList1.add(staffExcelBo);
//
//            }
//            // staffExcelBoList1 = new ArrayList<StaffExcelBo>(staffExcelBoSet1);
//                /*DmStaff dmStaff = new DmStaff();
//                dmStaff.setAccount(deOmVocassetsyuser.getSyuserid());
//                List<DmStaff> dmStaffList = iUniSpecDao.findByParamObj(dmStaff, "ENTERPRISESTAFF");
//                StaffExcelBo staffExcelBo = new StaffExcelBo();
//                if (null != dmStaffList && dmStaffList.size() > 0) {
//                    staffExcelBo.setNameAccount(dmStaffList.get(0).getName() + "(" + dmStaffList.get(0).getAccount() + ")");
//                    staffExcelBoSet1.add(staffExcelBo);
//                }
//                staffExcelBoList1 = new ArrayList<StaffExcelBo>(staffExcelBoSet1);*/
//
//        }
//        String deOmVocassetsydeptSql ="SELECT t.MDATA_ID as mdataId, t.SYDEPTCODE as sydeptcode, t.SYDEPTNAME as sydeptname, t.PROVINCE_NAME as provinceName, t.PRCTR as prctr, t.PTEXT as ptext, t.BUKRS as bukrs " +
//                " FROM DE_OM_V_O_CASSETSYDEPT t JOIN DM_OMDATA t2 on t.MDATA_ID = t2.ID where t2.IS_VALID = 100383 order by NLSSORT(t.SYDEPTNAME,'NLS_SORT =SCHINESE_PINYIN_M')";
//        deOmVocassetsydeptList =jdbcTemplate.query(deOmVocassetsydeptSql,new SpecEntityMapper(DeOmVocassetsydept.class));
//        //deOmVocassetsydeptList = (List<DeOmVocassetsydept>)map.getIfPresent("deOmVocassetsydeptList");
//        if (CollectionUtils.isNotEmpty(deOmVocassetsydeptList)) {
//            for (DeOmVocassetsydept deOmVocassetsydept : deOmVocassetsydeptList) {
//                SyDept syDept = new SyDept();
//                syDept.setSyDeptNameCode(deOmVocassetsydept.getSydeptname()
//                        + "(" + deOmVocassetsydept.getSydeptcode() + ")");
//                syDeptList.add(syDept);
//            }
//        }
//        if (null !=map.getIfPresent("deOmVocassetqxgsList")) {
//            String deOmVocassetqxgsSql ="SELECT t.MDATA_ID as mdataId,t.DFCODE as dfcode,t.DFNAME as dfname,t.PROVINCE_NAME as provinceName " +
//                    " FROM DE_OM_V_O_CASSETQXGS t JOIN DM_OMDATA t2 on t.MDATA_ID = t2.ID where t2.IS_VALID = 100383 order by NLSSORT(t.DFNAME,'NLS_SORT =SCHINESE_PINYIN_M')";
//            deOmVocassetqxgsList =jdbcTemplate.query(deOmVocassetqxgsSql,new SpecEntityMapper(DeOmVocassetqxgs.class));
//            // deOmVocassetqxgsList = (List<DeOmVocassetqxgs>) map.getIfPresent("deOmVocassetqxgsList");
//        }
//        if (null != map.getIfPresent("deOmVocassetzjList")) {
//            String deOmVocassetzjSql ="SELECT t.MDATA_ID as mdataId,t.DFCODE as dfcode,t.DFNAME as dfname,t.PROVINCE_NAME as provinceName " +
//                    " FROM DE_OM_V_O_CASSETZJ t JOIN DM_OMDATA t2 on t.MDATA_ID = t2.ID where t2.IS_VALID = 100383 order by NLSSORT(t.DFNAME,'NLS_SORT =SCHINESE_PINYIN_M')";
//            deOmVocassetzjList =jdbcTemplate.query(deOmVocassetzjSql,new SpecEntityMapper(DeOmVocassetzj.class));
//            //deOmVocassetzjList = (List<DeOmVocassetzj>) map.getIfPresent("deOmVocassetzjList");
//        }
//        if (null != map.getIfPresent("deOmVocassetyytList")) {
//            String deOmVocassetyytSql ="SELECT t.MDATA_ID as mdataId,t.DFCODE as dfcode,t.DFNAME as dfname,t.PROVINCE_NAME as provinceName " +
//                    " FROM DE_OM_V_O_CASSETYYT t JOIN DM_OMDATA t2 on t.MDATA_ID = t2.ID where t2.IS_VALID = 100383 order by NLSSORT(t.DFNAME,'NLS_SORT =SCHINESE_PINYIN_M')";
//            deOmVocassetyytList =jdbcTemplate.query(deOmVocassetyytSql,new SpecEntityMapper(DeOmVocassetyyt.class));
//            //deOmVocassetyytList = (List<DeOmVocassetyyt>) map.getIfPresent("deOmVocassetyytList");
//        }
//
////        mmAssetSpecList = mmAssetSpecMapper.getAll();
////
////        if (null != prmGmdataCache.getIfPresent("deOmVoanktList")) {
////            deOmVoanktList = (List<DeOmVoankt>) prmGmdataCache.getIfPresent("deOmVoanktList");
////        }
////        if (null != prmGmdataCache.getIfPresent("deOmVocjobcostclassList")) {
////            deOmVocjobcostclassList = (List<DeOmVocjobcostclass>) prmGmdataCache.getIfPresent("deOmVocjobcostclassList");
////        }

        //建立映射
//        Bean2ExcelRowMapper<DeOmVosunit> deOmVosunitmapper = new DefaultExcel4JavaRowMapper<DeOmVosunit>();
//        Bean2ExcelRowMapper<DeOmVocepct> deOmVocepctmapper = new DefaultExcel4JavaRowMapper<DeOmVocepct>();
//        Bean2ExcelRowMapper<DeOmVosareacode> deOmVosareacodemapper = new DefaultExcel4JavaRowMapper<DeOmVosareacode>();
        Bean2ExcelRowMapper<AreaCode> areacodemapper = new DefaultExcel4JavaRowMapper<AreaCode>();
        Bean2ExcelRowMapper<QyCode> qyCodemapper = new DefaultExcel4JavaRowMapper<QyCode>();
//        Bean2ExcelRowMapper<DeOmVocsapcostcenter> deOmVocsapcostcentermapper = new DefaultExcel4JavaRowMapper<DeOmVocsapcostcenter>();
        Bean2ExcelRowMapper<CostCenter> costcentermapper = new DefaultExcel4JavaRowMapper<CostCenter>();
        Bean2ExcelRowMapper<PrctrCode> prctrcodemapper = new DefaultExcel4JavaRowMapper<PrctrCode>();
//        Bean2ExcelRowMapper<DeOmVocassetgldept> deOmVocassetgldeptmapper = new DefaultExcel4JavaRowMapper<DeOmVocassetgldept>();
        Bean2ExcelRowMapper<GlDept> gldeptmapper = new DefaultExcel4JavaRowMapper<GlDept>();
        Bean2ExcelRowMapper<StaffExcelBo> staffExcelmapper = new DefaultExcel4JavaRowMapper<StaffExcelBo>();

//        Bean2ExcelRowMapper<DeOmVocassetsydept> deOmVocassetsydeptmapper = new DefaultExcel4JavaRowMapper<DeOmVocassetsydept>();
        Bean2ExcelRowMapper<SyDept> sydeptmapper = new DefaultExcel4JavaRowMapper<SyDept>();
        Bean2ExcelRowMapper<StaffExcelBo> deOmVocassetglusermapper = new DefaultExcel4JavaRowMapper<StaffExcelBo>();
        Bean2ExcelRowMapper<StaffExcelBo> deOmVocassetusermanmapper = new DefaultExcel4JavaRowMapper<StaffExcelBo>();

        Bean2ExcelRowMapper<DeOmVocassetqxgs> deOmVocassetqxgsmapper = new DefaultExcel4JavaRowMapper<DeOmVocassetqxgs>();
        Bean2ExcelRowMapper<DeOmVocassetzj> deOmVocassetzjmapper = new DefaultExcel4JavaRowMapper<DeOmVocassetzj>();
        Bean2ExcelRowMapper<DeOmVocassetyyt> deOmVocassetyytmapper = new DefaultExcel4JavaRowMapper<DeOmVocassetyyt>();
//        Bean2ExcelRowMapper<MmAssetSpec> mmAssetSpecmapper = new DefaultExcel4JavaRowMapper<MmAssetSpec>();
//        Bean2ExcelRowMapper<DeOmVoankt> deOmVoanktmapper = new DefaultExcel4JavaRowMapper<DeOmVoankt>();
//        Bean2ExcelRowMapper<DeOmVocjobcostclass> deOmVocjobcostclassmapper = new DefaultExcel4JavaRowMapper<DeOmVocjobcostclass>();

        //设置标签页
//        String deOmVosunitSheetName = "计量单位";
//        String deOmVocepctSheetName = "利润中心";

        String deOmVosareacodeSheetName = "使用单位";
        String deOmVocsapcostcenterSheetName = "成本中心";
        String deOmVocepctSheetName = "利润中心";
        String deOmVocassetgldeptSheetName = "资产管理部门";
        String staffExcelSheetName = "资产管理员";

        String deOmVocassetgldeptName = "使用部门";
        String deOmVocassetgluserName = "保管员";
        String deOmVocassetusermanName = "使用人";

        String deOmVocassetqxgsSheetName = "区县分公司";
        String deOmVocassetzjSheetName = "支局";
        String deOmVocassetyytSheetName = "营业厅";
        String deOmVocassetqybmSheetName = "区域编码";
//        String mmAssetSpecSheetName = "固定资产目录";
//        String deOmVoanktSheetName = "资产分类";
//        String deOmVocjobcostclassSheetName = "作业成本资产类别";

        //写入sheet页
//        e4j.toSheet(deOmVosunitList, deOmVosunitSheetName, 1, deOmVosunitmapper);
//        e4j.toSheet(deOmVocepctList, deOmVocepctSheetName, 1, deOmVocepctmapper);
        e4j.toSheet(areaCodeList, deOmVosareacodeSheetName, 1, areacodemapper);
        e4j.toSheet(qyCodeList, deOmVocassetqybmSheetName, 1, qyCodemapper);
        //利润中心sheet页
        e4j.toSheet(prctrCodeList, deOmVocepctSheetName, 1, prctrcodemapper);
        e4j.toSheet(costCenterList, deOmVocsapcostcenterSheetName, 1, costcentermapper);
        e4j.toSheet(glDeptList, deOmVocassetgldeptSheetName, 1, gldeptmapper);
        e4j.toSheet(staffExcelBoList, staffExcelSheetName, 1, staffExcelmapper);

        e4j.toSheet(syDeptList, deOmVocassetgldeptName, 1, sydeptmapper);
        e4j.toSheet(staffExcelBoList1, deOmVocassetgluserName, 1, deOmVocassetglusermapper);
        e4j.toSheet(staffExcelBoList2, deOmVocassetusermanName, 1, deOmVocassetusermanmapper);

        e4j.toSheet(deOmVocassetqxgsList, deOmVocassetqxgsSheetName, 1, deOmVocassetqxgsmapper);
        e4j.toSheet(deOmVocassetzjList, deOmVocassetzjSheetName, 1, deOmVocassetzjmapper);
        e4j.toSheet(deOmVocassetyytList, deOmVocassetyytSheetName, 1, deOmVocassetyytmapper);
//        e4j.toSheet(mmAssetSpecList, mmAssetSpecSheetName, 1, mmAssetSpecmapper);
//        e4j.toSheet(deOmVoanktList, deOmVoanktSheetName, 1, deOmVoanktmapper);
//        e4j.toSheet(deOmVocjobcostclassList, deOmVocjobcostclassSheetName, 1, deOmVocjobcostclassmapper);

        //隐藏sheet页
//        e4j.setSheetHidden(deOmVosunitSheetName, HIDDEN);
//        e4j.setSheetHidden(deOmVocepctSheetName, HIDDEN);
        e4j.setSheetHidden(deOmVosareacodeSheetName, HIDDEN);
        e4j.setSheetHidden(deOmVocassetqybmSheetName, HIDDEN);
        e4j.setSheetHidden(deOmVocsapcostcenterSheetName, HIDDEN);
        e4j.setSheetHidden(deOmVocepctSheetName, HIDDEN);
        e4j.setSheetHidden(deOmVocassetgldeptSheetName, HIDDEN);
        e4j.setSheetHidden(staffExcelSheetName, HIDDEN);

        e4j.setSheetHidden(deOmVocassetgldeptName, HIDDEN);
        e4j.setSheetHidden(deOmVocassetgluserName, HIDDEN);
        e4j.setSheetHidden(deOmVocassetusermanName, HIDDEN);

        e4j.setSheetHidden(deOmVocassetqxgsSheetName, HIDDEN);
        e4j.setSheetHidden(deOmVocassetzjSheetName, HIDDEN);
        e4j.setSheetHidden(deOmVocassetyytSheetName, HIDDEN);
//        e4j.setSheetHidden(mmAssetSpecSheetName, HIDDEN);
//        e4j.setSheetHidden(deOmVoanktSheetName, HIDDEN);
//        e4j.setSheetHidden(deOmVocjobcostclassSheetName, HIDDEN);

        return e4j;
    }


}
