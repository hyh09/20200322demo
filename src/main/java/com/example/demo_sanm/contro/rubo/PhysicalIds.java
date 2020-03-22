package com.example.demo_sanm.contro.rubo;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/3.
 * @by: DELL)
 */
public class PhysicalIds {

    private  String physicalId;

    public PhysicalIds() {
    }

    public PhysicalIds(String physicalId) {
        this.physicalId = physicalId;
    }

    public String getPhysicalId() {
        return physicalId;
    }

    public void setPhysicalId(String physicalId) {
        this.physicalId = physicalId;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PhysicalIds{");
        sb.append("physicalId='").append(physicalId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
