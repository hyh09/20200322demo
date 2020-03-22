package com.example.demo_sanm.yunexcel.excelvo;

import com.sinovate.ngrms.gcbscsvr.mdl.db.gmdata.*;
import com.sinovate.ngrms.gcbscsvr.mdl.db.gmeta.MmAssetMaterielMapping;
import com.sinovate.ngrms.gcbscsvr.mdl.db.gmeta.MmAssetSpec;
import com.sinovate.ngrms.gcbscsvr.mdl.db.gmeta.MmMarMapping;
import com.sinovate.ngrms.gcbscsvr.mdl.db.gmeta.MmMaterielSpec;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2019/3/7.
 * @by: DELL)
 *
 * 综合查询下  mss主数据导出
 */
public class MainDataExcelVo {
    /**
     * 资产分类清单
     */
    DeOmVoankt deOmVoankt;
    /**
     * 建设性质
     */
    DeOmVocdfcodeconfig deOmVocdfcodeconfig;
    /**
     * 工程状态查询
     */
    DeOmVocprojstatus deOmVocprojstatus;
    /**
     * 成本中心清单
     */
    DeOmVocsapcostcenter deOmVocsapcostcenter;
    /**
     *  业务大、小类
     */
    DeOmVocspeccode deOmVocspeccode;
    /**
     * 公司代码清单
     */
    DeOmVocsaporganize deOmVocsaporganize;
    /**
     * 资产性质清单
     */
    DeOmVot087 deOmVot087;
    /**
     *资产归属清单
     */
    DeOmVot087t deOmVot087t;
    /**
     * 折旧码清单
     */
    DeOmVot090nat deOmVot090nat;
    /**
     * 折旧范围清单
     */
    DeOmVot093 deOmVot093;
    /**
     * 科目定位码清单
     */
    DeOmVot095t deOmVot095t;
    /**
     * 班组清单
     */
    DeOmVoztbanzT deOmVoztbanzT;
    /**
     *  基站（机房）ID清单
     */
    DeOmVocassetjz deOmVocassetjz;
    /**
     * 区县分公司清单
     */
    DeOmVocassetqxgs deOmVocassetqxgs;
    /**
     *区域编码清单
     */
    DeOmVocassetqybm deOmVocassetqybm;
    /**
     *实物管理部门清单查询
     */
    CassetgldeptUnionGluser cassetgldeptUnionGluser;
    /**
     *使用部门清单
     */
    CassetsydeptUnionSyuser cassetsydeptUnionSyuser;
    /**
     * 营业厅机构ID清单
     */
    DeOmVocassetyyt deOmVocassetyyt;
    /**
     *作业资产类别清单
     */
    DeOmVoztzclbT deOmVoztzclbT;
    /**
     *增加原因清单
     */
    DeOmVoztzjyyT deOmVoztzjyyT;
    /**
     *支局清单
     */
    DeOmVocassetzj deOmVocassetzj;
    /**
     *作业资产类别
     */
    DeOmVocjobcostclass deOmVocjobcostclass;
    /**
     *MSS区域
     */
    DeOmVosareacode deOmVosareacode;
    /**
     *计量单位
     */
    DeOmVosunit deOmVosunit;
    /**
     *贸易伙伴清单
     */
    DeOmVot880 deOmVot880;
    /**
     *利润中心主数据
     */
    DeOmVocepct deOmVocepct;
    /**
     *民资出资方
     */
    DeOmVocthirdsupplier deOmVocthirdsupplier;
    /**
     *资产目录
     */
    MmAssetSpec mmAssetSpec;
    /**
     *物料目录
     */
    MmMaterielSpec mmMaterielSpec;
    /**
     *物料目录与资产目录关系
     */
    MmAssetMaterielMapping mmAssetMaterielMapping;
    /**
     *资源规格与资产目录、物料目录关系
     */
    MmMarMapping mmMarMapping;

    public DeOmVoankt getDeOmVoankt() {
        return deOmVoankt;
    }

    public void setDeOmVoankt(DeOmVoankt deOmVoankt) {
        this.deOmVoankt = deOmVoankt;
    }

    public DeOmVocdfcodeconfig getDeOmVocdfcodeconfig() {
        return deOmVocdfcodeconfig;
    }

    public void setDeOmVocdfcodeconfig(DeOmVocdfcodeconfig deOmVocdfcodeconfig) {
        this.deOmVocdfcodeconfig = deOmVocdfcodeconfig;
    }

    public DeOmVocprojstatus getDeOmVocprojstatus() {
        return deOmVocprojstatus;
    }

    public void setDeOmVocprojstatus(DeOmVocprojstatus deOmVocprojstatus) {
        this.deOmVocprojstatus = deOmVocprojstatus;
    }

    public DeOmVocsapcostcenter getDeOmVocsapcostcenter() {
        return deOmVocsapcostcenter;
    }

