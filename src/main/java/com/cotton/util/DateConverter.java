package com.cotton.util;


import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * Created by hp on 2016/12/21.
 */
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        Date dt = null;
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        try {
            dt = DateTime.parse(source.trim(), format).toDate();
        } catch (Exception e) {
            e.getMessage();
            format = DateTimeFormat.forPattern("yyyy-MM-dd");
            dt = DateTime.parse(source.trim(), format).toDate();
        }
        return dt;
    }

}
