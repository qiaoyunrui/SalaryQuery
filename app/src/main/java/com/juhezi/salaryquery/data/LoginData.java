package com.juhezi.salaryquery.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Created by qiaoyunrui on 16-7-21.
 */
public class LoginData {

    /**
     * code : 0
     * errorMsg : null
     * successResult : {"User":[{"USERID":13624,"USERNAME":"84021808","USERDESC":"彭峻峰","EMPID":57718}],"Tenant":[{"TENANTID":141,"TENANTDESC":"华为"}]}
     */

    private String code;
    private String errorMsg;
    private SuccessResultBean successResult;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public SuccessResultBean getSuccessResult() {
        return successResult;
    }

    public void setSuccessResult(SuccessResultBean successResult) {
        this.successResult = successResult;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "code='" + code + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", successResult=" + successResult +
                '}';
    }

    public static class SuccessResultBean {
        /**
         * USERID : 13624
         * USERNAME : 84021808
         * USERDESC : 彭峻峰
         * EMPID : 57718
         */

        private List<UserBean> User;
        /**
         * TENANTID : 141
         * TENANTDESC : 华为
         */

        private List<TenantBean> Tenant;

        public List<UserBean> getUser() {
            return User;
        }

        public void setUser(List<UserBean> User) {
            this.User = User;
        }

        public List<TenantBean> getTenant() {
            return Tenant;
        }

        public void setTenant(List<TenantBean> Tenant) {
            this.Tenant = Tenant;
        }

        @Override
        public String toString() {
            return "SuccessResultBean{" +
                    "User=" + User +
                    ", Tenant=" + Tenant +
                    '}';
        }

        public static class UserBean {
            private int USERID;
            private String USERNAME;
            private String USERDESC;
            private int EMPID;

            public int getUSERID() {
                return USERID;
            }

            public void setUSERID(int USERID) {
                this.USERID = USERID;
            }

            public String getUSERNAME() {
                return USERNAME;
            }

            public void setUSERNAME(String USERNAME) {
                this.USERNAME = USERNAME;
            }

            public String getUSERDESC() {
                return USERDESC;
            }

            public void setUSERDESC(String USERDESC) {
                this.USERDESC = USERDESC;
            }

            public int getEMPID() {
                return EMPID;
            }

            public void setEMPID(int EMPID) {
                this.EMPID = EMPID;
            }

            @Override
            public String toString() {
                return "UserBean{" +
                        "USERID=" + USERID +
                        ", USERNAME='" + USERNAME + '\'' +
                        ", USERDESC='" + USERDESC + '\'' +
                        ", EMPID=" + EMPID +
                        '}';
            }
        }

        public static class TenantBean {
            private int TENANTID;
            private String TENANTDESC;

            public int getTENANTID() {
                return TENANTID;
            }

            public void setTENANTID(int TENANTID) {
                this.TENANTID = TENANTID;
            }

            public String getTENANTDESC() {
                return TENANTDESC;
            }

            public void setTENANTDESC(String TENANTDESC) {
                this.TENANTDESC = TENANTDESC;
            }

            @Override
            public String toString() {
                return "TenantBean{" +
                        "TENANTID=" + TENANTID +
                        ", TENANTDESC='" + TENANTDESC + '\'' +
                        '}';
            }
        }
    }


}