    public void setDeOmVocsapcostcenter(DeOmVocsapcostcenter deOmVocsapcostcenter) {
        this.deOmVocsapcostcenter = deOmVocsapcostcenter;
    }

    public DeOmVocspeccode getDeOmVocspeccode() {
        return deOmVocspeccode;
    }

    public void setDeOmVocspeccode(DeOmVocspeccode deOmVocspeccode) {
        this.deOmVocspeccode = deOmVocspeccode;
    }

    public DeOmVocsaporganize getDeOmVocsaporganize() {
        return deOmVocsaporganize;
    }

    public void setDeOmVocsaporganize(DeOmVocsaporganize deOmVocsaporganize) {
        this.deOmVocsaporganize = deOmVocsaporganize;
    }

    public DeOmVot087 getDeOmVot087() {
        return deOmVot087;
    }

    public void setDeOmVot087(DeOmVot087 deOmVot087) {
        this.deOmVot087 = deOmVot087;
    }

    public DeOmVot087t getDeOmVot087t() {
        return deOmVot087t;
    }

    public void setDeOmVot087t(DeOmVot087t deOmVot087t) {
        this.deOmVot087t = deOmVot087t;
    }

    public DeOmVot090nat getDeOmVot090nat() {
        return deOmVot090nat;
    }

    public void setDeOmVot090nat(DeOmVot090nat deOmVot090nat) {
        this.deOmVot090nat = deOmVot090nat;
    }

    public DeOmVot093 getDeOmVot093() {
        return deOmVot093;
    }

    public void setDeOmVot093(DeOmVot093 deOmVot093) {
        this.deOmVot093 = deOmVot093;
    }

    public DeOmVot095t getDeOmVot095t() {
        return deOmVot095t;
    }

    public void setDeOmVot095t(DeOmVot095t deOmVot095t) {
        this.deOmVot095t = deOmVot095t;
    }

    public DeOmVoztbanzT getDeOmVoztbanzT() {
        return deOmVoztbanzT;
    }

    public void setDeOmVoztbanzT(DeOmVoztbanzT deOmVoztbanzT) {
        this.deOmVoztbanzT = deOmVoztbanzT;
    }

    public DeOmVocassetjz getDeOmVocassetjz() {
        return deOmVocassetjz;
    }

    public void setDeOmVocassetjz(DeOmVocassetjz deOmVocassetjz) {
        this.deOmVocassetjz = deOmVocassetjz;
    }

    public DeOmVocassetqxgs getDeOmVocassetqxgs() {
        return deOmVocassetqxgs;
    }

    public void setDeOmVocassetqxgs(DeOmVocassetqxgs deOmVocassetqxgs) {
        this.deOmVocassetqxgs = deOmVocassetqxgs;
    }

    public DeOmVocassetqybm getDeOmVocassetqybm() {
        return deOmVocassetqybm;
    }

    public void setDeOmVocassetqybm(DeOmVocassetqybm deOmVocassetqybm) {
        this.deOmVocassetqybm = deOmVocassetqybm;
    }

    public CassetgldeptUnionGluser getCassetgldeptUnionGluser() {
        return cassetgldeptUnionGluser;
    }

    public void setCassetgldeptUnionGluser(CassetgldeptUnionGluser cassetgldeptUnionGluser) {
        this.cassetgldeptUnionGluser = cassetgldeptUnionGluser;
    }

    public CassetsydeptUnionSyuser getCassetsydeptUnionSyuser() {
        return cassetsydeptUnionSyuser;
    }

    public void setCassetsydeptUnionSyuser(CassetsydeptUnionSyuser cassetsydeptUnionSyuser) {
        this.cassetsydeptUnionSyuser = cassetsydeptUnionSyuser;
    }

    public DeOmVocassetyyt getDeOmVocassetyyt() {
        return deOmVocassetyyt;
    }

    public void setDeOmVocassetyyt(DeOmVocassetyyt deOmVocassetyyt) {
        this.deOmVocassetyyt = deOmVocassetyyt;
    }

    public DeOmVoztzclbT getDeOmVoztzclbT() {
        return deOmVoztzclbT;
    }

    public void setDeOmVoztzclbT(DeOmVoztzclbT deOmVoztzclbT) {
        this.deOmVoztzclbT = deOmVoztzclbT;
    }

    public DeOmVoztzjyyT getDeOmVoztzjyyT() {
        return deOmVoztzjyyT;
    }

    public void setDeOmVoztzjyyT(DeOmVoztzjyyT deOmVoztzjyyT) {
        this.deOmVoztzjyyT = deOmVoztzjyyT;
    }

    public DeOmVocassetzj getDeOmVocassetzj() {
        return deOmVocassetzj;
    }

    public void setDeOmVocassetzj(DeOmVocassetzj deOmVocassetzj) {
        this.deOmVocassetzj = deOmVocassetzj;
    }

    public DeOmVocjobcostclass getDeOmVocjobcostclass() {
        return deOmVocjobcostclass;
    }

