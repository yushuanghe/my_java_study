package j2se.core.annotation.demo01;


import java.lang.reflect.Field;

/**
 * Created by yobdc on 2017/01/17.
 */
public class ToStringUtil {
    public static String annotationToString(Object object) {
        StringBuffer result = new StringBuffer("");

        Class<?> clazz = object.getClass();

        /**
         * 得到类的注解
         */
        boolean hasClassName = clazz.isAnnotationPresent(ClassName.class);
        if (hasClassName) {
            ClassName className = clazz.getAnnotation(ClassName.class);
            result.append(className.value() + "=[");
        } else {
            result.append(clazz.getSimpleName() + "=[");
        }

        /**
         * 得到field的注解
         */
        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            boolean hasAnnotation = field.isAnnotationPresent(FieldName.class);
            if (hasAnnotation) {
                FieldName fieldName = field.getAnnotation(FieldName.class);
                try {
                    result.append(fieldName.value() + ":" + field.get(object));

                    if (i < (fields.length - 1)) {
                        result.append(",");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        result.append("]");

        return result.toString();
    }
}
