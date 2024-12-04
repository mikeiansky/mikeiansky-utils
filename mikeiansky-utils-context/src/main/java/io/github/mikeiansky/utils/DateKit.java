package io.github.mikeiansky.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

/**
 * @author mike ian
 * @date 2024/12/3
 * @desc 日期工具
 **/
public class DateKit {

    public static boolean beforeNow(String dateStr, String pattern) {
        return DateUtil.compare(new DateTime(), DateUtil.parse(dateStr, pattern)) >= 0;
    }

}