    public void setDeOmVocjobcostclass(DeOmVocjobcostclass deOmVocjobcostclass) {
        this.deOmVocjobcostclass = deOmVocjobcostclass;
    }

    public DeOmVosareacode getDeOmVosareacode() {
        return deOmVosareacode;
    }

    public void setDeOmVosareacode(DeOmVosareacode deOmVosareacode) {
        this.deOmVosareacode = deOmVosareacode;
    }

    public DeOmVosunit getDeOmVosunit() {
        return deOmVosunit;
    }

    public void setDeOmVosunit(DeOmVosunit deOmVosunit) {
        this.deOmVosunit = deOmVosunit;
    }

    public DeOmVot880 getDeOmVot880() {
        return deOmVot880;
    }

    public void setDeOmVot880(DeOmVot880 deOmVot880) {
        this.deOmVot880 = deOmVot880;
    }

    public DeOmVocepct getDeOmVocepct() {
        return deOmVocepct;
    }

    public void setDeOmVocepct(DeOmVocepct deOmVocepct) {
        this.deOmVocepct = deOmVocepct;
    }

    public DeOmVocthirdsupplier getDeOmVocthirdsupplier() {
        return deOmVocthirdsupplier;
    }

    public void setDeOmVocthirdsupplier(DeOmVocthirdsupplier deOmVocthirdsupplier) {
        this.deOmVocthirdsupplier = deOmVocthirdsupplier;
    }

    public MmAssetSpec getMmAssetSpec() {
        return mmAssetSpec;
    }

    public void setMmAssetSpec(MmAssetSpec mmAssetSpec) {
        this.mmAssetSpec = mmAssetSpec;
    }

    public MmMaterielSpec getMmMaterielSpec() {
        return mmMaterielSpec;
    }

    public void setMmMaterielSpec(MmMaterielSpec mmMaterielSpec) {
        this.mmMaterielSpec = mmMaterielSpec;
    }

    public MmAssetMaterielMapping getMmAssetMaterielMapping() {
        return mmAssetMaterielMapping;
    }

    public void setMmAssetMaterielMapping(MmAssetMaterielMapping mmAssetMaterielMapping) {
        this.mmAssetMaterielMapping = mmAssetMaterielMapping;
    }

    public MmMarMapping getMmMarMapping() {
        return mmMarMapping;
    }

    public void setMmMarMapping(MmMarMapping mmMarMapping) {
        this.mmMarMapping = mmMarMapping;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MainDataExcelVo{");
        sb.append("deOmVoankt=").append(deOmVoankt);
        sb.append(", deOmVocdfcodeconfig=").append(deOmVocdfcodeconfig);
        sb.append(", deOmVocprojstatus=").append(deOmVocprojstatus);
        sb.append(", deOmVocsapcostcenter=").append(deOmVocsapcostcenter);
        sb.append(", deOmVocspeccode=").append(deOmVocspeccode);
        sb.append(", deOmVocsaporganize=").append(deOmVocsaporganize);
        sb.append(", deOmVot087=").append(deOmVot087);
        sb.append(", deOmVot087t=").append(deOmVot087t);
        sb.append(", deOmVot090nat=").append(deOmVot090nat);
        sb.append(", deOmVot093=").append(deOmVot093);
        sb.append(", deOmVot095t=").append(deOmVot095t);
        sb.append(", deOmVoztbanzT=").append(deOmVoztbanzT);
        sb.append(", deOmVocassetjz=").append(deOmVocassetjz);
        sb.append(", deOmVocassetqxgs=").append(deOmVocassetqxgs);
        sb.append(", deOmVocassetqybm=").append(deOmVocassetqybm);
        sb.append(", cassetgldeptUnionGluser=").append(cassetgldeptUnionGluser);
        sb.append(", cassetsydeptUnionSyuser=").append(cassetsydeptUnionSyuser);
        sb.append(", deOmVocassetyyt=").append(deOmVocassetyyt);
        sb.append(", deOmVoztzclbT=").append(deOmVoztzclbT);
        sb.append(", deOmVoztzjyyT=").append(deOmVoztzjyyT);
        sb.append(", deOmVocassetzj=").append(deOmVocassetzj);
        sb.append(", deOmVocjobcostclass=").append(deOmVocjobcostclass);
        sb.append(", deOmVosareacode=").append(deOmVosareacode);
        sb.append(", deOmVosunit=").append(deOmVosunit);
        sb.append(", deOmVot880=").append(deOmVot880);
        sb.append(", deOmVocepct=").append(deOmVocepct);
        sb.append(", deOmVocthirdsupplier=").append(deOmVocthirdsupplier);
        sb.append(", mmAssetSpec=").append(mmAssetSpec);
        sb.append(", mmMaterielSpec=").append(mmMaterielSpec);
        sb.append(", mmAssetMaterielMapping=").append(mmAssetMaterielMapping);
        sb.append(", mmMarMapping=").append(mmMarMapping);
        sb.append('}');
        return sb.toString();
    }
}
