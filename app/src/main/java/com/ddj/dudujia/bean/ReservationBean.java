package com.ddj.dudujia.bean;

/**
 * Created by hanshaobo on 11/01/2018.
 */

public class ReservationBean {
    /**
     * data : {"image":"appointment_20180106.png","price":"420.00"}
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
         * image : appointment_20180106.png
         * price : 420.00
         */

        private String image;
        private String price;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
