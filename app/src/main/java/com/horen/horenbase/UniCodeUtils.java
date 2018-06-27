package com.horen.horenbase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author :ChenYangYi
 * @date :2018/06/27/14:28
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class UniCodeUtils {
    /**
     * Unicode转 汉字字符串
     *
     * @param str \u6728
     * @return '木' 26408
     */
    public static String unicodeToString(String str) {

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            //group 6728
            String group = matcher.group(2);
            //ch:'木' 26408
            ch = (char) Integer.parseInt(group, 16);
            //group1 \u6728
            String group1 = matcher.group(1);
            str = str.replace(group1, ch + "");
        }
        return str;
    }
}
