package com.ddj.dudujia.bean;

import java.io.Serializable;
import java.util.List;

public class HealthReportBean implements Serializable {
    /**
     * data : {"cjmc":"宝马","pp":"宝马","cx":"330i Convertible 手自一体","vin":"WBAWL51039PY*****","license":"沪AB***8","mileage":"61865 KM","color":"褐色","emission":"欧4","displacement":"3.0T","productionyear":"2009","effectivetime":"2018-4-18","failuretime":"2018-5-18","protectedcar":"1","protectednote":"超过保障承诺时效，已失效","summary":[{"item":"重大事故","level":"3","times":"1","image":""},{"item":"非火烧","level":"1","times":"","image":""},{"item":"非泡水","level":"1","times":"","image":""},{"item":"里程有风险","level":"1","times":"","image":""},{"item":"大额理赔","level":"1","times":"","image":""},{"item":"过户次数","level":"2","times":"1","image":""}],"insurance":{"insuranceid":"100000","compensation":"79000","recording":"5","commercial":"天安保险公司","commercialnumber":"1702013081615263719***","commercialtime":"2013-08-16","compulsory":"天安保险公司","compulsorynumber":"1702013081657361827***","compulsorytime":"2013-08-16","insurancereporttime":"2018-04-19"},"carhistory":{"carhistoryid":"100000","carhistorylevel":"1","summaryitems":[{"item":"重大事故","level":"3","project":"后保险杠做漆；局部钣金；"}]},"testing":{"testingid":"100014","testingitems":null},"images":{"image1":"20180604/15280939645280.jpg","image2":"20180604/15280939993375.jpg"},"tester":{"testername":"范长江","testernote":"AIS特级认证检测员；国家高级二手车评估师；AIS高级培训师","testerimage":"100022_201806131912.png","testercall":"18301931581"},"testeropinion":{"opinion":"该车经过AIS267项实地检测，A柱有明显切割痕迹，挡风玻璃更换为复牌，存在重大事故可能。需结合维修记录和出险记录进行核实。"}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * cjmc : 宝马
         * pp : 宝马
         * cx : 330i Convertible 手自一体
         * vin : WBAWL51039PY*****
         * license : 沪AB***8
         * mileage : 61865 KM
         * color : 褐色
         * emission : 欧4
         * displacement : 3.0T
         * productionyear : 2009
         * effectivetime : 2018-4-18
         * failuretime : 2018-5-18
         * protectedcar : 1
         * protectednote : 超过保障承诺时效，已失效
         * summary : [{"item":"重大事故","level":"3","times":"1","image":""},{"item":"非火烧","level":"1","times":"","image":""},{"item":"非泡水","level":"1","times":"","image":""},{"item":"里程有风险","level":"1","times":"","image":""},{"item":"大额理赔","level":"1","times":"","image":""},{"item":"过户次数","level":"2","times":"1","image":""}]
         * insurance : {"insuranceid":"100000","compensation":"79000","recording":"5","commercial":"天安保险公司","commercialnumber":"1702013081615263719***","commercialtime":"2013-08-16","compulsory":"天安保险公司","compulsorynumber":"1702013081657361827***","compulsorytime":"2013-08-16","insurancereporttime":"2018-04-19"}
         * carhistory : {"carhistoryid":"100000","carhistorylevel":"1","summaryitems":[{"item":"重大事故","level":"3","project":"后保险杠做漆；局部钣金；"}]}
         * testing : {"testingid":"100014","testingitems":null}
         * images : {"image1":"20180604/15280939645280.jpg","image2":"20180604/15280939993375.jpg"}
         * tester : {"testername":"范长江","testernote":"AIS特级认证检测员；国家高级二手车评估师；AIS高级培训师","testerimage":"100022_201806131912.png","testercall":"18301931581"}
         * testeropinion : {"opinion":"该车经过AIS267项实地检测，A柱有明显切割痕迹，挡风玻璃更换为复牌，存在重大事故可能。需结合维修记录和出险记录进行核实。"}
         */

        private String cjmc;
        private String pp;
        private String cx;
        private String vin;
        private String license;
        private String mileage;
        private String color;
        private String emission;
        private String displacement;
        private String productionyear;
        private String effectivetime;
        private String failuretime;
        private String protectedcar;
        private String protectednote;
        private InsuranceBean insurance;
        private CarhistoryBean carhistory;
        private TestingBean testing;
        private ImagesBean images;
        private TesterBean tester;
        private TesteropinionBean testeropinion;
        private List<SummaryBean> summary;

