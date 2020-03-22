package com.example.demo_sanm.contro;

import java.util.List;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/3.
 * @by: DELL)
 */
public class ResultBos {
    /**
     * “resultBos”：[
     {
     “resultCode”：“”，
     “errorMsg”：“”，
     “instanceId”：“”，
     “results”：
     [
     {
     “bukrs”：“”，
     “assetsCardCode”：“”，
     “result”：“”
     }，
     ……
     ]
     }，
     ……
     ]

     */

    //2	返回码	1:成功,0:失败(必填)
    private String resultCode;

    //String	200	错误信息	非必填
    private String errorMsg;

//String	32	流程ID	成功时必填
    private String instanceId;


    private List<Results> results;


    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public List <Results> getResults() {
        return results;
    }

    public void setResults(List <Results> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResultBos{");
        sb.append("resultCode='").append(resultCode).append('\'');
        sb.append(", errorMsg='").append(errorMsg).append('\'');
        sb.append(", instanceId='").append(instanceId).append('\'');
        sb.append(", results=").append(results);
        sb.append('}');
        return sb.toString();
    }
}
