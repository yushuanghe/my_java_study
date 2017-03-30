package j2se.core.annotation;

import java.lang.annotation.*;

/**
 * 定义注解 Test
 * 注解中含有两个元素：id，description
 * description的默认值为：no description
 * Created by yobdc on 2017/01/17.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Test {
    public int id();

    public String description() default "no description";
}
