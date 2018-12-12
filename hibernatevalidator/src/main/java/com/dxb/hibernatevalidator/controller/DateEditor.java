package com.dxb.hibernatevalidator.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p><strong>类名: </strong></p>DateEditor <br/>
 * <p><strong>功能说明: </strong></p>日期数据自动转换类. <br/>
 *
 * @since JDK 1.8
 */
public class DateEditor extends PropertyEditorSupport {
    public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void setAsText(String text) {
        setValue(parseString(text));
    }

    private Object parseString(String text) {
        Date date = null;
        if (null != text && !"".equals(text)) {
            try {
                dateTimeFormat.setLenient(true);
                date = dateTimeFormat.parse(text);
            } catch (ParseException e) {
//                e.printStackTrace();
                try {
                    dateFormat.setLenient(true);
                    date = dateFormat.parse(text);
                } catch (ParseException e1) {
//                    e1.printStackTrace();
                }
            }
        }
        System.out.println(text + "::::" + dateTimeFormat.format(date));
        return date;
    }
}
