package com.example.demo_sanm.yunexcel.yunenum;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2019/3/7.
 * @by: DELL)
 */
public enum MainDataQryExcel1Enum {
    /**
     * 如果前台能传标题,则弃用标题那列
     */


    DATA_DEOMVOANKT("deomvoankt","资产分类清单","DeOmVoankt",  new String[]{"资产分类","资产描述 "}),

    DATA_DEOMVOCDFCODECONFIG("deomvocdfcodeconfig","建设性质","DeOmVocdfcodeconfig", new String[]{"建设性质编码","建设性质名称"}),

    DATA_DEOMVOCPROJSTATUS("deomvocprojstatus","工程状态","DeOmVocsapcostcenter", new String[]{"工程状态主键","状态描述","组织编码","组织简称","所属公司1","所属公司2","上级组织","上级编码","公司类型"}),

    DATA_DeOmVocsapcostcenter("deomvocsapcostcenter","成本中心清单","DeOmVocsapcostcenter", new String[]{"成本中心","成本中心描述","公司代码","利润中心","是否虚拟成本中心","控制范围","有效起始日","有效期至","负责人","部门","成本中心类型","层次结构范围"}),

    DATA_DeOmVocspeccode("deomvocspeccode","业务大、小类","DeOmVocspeccode",new String[]{"大小类主键编码1","大小类主键编码2",}),

    DATA_deOmVocsaporganize("deomvocsaporganize","公司代码清单","DeOmVocsaporganize",new String[]{"公司代码描述","公司代码"}),

    DATA_DeOmVot087("deomvot087","资产性质清单","DeOmVot087",new String[]{"评审小组 1 - 4","评审小组:短描述"}),

    DATA_DeOmVot087t("deomvot087t","资产归属清单","DeOmVot087t",new String[]{"评审小组 1 - 4 ","评审小组:短描述"}),

    DATA_DeOmVot090nat("deomvot090nat","折旧码清单","DeOmVot090nat",new String[]{"有关资产评估的折旧表","折旧码","外部折旧码名称"}),

    DATA_DeOmVot093("deomvot093","折旧范围清单","DeOmVot093",new String[]{"有关资产评估的折旧表","实际的或派生的折旧范围","折旧范围的目的"}),

    DATA_DeOmVot095t("deomvot095t","科目定位码清单","DeOmVot095t",new String[]{"科目定位码","科目组说明 "}),

    DATA_DeOmVoztbanzT("deomvoztbanzt","班组清单","DeOmVoztbanzT",new String[]{"班组"," 班组名称 "}),

    DATA_DeOmVocassetjz("deomvocassetjz","基站（机房）ID清单","DeOmVocassetjz", new String[]{"基站（机房）编码 ","基站（机房）名称 "}),

    DATA_DeOmVocassetqxgs("deomvocassetqxgs","区县分公司清单","DeOmVocassetqxgs",new String[]{"区县公司 ","区县公司描述 "}),

    DATA_DeOmVocassetqybm("deomvocassetqybm","区域编码清单","DeOmVocassetqybm",new String[]{"区县公司","区县公司描述 "}),

    DATA_CassetgldeptUnionGluser("deomvocassetgldept","实物管理部门清单","CassetgldeptUnionGluser",new String[]{"部门编码","部门名称","公司代码","利润中心","利润中心描述","管理员","管理员姓名"}),

    DATA_CassetsydeptUnionSyuser("deomvocassetsydept","使用部门清单","CassetsydeptUnionSyuser",new String[]{"部门编码","部门名称","利润中心","利润中心描述","公司代码","保管员","保管员姓名"}),

    DATA_DeOmVocassetyyt("deomvocassetyyt","营业厅机构ID清单","DeOmVocassetyyt",new String[]{"营业厅编码","营业厅描述"}),

    DATA_DeOmVoztzclbT("deomvoztzclbt","作业资产类别清单","DeOmVoztzclbT",new String[]{"作业资产成本类别","资产类别描述"}),

    DATA_DeOmVoztzjyyT("deomvoztzjyyt","增加原因清单","DeOmVoztzjyyT",new String[]{"增加原因","增加原因描述"}),

    DATA_DeOmVocassetzj("deomvocassetzj","支局清单","DeOmVocassetzj",new String[]{"支局编码","支局描述"}),

     DATA_DeOmVocjobcostclass("deomvocjobcostclass","作业资产类别","DeOmVocjobcostclass",new String[]{"类","项","目","节","资产归属","作业资产类别"}),

