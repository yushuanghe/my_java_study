package com.shuanghe.j2se.core.reflection.job02;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * java bean反射生成对象工具类
 * Created by yobdc on 2017/01/18.
 */
public class Tool {
    public static Object setValue(Map<String, Object> map, Class<?> clazz) {
        Object object = null;
        try {
            object = clazz.newInstance();

            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = map.get(fieldName);

                if (value != null) {
                    String methodName = String.format("set%s%s",
                            fieldName.substring(0, 1).toUpperCase(),
                            fieldName.substring(1));

                    Class<?>[] ps = new Class[1];
                    ps[0] = field.getType();

                    Method method = clazz.getMethod(methodName, ps);
                    method.invoke(object, value);
                }
            }
            return object;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Object setValue2(Map<String, Object> map, Class<?> clazz) {
        Object object = null;

        try {
            object = clazz.newInstance();

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);

                String fieldName = field.getName();
                Object value = map.get(fieldName);
                if (value != null) {
                    field.set(object, value);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }
}
