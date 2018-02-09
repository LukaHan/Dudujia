package com.ddj.dudujia.bean;

/**
 * Created by hanshaobo on 09/02/2018.
 */

public class CheckVinBean {
    /**
     * data : {"vin":"WAURGB4H2CN031396","xsmc":"奥迪 A8L 3.0TFSI 手自一体 45TFSI Quattro 舒适型","pfbz":"欧5","scnf":"2012"}
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
         * vin : WAURGB4H2CN031396
         * xsmc : 奥迪 A8L 3.0TFSI 手自一体 45TFSI Quattro 舒适型
         * pfbz : 欧5
         * scnf : 2012
         */

        private String vin;
        private String xsmc;
        private String pfbz;
        private String scnf;

        public String getVin() {
            return vin;
        }

        public void setVin(String vin) {
            this.vin = vin;
        }

        public String getXsmc() {
            return xsmc;
        }

        public void setXsmc(String xsmc) {
            this.xsmc = xsmc;
        }

        public String getPfbz() {
            return pfbz;
        }

        public void setPfbz(String pfbz) {
            this.pfbz = pfbz;
        }

        public String getScnf() {
            return scnf;
        }

        public void setScnf(String scnf) {
            this.scnf = scnf;
        }
    }
}
