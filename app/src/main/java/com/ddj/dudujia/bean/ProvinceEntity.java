package com.ddj.dudujia.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hanshaobo on 2016/11/22.
 */
public class ProvinceEntity implements Serializable{


    public ArrayList<Province> province;

    public static class Province {
        /**
         *"name": "沪",
         * "letter": "H",
         *"province": "上海市"
         */

        public String letter;
        public String province;
        public String name;
        public String simple;
    }
}
