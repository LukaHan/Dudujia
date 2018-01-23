package com.ddj.dudujia.bean;

import java.util.List;

/**
 * Created by hanshaobo on 18/01/2018.
 */

public class CarDetailBean {
    /**
     * data : {"cheshi":null,"weizhang":[{"violationid":"23","userid":"100003","vin":"WAURGB4H2CN031396","licenseplate":"浙B2G7V7","code":"23-950052","time":"2017-12-06 12:51:18","fine":"200","address":"S19甬台温复线高速宁波方向20KM 200M","reason":"驾驶中型以上载客载货汽车、危险物品运输车辆以外的其他机动车行驶超过规定时速10%未达20%的","point":"3","violationcity":"宁波市","province":"浙江省","city":"宁波市","servicefee":"","markfee":"","canselect":"0","processstatus":"1","violationnum":"13522","paymentstatus":"1"},{"violationid":"24","userid":"100003","vin":"WAURGB4H2CN031396","licenseplate":"浙B2G7V7","code":"23-950053","time":"2017-11-06 13:42:20","fine":"100","address":"北安路进月罗公路南约200米","reason":"机动车违反禁止标线指示的","point":"3","violationcity":"上海市","province":"上海市","city":"上海市","servicefee":"","markfee":"","canselect":"0","processstatus":"1","violationnum":"1345","paymentstatus":"1"}],"jiance":null}
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
         * cheshi : null
         * weizhang : [{"violationid":"23","userid":"100003","vin":"WAURGB4H2CN031396","licenseplate":"浙B2G7V7","code":"23-950052","time":"2017-12-06 12:51:18","fine":"200","address":"S19甬台温复线高速宁波方向20KM 200M","reason":"驾驶中型以上载客载货汽车、危险物品运输车辆以外的其他机动车行驶超过规定时速10%未达20%的","point":"3","violationcity":"宁波市","province":"浙江省","city":"宁波市","servicefee":"","markfee":"","canselect":"0","processstatus":"1","violationnum":"13522","paymentstatus":"1"},{"violationid":"24","userid":"100003","vin":"WAURGB4H2CN031396","licenseplate":"浙B2G7V7","code":"23-950053","time":"2017-11-06 13:42:20","fine":"100","address":"北安路进月罗公路南约200米","reason":"机动车违反禁止标线指示的","point":"3","violationcity":"上海市","province":"上海市","city":"上海市","servicefee":"","markfee":"","canselect":"0","processstatus":"1","violationnum":"1345","paymentstatus":"1"}]
         * jiance : null
         */

        private Object cheshi;
        private Object jiance;
        private List<WeizhangBean> weizhang;

        public Object getCheshi() {
            return cheshi;
        }

        public void setCheshi(Object cheshi) {
            this.cheshi = cheshi;
        }

        public Object getJiance() {
            return jiance;
        }

        public void setJiance(Object jiance) {
            this.jiance = jiance;
        }

        public List<WeizhangBean> getWeizhang() {
            return weizhang;
        }

        public void setWeizhang(List<WeizhangBean> weizhang) {
            this.weizhang = weizhang;
        }

        public static class WeizhangBean {
            /**
             * violationid : 23
             * userid : 100003
             * vin : WAURGB4H2CN031396
             * licenseplate : 浙B2G7V7
             * code : 23-950052
             * time : 2017-12-06 12:51:18
             * fine : 200
             * address : S19甬台温复线高速宁波方向20KM 200M
             * reason : 驾驶中型以上载客载货汽车、危险物品运输车辆以外的其他机动车行驶超过规定时速10%未达20%的
             * point : 3
             * violationcity : 宁波市
             * province : 浙江省
             * city : 宁波市
             * servicefee :
             * markfee :
             * canselect : 0
             * processstatus : 1
             * violationnum : 13522
             * paymentstatus : 1
             */

            private String violationid;
            private String userid;
            private String vin;
            private String licenseplate;
            private String code;
            private String time;
            private String fine;
            private String address;
            private String reason;
            private String point;
            private String violationcity;
            private String province;
            private String city;
            private String servicefee;
            private String markfee;
            private String canselect;
            private String processstatus;
            private String violationnum;
            private String paymentstatus;

            public String getViolationid() {
                return violationid;
            }

            public void setViolationid(String violationid) {
                this.violationid = violationid;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getVin() {
                return vin;
            }

            public void setVin(String vin) {
                this.vin = vin;
            }

            public String getLicenseplate() {
                return licenseplate;
            }

            public void setLicenseplate(String licenseplate) {
                this.licenseplate = licenseplate;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getFine() {
                return fine;
            }

            public void setFine(String fine) {
                this.fine = fine;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getPoint() {
                return point;
            }

            public void setPoint(String point) {
                this.point = point;
            }

            public String getViolationcity() {
                return violationcity;
            }

            public void setViolationcity(String violationcity) {
                this.violationcity = violationcity;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getServicefee() {
                return servicefee;
            }

            public void setServicefee(String servicefee) {
                this.servicefee = servicefee;
            }

            public String getMarkfee() {
                return markfee;
            }

            public void setMarkfee(String markfee) {
                this.markfee = markfee;
            }

            public String getCanselect() {
                return canselect;
            }

            public void setCanselect(String canselect) {
                this.canselect = canselect;
            }

            public String getProcessstatus() {
                return processstatus;
            }

            public void setProcessstatus(String processstatus) {
                this.processstatus = processstatus;
            }

            public String getViolationnum() {
                return violationnum;
            }

            public void setViolationnum(String violationnum) {
                this.violationnum = violationnum;
            }

            public String getPaymentstatus() {
                return paymentstatus;
            }

            public void setPaymentstatus(String paymentstatus) {
                this.paymentstatus = paymentstatus;
            }
        }
    }
}