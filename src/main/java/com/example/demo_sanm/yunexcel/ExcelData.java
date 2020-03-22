package com.example.demo_sanm.yunexcel;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2019/3/4.
 * @by: DELL)
 * 用于Excel信息封装
 */
public class ExcelData implements Serializable {

    private static final long serialVersionUID = 4444017239100620999L;

     //
    private List<String> titles;

    /**
     *
     * 表名(必填)
     * Excel展示用
     */
    private String tableName;

    /**
     * 表头第二行(必填)
     */
    private  String[] fields;

    /**
     * 数据
     */
    private List<List<Object>> rows;

    /**
     * sheet 标签
     */
    private String name;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public List<List<Object>> getRows() {
        return rows;
    }

    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ExcelData{");
        sb.append("titles=").append(titles);
        sb.append(", tableName='").append(tableName).append('\'');
        sb.append(", fields=").append(fields == null ? "null" : Arrays.asList(fields).toString());
        sb.append(", rows=").append(rows);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}