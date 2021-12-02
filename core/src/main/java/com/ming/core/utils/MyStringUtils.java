package com.ming.core.utils;

import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;

/**
 * my string utils
 *
 * @author ming
 * @date 2021-07-19 14:35
 */
public class MyStringUtils {

    public static String toLowerCaseJoiner(String str) {
        return toLowerCaseJoiner(str, "-");
    }


    public static String toLowerCaseJoiner(String str, String Joiner) {
        if (str == null || "".equals(str)) {
            return "";
        }
        str = toLowerCaseFirstOne(str);
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append(Joiner);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * @author ming
     * @date 2021-07-19 15:27
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return Character.toLowerCase(s.charAt(0)) + s.substring(1);
        }
    }

    /**
     * 将 byte 数组转换为 String 用utf8编码
     *
     * @param bytes 二进制数组
     * @return String 返回的字符串
     * @author ming
     * @date 2019-09-04 17:31:46
     */
    public static @NotNull
    String valueOfByUtf8(@NotNull byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }


    /**
     * 获取字符串中的大写字母 字符串
     *
     * @param str 参数字符串
     * @return String
     * @author ming
     * @date 2019-09-18 13:36:22
     */
    public static @NotNull
    String getUpperCaseChar(@NotNull String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }


}
