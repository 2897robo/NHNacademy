package com.nhnacademy.app;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public class StringCheck {
    public static void main(String[] args) {
        String str1 = null;
        String str2 = "";
        String str3 = "Hello";

        boolean isStr1Empty = Objects.isNull(str1) || str1.isEmpty(); // true
        boolean isStr2Empty = Objects.isNull(str2) || str2.isEmpty(); // true
        boolean isStr3Empty = StringUtils.isEmpty(str3); // false

        System.out.println("str1 is empty (isEmpty 메소드): " + isStr1Empty);
        System.out.println("str2 is empty: (Objects.isNull 메소드)" + isStr2Empty);
        System.out.println("str3 is empty: (StringUtils.isEmpty 메소드)" + isStr3Empty);
    }
}