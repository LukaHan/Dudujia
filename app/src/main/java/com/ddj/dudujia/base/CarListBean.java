package com.ddj.dudujia.base;

import java.util.List;

/**
 * Created by hanshaobo on 17/01/2018.
 */

public class CarListBean {
    private List<CarBean> data;

    public List<CarBean> getData() {
        return data;
    }

    public void setData(List<CarBean> data) {
        this.data = data;
    }

    public static class CarBean {
        /**
         * mycarid : 1000009
         * phonenumber : null
         * licenseplate : 沪A63111
         * enginenum : 123
         * vinnum : 000
         * cartype : 小型车
         * image : null
         * model : null
         */

        private String mycarid;
        private Object phonenumber;
        private String licenseplate;
        private String enginenum;
        private String vinnum;
        private String cartype;
        private Object image;
        private Object model;

        public String getMycarid() {
            return mycarid;
        }

        public void setMycarid(String mycarid) {
            this.mycarid = mycarid;
        }

        public Object getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(Object phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getLicenseplate() {
            return licenseplate;
        }

        public void setLicenseplate(String licenseplate) {
            this.licenseplate = licenseplate;
        }

        public String getEnginenum() {
            return enginenum;
        }

        public void setEnginenum(String enginenum) {
            this.enginenum = enginenum;
        }

        public String getVinnum() {
            return vinnum;
        }

        public void setVinnum(String vinnum) {
            this.vinnum = vinnum;
        }

        public String getCartype() {
            return cartype;
        }

        public void setCartype(String cartype) {
            this.cartype = cartype;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public Object getModel() {
            return model;
        }

        public void setModel(Object model) {
            this.model = model;
        }
    }
}
