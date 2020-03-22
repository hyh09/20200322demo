package com.example.demo_sanm.contro;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/3.
 * @by: DELL)
 */
public class Results {

    //    bukrs	String	50	公司代码	必填
    private  String bukrs;

    //assetsCardCode	String	32	卡片编号	必填
    private  String assetsCardCode;

    //result	String	2	校验结果	必填  1：校验通过，0：校验不通过
    private String result;


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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Results{");
        sb.append("bukrs='").append(bukrs).append('\'');
        sb.append(", assetsCardCode='").append(assetsCardCode).append('\'');
        sb.append(", result='").append(result).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
