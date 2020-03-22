package com.example.demo_sanm.yunexcel.yunenum;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2019/3/12.
 * @by: DELL)
 */
//资产归属翻译
public enum Assertord41Enums {
    Asset_o1("A001","一级干线"),
    Asset_o2("A002","二级干线"),
    Asset_o3("A003","本地网及其他"),;

    private String code;

    private String name;

    Assertord41Enums(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 取name
     */
    public static String getnamebycode(String code){
        for(Assertord41Enums assertord41Enums :Assertord41Enums.values()){
            if(assertord41Enums.getCode().equals(code)){
                return  assertord41Enums.getName();
            }

        }
        return  code;
    }

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
}
