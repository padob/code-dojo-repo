package com.pdb.utils;

import static java.util.Objects.isNull;

public class StringUtils {

    public static boolean isBlank(String content) {
        return isNull(content) || StringUtils.isEmpty(content);
    }

    public static boolean isEmpty(String content) {
        return content.trim().length() == 0;
    }
}
