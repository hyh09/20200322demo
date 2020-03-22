package com.example.demo_sanm.contro.rubo;


import java.util.List;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/3.
 * @by: DELL)
 */
public class StrongMssChekbo {

   // appSystemId	String	2	应用系统ID	非必填，可传空值
    private  String appSystemId;
   // userId	String	10	用户id	非必填，可暂传空值
    private  String userId;
  //  password	String	20	密码	非必填，可传空值

    private  String password;
    /**processCode	String	32	流程编码	(必填)
     *   报废：zc_tc_bf
     网间调拨：zc_db_bdwj
     省间调拨（调出方）：zc_db_sj_dc_first
     省间调拨（调入方）：zc_db_sj_dr_first
     * */
    private String processCode;

    /**    instanceId	String	100	MSS工单号	必填*/
     private String instanceId;


    /**orgCode	String	32	发起组织编码	必填 */
    private  String orgCode;


   /** endMark	String	1	分包结束标志	必填，0代表未结束，1代表结束*/
   private  String endMark;
   /** orgName	String	32	发起组织名称	必填*/
   private  String orgName;
   /** appDate	String	32	申请日期	必填*/
   private  String appDate;
    /**account	String	32	发起人账号	必填*/
    private  String account;

    /**principalName	String	32	发起人名称	必填*/
    private  String principalName;

    /**moveOut	String	32	调出部门	仅对调拨流程必填，其他传空值*/
    private  String moveOut;

    /**moveIn	String	32	调入部门	仅对调拨流程必填，其他传空值*/
    private  String moveIn;

    /**sapCompanyName	String	32	账套	必填*/
    private  String sapCompanyName;

    /**reason	String	32	原因	必填*/
    private  String reason;


    /**note	String	500	备注	　*/
    private  String note;


    private List<AssetsCardItems> assetsCardItems;


    public String getAppSystemId() {
        return appSystemId;
    }

    public void setAppSystemId(String appSystemId) {
        this.appSystemId = appSystemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getEndMark() {
        return endMark;
    }

    public void setEndMark(String endMark) {
        this.endMark = endMark;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getMoveOut() {
        return moveOut;
    }

    public void setMoveOut(String moveOut) {
        this.moveOut = moveOut;
    }

    public String getMoveIn() {
        return moveIn;
    }

    public void setMoveIn(String moveIn) {
        this.moveIn = moveIn;
    }

    public String getSapCompanyName() {
        return sapCompanyName;
    }

    public void setSapCompanyName(String sapCompanyName) {
        this.sapCompanyName = sapCompanyName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List <AssetsCardItems> getAssetsCardItems() {
        return assetsCardItems;
    }

    public void setAssetsCardItems(List <AssetsCardItems> assetsCardItems) {
        this.assetsCardItems = assetsCardItems;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StrongMssChekbo{");
        sb.append("appSystemId='").append(appSystemId).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", processCode='").append(processCode).append('\'');
        sb.append(", instanceId='").append(instanceId).append('\'');
        sb.append(", orgCode='").append(orgCode).append('\'');
        sb.append(", endMark='").append(endMark).append('\'');
        sb.append(", orgName='").append(orgName).append('\'');
        sb.append(", appDate='").append(appDate).append('\'');
        sb.append(", account='").append(account).append('\'');
        sb.append(", principalName='").append(principalName).append('\'');
        sb.append(", moveOut='").append(moveOut).append('\'');
        sb.append(", moveIn='").append(moveIn).append('\'');
        sb.append(", sapCompanyName='").append(sapCompanyName).append('\'');
        sb.append(", reason='").append(reason).append('\'');
        sb.append(", note='").append(note).append('\'');
        sb.append(", assetsCardItems=").append(assetsCardItems);
        sb.append('}');
        return sb.toString();
    }
}
