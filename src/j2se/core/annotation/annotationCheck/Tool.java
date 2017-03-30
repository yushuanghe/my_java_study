package j2se.core.annotation.annotationCheck;

import java.lang.reflect.Field;

/**
 * Created by yobdc on 2017/01/17.
 */
public class Tool {
    public static boolean checkInput(String fieldName, Object value, Object object) {
        Class<?> clazz = object.getClass();

        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);

            boolean hasAnnotation = field.isAnnotationPresent(Check.class);
            if (hasAnnotation) {
                Check anno = field.getAnnotation(Check.class);
                boolean request = anno.request();
                String range = anno.range();

                if (request) {
                    if (value == null || String.valueOf(value).equals("")) {
                        throw new RuntimeException(field.getName() + "该字段不允许为空");
                    }
                } else {
                    //非强制性校验，如果为null不进行校验
                    if (value != null) {
                        String[] strs = range.split("-");
                        if (strs.length == 2) {
                            double min = Double.valueOf(strs[0]);
                            double max = Double.valueOf(strs[1]);

                            double price = Double.valueOf(String.valueOf(value));

                            if (price < min || price > max) {
                                throw new RuntimeException(field.getName() + "价格不符合标准");
                            }
                        }
                    }
                }
            } else {
                return true;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return true;
    }
}
