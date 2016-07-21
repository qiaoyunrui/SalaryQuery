package com.juhezi.salaryquery.data.params;

/**
 * {"methodname":"EmpBaseInfo","tenantid":"141","empid":"57718"}
 * 获取人员基本信息的列表
 * <p/>
 * Created by qiaoyunrui on 16-7-21.
 */
public class UserInfoParam {

    private String methodname;
    private String tenantid;
    private String empid;

    public UserInfoParam(String methodname, String tenantid, String empid) {
        this.methodname = methodname;
        this.tenantid = tenantid;
        this.empid = empid;
    }

    @Override
    public String toString() {
        return "{\"methodname\":\"" + methodname + "\","
                + "\"tenantid\":\"" + tenantid + "\","
                + "\"empid\":\"" + empid + "\"}";
    }


}
