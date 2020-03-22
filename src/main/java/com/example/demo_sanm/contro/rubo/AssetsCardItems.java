package com.example.demo_sanm.contro.rubo;

import java.util.List;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/3.
 * @by: DELL)
 */
public class AssetsCardItems {

   // bukrs	String	50	公司代码	必填
  //  assetsCardCode	String	32	卡片编号	必填
  //  physicalIds	List		实物ID列表	必填

  private  String bukrs;

  private  String assetsCardCode;

  private List<PhysicalIds> physicalIds;


    public String getBukrs() {
        return bukrs;
    }

    public void setBukrs(String bukrs) {
        this.bukrs = bukrs;
    }

    public String getAssetsCardCode() {
        return assetsCardCode;
    }

    public void setAssetsCardCode(String assetsCardCode) {
        this.assetsCardCode = assetsCardCode;
    }

    public List <PhysicalIds> getPhysicalIds() {
        return physicalIds;
    }

    public void setPhysicalIds(List <PhysicalIds> physicalIds) {
        this.physicalIds = physicalIds;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AssetsCardItems{");
        sb.append("bukrs='").append(bukrs).append('\'');
        sb.append(", assetsCardCode='").append(assetsCardCode).append('\'');
        sb.append(", physicalIds=").append(physicalIds);
        sb.append('}');
        return sb.toString();
    }
}
