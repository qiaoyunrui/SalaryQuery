package com.juhezi.salaryquery.data.params;

/**
 * 登录参数
 * <p/>
 * Created by qiaoyunrui on 16-7-21.
 */
public class LoginParam {


    //{"methodname":"login","tenantname":"华为","username":"84021808","password":"A"}

    private String methodname;
    private String tenantname;
    private String username;
    private String password;

    public LoginParam(String methodname, String tenantname, String username, String password) {
        this.methodname = methodname;
        this.tenantname = tenantname;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "{\"methodname\":\"" + methodname + "\"," +
                "\"tenantname\":\"" + tenantname + "\"," +
                "\"username\":\"" + username + "\"," +
                "\"password\":\"" + password + "\"}";
    }



}
