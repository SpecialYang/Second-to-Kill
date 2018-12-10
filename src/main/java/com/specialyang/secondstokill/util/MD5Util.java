package com.specialyang.secondstokill.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * Created by Fan Yang in 2018/12/6 8:31 AM.
 */
public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt =  "1a2b3c4d";

    public static String generatorRandomSalt() {
        return UUID.randomUUID().toString().substring(0, 6);
    }

    public static String inputPassToFormPass(String inputPass) {
        String src = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(src);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String src = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(src);
    }

    public static String inputPassToDBPass(String input, String saltDB) {
        String formPass = inputPassToFormPass(input);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFormPass("123456"));
        String randomSalt = generatorRandomSalt();
        System.out.println(randomSalt);
        System.out.println(formPassToDBPass("123456", randomSalt));
    }
}