     DATA_DeOmVosareacode("deomvosareacode","MSS区域","DeOmVosareacode",new String[]{"地区代码","地区名称","统一目录地区码","地区简称","地区全称","地区级别","地区代号","地区级别中文","SAP组织机构代码","用来生成部门ID的代码"}),
//
     DATA_DeOmVosunit("deomvosunit","计量单位","DeOmVosunit",new String[]{"计量单位中文","计量单位编码"}),
//
     DATA_DeOmVot880("deomvot880","贸易伙伴清单","DeOmVot880",new String[]{"名称","贸易伙伴","公司2的名称","公司所在的国家","语言代码","公司所处的街区","公司的邮政信箱","全局公司的邮政编码","公司所在城市"}),
//
//
     DATA_DeOmVocepct("deomvocepct","利润中心主数据","DeOmVocepct",new String[]{"一般姓名","利润中心组","公司代码","利润中心","负责人","部门"}),
//
     DATA_DeOmVocthirdsupplier("deomvocthirdsupplier","民资出资方","DeOmVocthirdsupplier",new String[]{"出资方编码","出资方名称","地区"}),
//
      DATA_MmAssetSpec("mmassetspec","资产目录","MmAssetSpec",new String[]{"编码","名称","父目录编码","会计折旧年限","会计折旧月份","单位","是否网络类资产"}),
//
      DATA_MmMaterielSpec("mmmaterielspec","物料目录","MmMaterielSpec",new String[]{"编码","名称","父目录编码"}),
//
      DATA_MmAssetMaterielMapping("mmassetmaterielmapping","物料目录与资产目录关系","MmAssetMaterielMapping",new String[]{"物料目录编码","物料目录名称","资产目录编码","资产目录名称"}),



    DATA_MmMarMapping("mmmarmapping","资源规格与资产目录、物料目录关系","MmMarMapping",new String[]{"名称","实体规格ID","对端目录ID","对端目录类型ID","编码"}),


    /**
     * 交资明细
     */
    DATA_AssetDetailVo("DetailInfoExcel","综合查询交资明细清单","ZgAssetDetailVo",new String[]{"物料目录","物料描述","物料编码","数量(面积)","计量单位","设备采购合同价(元)(不含增值税)","资源编码","集团编码","资源名称","资源GID","规格、型号、结构","所在地点","生产厂家","利润中心(组)","使用单位","成本中心","资产管理部门","资产管理员","使用部门","保管员","使用人","区域编码","区县分公司","支局","营业厅机构ID","固定资产目录","资产目录名称","资产分类","资产归属","作业成本资产类别","折旧年限","原资产卡片号","资产卡片号","专业属性","是否客户端资产","客户端名称","是否扩容","扩容设备原固定资产卡片编号","转固资产序号","备注"}),

    ;
    //前台入参
    private String parameter;
    //表name
    private String translateName;
    //类名
    private String ClassName;
    //标题(一般主数据变化不大)
    private  String[] fields;



    MainDataQryExcel1Enum(String parameter, String translateName, String className, String[] fields) {
        this.parameter = parameter;
        this.translateName = translateName;
        this.ClassName = className;
        this.fields = fields;
    }


    /**
     *
     * @param parameter  前台传的入参
     * @return 返回中文名
     */
    public static String gettranslateNamebypar(String parameter){
        for(MainDataQryExcel1Enum mainDataQryExcel1Enum :MainDataQryExcel1Enum.values()){
            if(mainDataQryExcel1Enum.getParameter().equals(parameter)){
                return mainDataQryExcel1Enum.getTranslateName();
            }
        }
        return  null;
    }

    /**
     * 返回标题
     * @return
     */
    public  static String[]  getfieldsbyid(String parameter){
        for(MainDataQryExcel1Enum  mainDataQryExcel1Enum:MainDataQryExcel1Enum.values()){
            if(mainDataQryExcel1Enum.getParameter().equals(parameter)){

                return  mainDataQryExcel1Enum.getFields();
            }
        }
        return  null;
    }

    /**
     * 返回类名
     * @return
     */
    public  static String getClassName(String parameter){
        for(MainDataQryExcel1Enum  mainDataQryExcel1Enum:MainDataQryExcel1Enum.values()){
            if(mainDataQryExcel1Enum.getParameter().equals(parameter)){
                return mainDataQryExcel1Enum.getClassName();
            }
        }
        return null;
    }


    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getTranslateName() {
        return translateName;
    }

    public void setTranslateName(String translateName) {
        this.translateName = translateName;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }
}
