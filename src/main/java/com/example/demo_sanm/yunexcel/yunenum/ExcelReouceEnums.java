package com.example.demo_sanm.yunexcel.yunenum;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2019/3/6.
 * @by: DELL)
 */
public enum ExcelReouceEnums {
    /**
     * 综合资源查询
     * 虚实属性
     *  自定义三项 ：  翻译名字,规格id,英文标识
     */
    RESOUCE_VIRTUA_LOGIC("虚(逻辑)",100705,"LOGIC"),
    RESOUCE_VIRTUA_PHYSICS("实(物理)",100706,"PHYSICS"),
    RESOUCE_VIRTUA_ALL("虚实（物理逻辑）合一",100707,"ALL"),
    ;
    private String specName;

    private Integer specId;

    private String specCode;

    ExcelReouceEnums(String specName, Integer specId, String specCode) {
        this.specName = specName;
        this.specId = specId;
        this.specCode = specCode;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String specCode) {
        this.specCode = specCode;
    }


    public  static String getSpecNmeBySpecId(Integer specId){
        for(ExcelReouceEnums excelReouceEnums:ExcelReouceEnums.values()){
            if(excelReouceEnums.getSpecId().equals(specId)){
                return  excelReouceEnums.getSpecName();
            }
        }
        return null;

    }



}
