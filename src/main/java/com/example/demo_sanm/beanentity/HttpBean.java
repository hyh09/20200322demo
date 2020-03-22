package com.example.demo_sanm.beanentity;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/16.
 * @by: DELL)
 *
 *
 *
 *
 *
 *
 */
public class HttpBean {

    private  String url;

    private  String appId;

    private  String appKey;

    private String  Json;

    public HttpBean() {
    }


    public HttpBean(String url, String appId, String appKey, String json) {
        this.url = url;
        this.appId = appId;
        this.appKey = appKey;
        Json = json;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getJson() {
        return Json;
    }

    public void setJson(String json) {
        Json = json;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HttpBean{");
        sb.append("url='").append(url).append('\'');
        sb.append(", appId='").append(appId).append('\'');
        sb.append(", appKey='").append(appKey).append('\'');
        sb.append(", Json='").append(Json).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
