package com.ddj.dudujia.bean;

/**
 * Created by hanshaobo on 15/10/2017.
 */

public class LoginBean {
    /**
     * data : {"userid":"6002559","username":"","phone":"18221971917"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userid : 6002559
         * username :
         * phone : 18221971917
         */

        private String userid;
        private String username;
        private String phone;
        private String usertype;
        private String detectorid;
        private String mycarnum;
        private String myreportnum;

        public String getUsertype() {
            return usertype;
        }

        public void setUsertype(String usertype) {
            this.usertype = usertype;
        }

        public String getDetectorid() {
            return detectorid;
        }

        public void setDetectorid(String detectorid) {
            this.detectorid = detectorid;
        }

        public String getMycarnum() {
            return mycarnum;
        }

        public void setMycarnum(String mycarnum) {
            this.mycarnum = mycarnum;
        }

        public String getMyreportnum() {
            return myreportnum;
        }

        public void setMyreportnum(String myreportnum) {
            this.myreportnum = myreportnum;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
