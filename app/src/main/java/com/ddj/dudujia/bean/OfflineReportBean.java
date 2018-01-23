package com.ddj.dudujia.bean;

import java.util.List;

/**
 * Created by hanshaobo on 22/01/2018.
 */

public class OfflineReportBean {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * offlineapplicationid : 100009
         * orderid : 170151487705644510
         * address : 上海市 黄埔区 九江路501号
         * phone : 18301931581
         * time : 2018-01-02 15:07
         * vin : LB37954SXCJ007555
         * carmodel : 奥迪 S7
         * reportid : 100023
         * updatedt : 2018-01-02 15:10:56
         */

        private String offlineapplicationid;
        private String orderid;
        private String address;
        private String phone;
        private String time;
        private String vin;
        private String carmodel;
        private String reportid;
        private String updatedt;

        public String getOfflineapplicationid() {
            return offlineapplicationid;
        }

        public void setOfflineapplicationid(String offlineapplicationid) {
            this.offlineapplicationid = offlineapplicationid;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getVin() {
            return vin;
        }

        public void setVin(String vin) {
            this.vin = vin;
        }

        public String getCarmodel() {
            return carmodel;
        }

        public void setCarmodel(String carmodel) {
            this.carmodel = carmodel;
        }

        public String getReportid() {
            return reportid;
        }

        public void setReportid(String reportid) {
            this.reportid = reportid;
        }

        public String getUpdatedt() {
            return updatedt;
        }

        public void setUpdatedt(String updatedt) {
            this.updatedt = updatedt;
        }
    }
}
