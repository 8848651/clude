package com.base.xczx_plus_base.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneUtil {
    public static Boolean isMatches(String phone){
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}
