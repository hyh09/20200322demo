package com.example.demo_sanm.contro.excel;

import com.example.demo_sanm.interfacesvc.ColumnConfig;
import com.example.demo_sanm.interfacesvc.TableConFiG;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/22.
 * @by: DELL)
 *
 *
 */
@TableConFiG(value = "综合查询资源信息清单")
public class ErorerBo {


    public ErorerBo() {
    }

    public ErorerBo(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @ColumnConfig(descriptionyun="资源编码")
    private  String code;


    @ColumnConfig(descriptionyun = "资源名称")
    private  String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ErorerBo{");
        sb.append("code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
