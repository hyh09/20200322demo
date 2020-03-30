package com.example.demo_sanm.yunexcel.yunfileutil;

import com.sinovate.ngrms.comm.ptfcore.bsutils.jucext.ThreadsExtUtils;
import com.sinovate.ngrms.gcbscsvr.support.multids.ItspProvCfgConstants;
import com.sinovate.ngrms.gcbscsvr.support.utils.excelResult.PathExcelfileName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by pengzhiyuan on 2015/9/26.
 * 系统基础静态信息
 */
@Service
public class SysBasicConstant implements SysBasicConstantSVC{

    private static final Logger LOGGER = LoggerFactory.getLogger(com.sinovate.ngrms.gcbscsvr.ps.support.SysBasicConstant.class);

    public static final int CPUNUMS = Runtime.getRuntime().availableProcessors();

    //系统运行目录信息
    public static final String SYS_PFILE_ROOT = "/CODEMIX_APP_DICT";
    public static final String SYS_PFILE_MEMDOCS = "MEMDOCS";
    public static final String SYS_PFILE_SOAPDOCS = "SOAPDOCS";
    public static final String SYS_PFILE_BUSIDOCS = "BUSIDOCS";
    public static final String SYS_PFILE_OTHDOCS = "OTHDOCS";

    public static File sysPfileRoot;
    public static File sysPfileMemdocs;
    public static File sysPfileSoapdocs;
    public static File sysPfileBusidocs;
    public static File sysPfileOthdocs;

    //系统公共固定大小线程池
    public static ExecutorService sysCommFixedExecService;

    //系统定时器线程池
    public static ScheduledExecutorService sysCommSchdExecService;

    //初始化文件目录
    private String sysFilePath = "";
//    @Autowired
//    @Qualifier("fileOptSvcImpl")
//    private static FileOptSvc fileOptSvc;

