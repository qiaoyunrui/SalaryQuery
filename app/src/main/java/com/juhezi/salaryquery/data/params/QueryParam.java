package com.juhezi.salaryquery.data.params;

import com.google.gson.JsonObject;

import org.json.JSONObject;

/**
 * 查询工资的参数列表
 * <p/>
 * Created by qiaoyunrui on 16-7-21.
 */
public class QueryParam {

    private String methodname;
    private String tenantid;
    private String empid;
    private String month;

    public QueryParam(String methodname, String tenantid, String empid, String month) {
        this.methodname = methodname;
        this.tenantid = tenantid;
        this.empid = empid;
        this.month = month;
    }

    @Override
    public String toString() {
        return "{\"methodname\":\"" + methodname + '\"' +
                ",\"tenantid\":\"" + tenantid + '\"' +
                ",\"empid\":\"" + empid + '\"' +
                ",\"month\":\"" + month + "\"}";
    }

}