        public String getCjmc() {
            return cjmc;
        }

        public void setCjmc(String cjmc) {
            this.cjmc = cjmc;
        }

        public String getPp() {
            return pp;
        }

        public void setPp(String pp) {
            this.pp = pp;
        }

        public String getCx() {
            return cx;
        }

        public void setCx(String cx) {
            this.cx = cx;
        }

        public String getVin() {
            return vin;
        }

        public void setVin(String vin) {
            this.vin = vin;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getMileage() {
            return mileage;
        }

        public void setMileage(String mileage) {
            this.mileage = mileage;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getEmission() {
            return emission;
        }

        public void setEmission(String emission) {
            this.emission = emission;
        }

        public String getDisplacement() {
            return displacement;
        }

        public void setDisplacement(String displacement) {
            this.displacement = displacement;
        }

        public String getProductionyear() {
            return productionyear;
        }

        public void setProductionyear(String productionyear) {
            this.productionyear = productionyear;
        }

        public String getEffectivetime() {
            return effectivetime;
        }

        public void setEffectivetime(String effectivetime) {
            this.effectivetime = effectivetime;
        }

        public String getFailuretime() {
            return failuretime;
        }

        public void setFailuretime(String failuretime) {
            this.failuretime = failuretime;
        }

        public String getProtectedcar() {
            return protectedcar;
        }

        public void setProtectedcar(String protectedcar) {
            this.protectedcar = protectedcar;
        }

        public String getProtectednote() {
            return protectednote;
        }

        public void setProtectednote(String protectednote) {
            this.protectednote = protectednote;
        }

        public InsuranceBean getInsurance() {
            return insurance;
        }

        public void setInsurance(InsuranceBean insurance) {
            this.insurance = insurance;
        }

        public CarhistoryBean getCarhistory() {
            return carhistory;
        }

        public void setCarhistory(CarhistoryBean carhistory) {
            this.carhistory = carhistory;
        }

        public TestingBean getTesting() {
            return testing;
        }

        public void setTesting(TestingBean testing) {
            this.testing = testing;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public TesterBean getTester() {
            return tester;
        }

        public void setTester(TesterBean tester) {
            this.tester = tester;
        }

        public TesteropinionBean getTesteropinion() {
            return testeropinion;
        }

        public void setTesteropinion(TesteropinionBean testeropinion) {
            this.testeropinion = testeropinion;
        }

        public List<SummaryBean> getSummary() {
            return summary;
        }

        public void setSummary(List<SummaryBean> summary) {
            this.summary = summary;
        }

        public static class InsuranceBean implements Serializable {
            /**
             * insuranceid : 100000
             * compensation : 79000
             * recording : 5
             * commercial : 天安保险公司
             * commercialnumber : 1702013081615263719***
             * commercialtime : 2013-08-16
             * compulsory : 天安保险公司
             * compulsorynumber : 1702013081657361827***
             * compulsorytime : 2013-08-16
             * insurancereporttime : 2018-04-19
             */

            private String insuranceid;
            private String compensation;
            private String recording;
            private String commercial;
            private String commercialnumber;
            private String commercialtime;
            private String compulsory;
            private String compulsorynumber;
            private String compulsorytime;
            private String insurancereporttime;

            public String getInsuranceid() {
                return insuranceid;
            }

            public void setInsuranceid(String insuranceid) {
                this.insuranceid = insuranceid;
            }

            public String getCompensation() {
                return compensation;
            }

            public void setCompensation(String compensation) {
                this.compensation = compensation;
            }

            public String getRecording() {
                return recording;
            }

            public void setRecording(String recording) {
                this.recording = recording;
            }

            public String getCommercial() {
                return commercial;
            }

            public void setCommercial(String commercial) {
                this.commercial = commercial;
            }

            public String getCommercialnumber() {
                return commercialnumber;
            }

            public void setCommercialnumber(String commercialnumber) {
                this.commercialnumber = commercialnumber;
            }

            public String getCommercialtime() {
                return commercialtime;
            }

            public void setCommercialtime(String commercialtime) {
                this.commercialtime = commercialtime;
            }

            public String getCompulsory() {
                return compulsory;
            }

            public void setCompulsory(String compulsory) {
                this.compulsory = compulsory;
            }

            public String getCompulsorynumber() {
                return compulsorynumber;
            }

            public void setCompulsorynumber(String compulsorynumber) {
                this.compulsorynumber = compulsorynumber;
            }

            public String getCompulsorytime() {
                return compulsorytime;
            }

            public void setCompulsorytime(String compulsorytime) {
                this.compulsorytime = compulsorytime;
            }

            public String getInsurancereporttime() {
                return insurancereporttime;
            }

            public void setInsurancereporttime(String insurancereporttime) {
                this.insurancereporttime = insurancereporttime;
            }
        }

        public static class CarhistoryBean implements Serializable {
            /**
             * carhistoryid : 100000
             * carhistorylevel : 1
             * summaryitems : [{"item":"重大事故","level":"3","project":"后保险杠做漆；局部钣金；"}]
             */

            private String carhistoryid;
            private String carhistorylevel;
            private List<SummaryitemsBean> summaryitems;

            public String getCarhistoryid() {
                return carhistoryid;
            }

            public void setCarhistoryid(String carhistoryid) {
                this.carhistoryid = carhistoryid;
            }

            public String getCarhistorylevel() {
                return carhistorylevel;
            }

            public void setCarhistorylevel(String carhistorylevel) {
                this.carhistorylevel = carhistorylevel;
            }

            public List<SummaryitemsBean> getSummaryitems() {
                return summaryitems;
            }

            public void setSummaryitems(List<SummaryitemsBean> summaryitems) {
                this.summaryitems = summaryitems;
            }

            public static class SummaryitemsBean implements Serializable {
                /**
                 * item : 重大事故
                 * level : 3
                 * project : 后保险杠做漆；局部钣金；
                 */

                private String item;
                private String level;
                private String project;

                public String getItem() {
                    return item;
                }

                public void setItem(String item) {
                    this.item = item;
                }

                public String getLevel() {
                    return level;
                }

                public void setLevel(String level) {
                    this.level = level;
                }

                public String getProject() {
                    return project;
                }

                public void setProject(String project) {
                    this.project = project;
                }
            }
        }

        public static class TestingBean implements Serializable {
            /**
             * testingid : 100014
             * testingitems : null
             */

            private String testingid;
            private Object testingitems;

            public String getTestingid() {
                return testingid;
            }

            public void setTestingid(String testingid) {
                this.testingid = testingid;
            }

            public Object getTestingitems() {
                return testingitems;
            }

            public void setTestingitems(Object testingitems) {
                this.testingitems = testingitems;
            }
        }

        public static class ImagesBean implements Serializable {
            /**
             * image1 : 20180604/15280939645280.jpg
             * image2 : 20180604/15280939993375.jpg
             */

            private String image1;
            private String image2;

            public String getImage1() {
                return image1;
            }

            public void setImage1(String image1) {
                this.image1 = image1;
            }

            public String getImage2() {
                return image2;
            }

            public void setImage2(String image2) {
                this.image2 = image2;
            }
        }

        public static class TesterBean implements Serializable {
            /**
             * testername : 范长江
             * testernote : AIS特级认证检测员；国家高级二手车评估师；AIS高级培训师
             * testerimage : 100022_201806131912.png
             * testercall : 18301931581
             */

            private String testername;
            private String testernote;
            private String testerimage;
            private String testercall;

            public String getTestername() {
                return testername;
            }

            public void setTestername(String testername) {
                this.testername = testername;
            }

            public String getTesternote() {
                return testernote;
            }

            public void setTesternote(String testernote) {
                this.testernote = testernote;
            }

            public String getTesterimage() {
                return testerimage;
            }

            public void setTesterimage(String testerimage) {
                this.testerimage = testerimage;
            }

            public String getTestercall() {
                return testercall;
            }

            public void setTestercall(String testercall) {
                this.testercall = testercall;
            }
        }

        public static class TesteropinionBean implements Serializable {
            /**
             * opinion : 该车经过AIS267项实地检测，A柱有明显切割痕迹，挡风玻璃更换为复牌，存在重大事故可能。需结合维修记录和出险记录进行核实。
             */

            private String opinion;

            public String getOpinion() {
                return opinion;
            }

            public void setOpinion(String opinion) {
                this.opinion = opinion;
            }
        }

        public static class SummaryBean implements Serializable {
            /**
             * item : 重大事故
             * level : 3
             * times : 1
             * image :
             */

            private String item;
            private String level;
            private String times;
            private String image;

            public String getItem() {
                return item;
            }

            public void setItem(String item) {
                this.item = item;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getTimes() {
                return times;
            }

            public void setTimes(String times) {
                this.times = times;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
}