    //@PostConstruct
    public  void preInitialize() throws UnsupportedEncodingException {
        //初始化时删除原来的模板文件和文件夹
        LOGGER.info("系统基础静态信息—文件目录删除开始");
        deleteExcelFile();

        LOGGER.info("系统基础静态信息—文件目录建立");
        URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        sysFilePath = URLDecoder.decode(url.getPath(), "UTF-8");
        sysFilePath = sysFilePath.substring(0, sysFilePath.lastIndexOf("/"));
        sysFilePath = sysFilePath.substring(0, sysFilePath.lastIndexOf("/"));
        LOGGER.info("当前目录： " + sysFilePath);
        if(null!=sysFilePath && !"".equals(sysFilePath)) {
            sysPfileRoot = new File(sysFilePath, SYS_PFILE_ROOT);
            if(!sysPfileRoot.exists()) {
                if(sysPfileRoot.mkdir()) {
                    LOGGER.info("成功创建运行时文件存储目录~");
                    //创建子文件夹
                    sysPfileBusidocs = new File(sysPfileRoot, SYS_PFILE_BUSIDOCS);
                    if(!sysPfileBusidocs.exists()) {
                        sysPfileBusidocs.mkdir();
                    }
                    sysPfileMemdocs = new File(sysPfileRoot, SYS_PFILE_MEMDOCS);
                    if(!sysPfileMemdocs.exists()) {
                        sysPfileMemdocs.mkdir();
                    }
                    sysPfileSoapdocs = new File(sysPfileRoot, SYS_PFILE_SOAPDOCS);
                    if(!sysPfileSoapdocs.exists()) {
                        sysPfileSoapdocs.mkdir();
                    }
                    sysPfileOthdocs = new File(sysPfileRoot, SYS_PFILE_OTHDOCS);
                    if(!sysPfileOthdocs.exists()) {
                        sysPfileOthdocs.mkdir();
                    }

                    //创建当天数据文件夹
                    Date currDate = new Date();
                    getSysPubFileDocByDate(SYS_PFILE_MEMDOCS, currDate);
                    getSysPubFileDocByDate(SYS_PFILE_BUSIDOCS, currDate);
                    getSysPubFileDocByDate(SYS_PFILE_SOAPDOCS, currDate);
                    getSysPubFileDocByDate(SYS_PFILE_OTHDOCS, currDate);
                } else {
                    LOGGER.error("无法创建根文件夹，请确认！！！");
                }
            } else {
                LOGGER.info("已找到文件存储根目录~");
                //创建子文件夹
                sysPfileBusidocs = new File(sysPfileRoot, SYS_PFILE_BUSIDOCS);
                if(!sysPfileBusidocs.exists()) {
                    sysPfileBusidocs.mkdir();
                }
                sysPfileMemdocs = new File(sysPfileRoot, SYS_PFILE_MEMDOCS);
                if(!sysPfileMemdocs.exists()) {
                    sysPfileMemdocs.mkdir();
                }
                sysPfileSoapdocs = new File(sysPfileRoot, SYS_PFILE_SOAPDOCS);
                if(!sysPfileSoapdocs.exists()) {
                    sysPfileSoapdocs.mkdir();
                }
                sysPfileOthdocs = new File(sysPfileRoot, SYS_PFILE_OTHDOCS);
                if(!sysPfileOthdocs.exists()) {
                    sysPfileOthdocs.mkdir();
                }

                //创建当天数据文件夹
                Date currDate = new Date();
                getSysPubFileDocByDate(SYS_PFILE_MEMDOCS, currDate);
                getSysPubFileDocByDate(SYS_PFILE_BUSIDOCS, currDate);
                getSysPubFileDocByDate(SYS_PFILE_SOAPDOCS, currDate);
                getSysPubFileDocByDate(SYS_PFILE_OTHDOCS, currDate);
            }
            //初始化线程池
            sysCommFixedExecService = ThreadsExtUtils.createFixedThdPools(2, "CDMIX-PUBFIXEDPOOLS-%d", true, "CDMIX-PUBFIXEDPOOLS");
            sysCommSchdExecService = ThreadsExtUtils.createScheduleThdPools(2, "CDMIX-PUBSCHDPOOLS-%d", true, "CDMIX-PUBSCHDPOOLS");
            //定时守护线程，向每个目录中添加每日格式的子文件，每天增量的文件分散到不同文件夹下
            sysCommSchdExecService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    LOGGER.info("定时线程，生成某一天文件目录结构。。。");
                    Date currDate = new Date();
                    getSysPubFileDocByDate(SYS_PFILE_MEMDOCS, currDate);
                    getSysPubFileDocByDate(SYS_PFILE_BUSIDOCS, currDate);
                    getSysPubFileDocByDate(SYS_PFILE_SOAPDOCS, currDate);
                    getSysPubFileDocByDate(SYS_PFILE_OTHDOCS, currDate);
                }
            }, 120, 180, TimeUnit.MINUTES);

        } else {
            LOGGER.error("系统初始化基础静态信息出现错误，很严重( ⊙ o ⊙ )");
            throw new RuntimeException("系统初始化基础静态信息出现错误，很严重( ⊙ o ⊙ )");
        }
    }

    //删除linux下装载Excel模板的目录
    public  void deleteExcelFile() throws UnsupportedEncodingException {
        LOGGER.info("开始删除目录");
        URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        sysFilePath = URLDecoder.decode(url.getPath(), "UTF-8");
        sysFilePath = sysFilePath.substring(0, sysFilePath.lastIndexOf("/"));
        sysFilePath = sysFilePath.substring(0, sysFilePath.lastIndexOf("/"));
        LOGGER.info("初始化删除的目录： " + sysFilePath + "/CODEMIX_APP_DICT");
        if(!"".equals(sysFilePath)) {
            File file = new File(sysFilePath, SYS_PFILE_ROOT);
            if (file.exists()) {
                deleteDir(file);
            }
        }
        String errorFilePath = ItspProvCfgConstants.getErrorFilePath() + File.separator + "errorfiles";

        errorFilePath =   PathExcelfileName.filenameFilter(errorFilePath);

        if(!"".equals(errorFilePath)) {
            File errFile = new File(errorFilePath);
            if (errFile.exists()) {
                deleteDir(errFile);
            }
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        //目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 查找指定目录中是否含有当天日期命名的文件夹存在
     * @param docPath
     * @param date
     * @return
     */
    public static File getSysPubFileDocByDate(String docPath, Date date) {
        File subFile = null;
        if(null!=docPath&&!"".equals(docPath)) {
            if(null == date)
                date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String dateName = dateFormat.format(date);
            LOGGER.info("日期信息为：" + dateName);
            if(docPath.contentEquals(SYS_PFILE_MEMDOCS)) {
                if(sysPfileMemdocs.exists()) {
                    subFile = new File(sysPfileMemdocs, dateName);
                    if(!subFile.exists()) {
                        subFile.mkdir();
                    }
                }
            } else if(docPath.contentEquals(SYS_PFILE_SOAPDOCS)) {
                if(sysPfileSoapdocs.exists()) {
                    subFile = new File(sysPfileSoapdocs, dateName);
                    if(!subFile.exists()) {
                        subFile.mkdir();
                    }
                }
            } else if(docPath.contentEquals(SYS_PFILE_BUSIDOCS)) {
                if(sysPfileBusidocs.exists()) {
                    subFile = new File(sysPfileBusidocs, dateName);
                    if(!subFile.exists()) {
                        subFile.mkdir();
                    }
                }
            } else if(docPath.contentEquals(SYS_PFILE_OTHDOCS)) {
                if(sysPfileOthdocs.exists()) {
                    subFile = new File(sysPfileOthdocs, dateName);
                    if(!subFile.exists()) {
                        subFile.mkdir();
                    }
                }
            }
        } else {
            LOGGER.error("入参信息有误，请确认！！！");
        }
        return subFile;
    }
    /**
     * 查找指定目录中是否含有当天日期命名的文件夹存在
     * @param docPath
     * @return
     */
    public static File getSysPubFileDoc(String docPath) {
        LOGGER.info("入参路径"+docPath);
        if(null != docPath && !"".equals(docPath)) {
            if(docPath.contentEquals(SYS_PFILE_MEMDOCS)) {
                if(sysPfileMemdocs.exists()) {
                    return sysPfileMemdocs;
                }
            } else if(docPath.contentEquals(SYS_PFILE_SOAPDOCS)) {
                if(sysPfileSoapdocs.exists()) {
                    return sysPfileSoapdocs;
                }
            } else if(docPath.contentEquals(SYS_PFILE_BUSIDOCS)) {
                if(sysPfileBusidocs.exists()) {
                    return sysPfileBusidocs;
                }
            } else if(docPath.contentEquals(SYS_PFILE_OTHDOCS)) {
                if(sysPfileOthdocs.exists()) {
                    return sysPfileOthdocs;
                }
            }
        } else {
            LOGGER.error("入参信息有误，请确认！！！");
        }
        return null;
    }

    public void destroy() {
        ThreadsExtUtils.gracefulShutdown(sysCommFixedExecService, 3, 3, TimeUnit.SECONDS);
        ThreadsExtUtils.gracefulShutdown(sysCommSchdExecService, 3, 3, TimeUnit.SECONDS);
        LOGGER.info("优雅关闭线程池【CDMIX-PUBFIXEDPOOLS-】【CDMIX-PUBSCHDPOOLS-】，防止内存泄露~~");
    }
}
