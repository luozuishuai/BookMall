package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author luozuishuai
 * @Created on 2020-11-25 20:30
 */
public class WebUtils {
    public static <T> T copyParamToBean(Map map, T bean) {
        try {
            BeanUtils.populate(bean, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String strInt, int defaultValue) {
        if (strInt != null) {
            try {
                return Integer.parseInt(strInt);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }
}
