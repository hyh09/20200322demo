package com.example.demo_sanm.yunexcel;

import org.directwebremoting.io.FileTransfer;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/23.
 * @by: DELL)
 */
public interface ExportExcelyunSvc {

    public   <T> FileTransfer exportExcel(HttpServletResponse response, String fileName, List<T> voList , T beans) throws Exception;

    }
