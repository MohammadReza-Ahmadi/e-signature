package com.vsq.esignature.enumeration;

/**
 * author: MohammadReza Ahmadi  11.08.20
 * email: m.reza79ahmadi@gmail.com
 */


public class AppEnums {

    public enum GENDER{
        MALE(0), FEMALE(1);

        private int code;

        GENDER(int code) {
            this.code = code;
        }
    }

}
