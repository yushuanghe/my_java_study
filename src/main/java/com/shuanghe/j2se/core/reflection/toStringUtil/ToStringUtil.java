package com.shuanghe.j2se.core.reflection.toStringUtil;

import java.lang.reflect.Field;

/**
 * Created by yobdc on 2017/01/13.
 */
public class ToStringUtil {
    public static String toString(Object obj) {
        StringBuffer result = new StringBuffer("");

        if (obj == null)
            return null;

        Class<?> clz = obj.getClass();
//        String name = clz.getName().substring(clz.getName().lastIndexOf(".") + 1);
        String name = clz.getSimpleName();
        result.append(name + ":[");

        Field[] fields = clz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            try {
                result.append(field.getName() + " = " + field.get(obj));

                if (i < (fields.length - 1))
                    result.append(", ");

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        result.append("]");

        return result.toString();
    }
}
