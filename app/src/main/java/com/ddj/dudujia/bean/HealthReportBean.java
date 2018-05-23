package com.ddj.dudujia.bean;

import java.util.List;

public class HealthReportBean {

    /**
     * data : {"cjmc":"奥迪汽车","pp":"奥迪","cx":"A8L","vin":"ZXCVBNMASDFGHJKLW","license":"沪A00001","mileage":"3000","color":"红色","emission":"国5","displacement":"3.0T","productionyear":"2012","summary":[{"item":"重大事故","level":"3","times":"3"},{"item":"非火烧","level":"1","times":""},{"item":"非泡水","level":"1","times":""},{"item":"里程有风险","level":"1","times":""},{"item":"大额理赔","level":"2","times":"1"},{"item":"过户次数","level":"2","times":"2"}],"insurance":{"insuranceid":"100000","compensation":"75000","recording":"5","commercial":"天安保险公司","commercialnumber":"190099009090","commercialtime":"2019-5-30","compulsory":"天安保险公司","compulsorynumber":"180099001231","compulsorytime":"2019-5-30","insurancereporttime":"2018-5-23"},"carhistory":{"carhistoryid":"100000","summaryitems":[{"item":"重大事故","level":"3","project":"A柱切割；"},{"item":"重大事故","level":"3","project":"后保险杠做漆；局部钣金；"},{"item":"一般事故","level":"2","project":"前保险杠做漆；拆装前保险杠及中网；"}]},"testing":{"testingid":"100000","testingitems":[{"item":"前保险杠","level":"2","project":"重新喷漆；划痕；凹陷；"},{"item":"前挡风玻璃","level":"2","project":"断裂；"},{"item":"后保险杠","level":"2","project":"重新喷漆；凹陷；"},{"item":"引擎盖","level":"2","project":"重新喷漆；凹陷；褪色；"}]},"images":{"image1":"","image2":""}}
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
         * cjmc : 奥迪汽车
         * pp : 奥迪
         * cx : A8L
         * vin : ZXCVBNMASDFGHJKLW
         * license : 沪A00001
         * mileage : 3000
         * color : 红色
         * emission : 国5
         * displacement : 3.0T
         * productionyear : 2012
         * summary : [{"item":"重大事故","level":"3","times":"3"},{"item":"非火烧","level":"1","times":""},{"item":"非泡水","level":"1","times":""},{"item":"里程有风险","level":"1","times":""},{"item":"大额理赔","level":"2","times":"1"},{"item":"过户次数","level":"2","times":"2"}]
         * insurance : {"insuranceid":"100000","compensation":"75000","recording":"5","commercial":"天安保险公司","commercialnumber":"190099009090","commercialtime":"2019-5-30","compulsory":"天安保险公司","compulsorynumber":"180099001231","compulsorytime":"2019-5-30","insurancereporttime":"2018-5-23"}
         * carhistory : {"carhistoryid":"100000","summaryitems":[{"item":"重大事故","level":"3","project":"A柱切割；"},{"item":"重大事故","level":"3","project":"后保险杠做漆；局部钣金；"},{"item":"一般事故","level":"2","project":"前保险杠做漆；拆装前保险杠及中网；"}]}
         * testing : {"testingid":"100000","testingitems":[{"item":"前保险杠","level":"2","project":"重新喷漆；划痕；凹陷；"},{"item":"前挡风玻璃","level":"2","project":"断裂；"},{"item":"后保险杠","level":"2","project":"重新喷漆；凹陷；"},{"item":"引擎盖","level":"2","project":"重新喷漆；凹陷；褪色；"}]}
         * images : {"image1":"","image2":""}
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
        private InsuranceBean insurance;
        private CarhistoryBean carhistory;
        private TestingBean testing;
        private ImagesBean images;
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

        public List<SummaryBean> getSummary() {
            return summary;
        }

        public void setSummary(List<SummaryBean> summary) {
            this.summary = summary;
        }

        public static class InsuranceBean {
            /**
             * insuranceid : 100000
             * compensation : 75000
             * recording : 5
             * commercial : 天安保险公司
             * commercialnumber : 190099009090
             * commercialtime : 2019-5-30
             * compulsory : 天安保险公司
             * compulsorynumber : 180099001231
             * compulsorytime : 2019-5-30
             * insurancereporttime : 2018-5-23
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

        public static class CarhistoryBean {
            /**
             * carhistoryid : 100000
             * summaryitems : [{"item":"重大事故","level":"3","project":"A柱切割；"},{"item":"重大事故","level":"3","project":"后保险杠做漆；局部钣金；"},{"item":"一般事故","level":"2","project":"前保险杠做漆；拆装前保险杠及中网；"}]
             */

            private String carhistoryid;
            private List<SummaryitemsBean> summaryitems;

            public String getCarhistoryid() {
                return carhistoryid;
            }

            public void setCarhistoryid(String carhistoryid) {
                this.carhistoryid = carhistoryid;
            }

            public List<SummaryitemsBean> getSummaryitems() {
                return summaryitems;
            }

            public void setSummaryitems(List<SummaryitemsBean> summaryitems) {
                this.summaryitems = summaryitems;
            }

            public static class SummaryitemsBean {
                /**
                 * item : 重大事故
                 * level : 3
                 * project : A柱切割；
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

        public static class TestingBean {
            /**
             * testingid : 100000
             * testingitems : [{"item":"前保险杠","level":"2","project":"重新喷漆；划痕；凹陷；"},{"item":"前挡风玻璃","level":"2","project":"断裂；"},{"item":"后保险杠","level":"2","project":"重新喷漆；凹陷；"},{"item":"引擎盖","level":"2","project":"重新喷漆；凹陷；褪色；"}]
             */

            private String testingid;
            private List<TestingitemsBean> testingitems;

            public String getTestingid() {
                return testingid;
            }

            public void setTestingid(String testingid) {
                this.testingid = testingid;
            }

            public List<TestingitemsBean> getTestingitems() {
                return testingitems;
            }

            public void setTestingitems(List<TestingitemsBean> testingitems) {
                this.testingitems = testingitems;
            }

            public static class TestingitemsBean {
                /**
                 * item : 前保险杠
                 * level : 2
                 * project : 重新喷漆；划痕；凹陷；
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

        public static class ImagesBean {
            /**
             * image1 :
             * image2 :
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

        public static class SummaryBean {
            /**
             * item : 重大事故
             * level : 3
             * times : 3
             */

            private String item;
            private String level;
            private String times;

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
        }
    }
}
