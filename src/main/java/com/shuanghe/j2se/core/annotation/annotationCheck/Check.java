package com.shuanghe.j2se.core.annotation.annotationCheck;

import java.lang.annotation.*;

/**
 * Created by yobdc on 2017/01/17.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Check {
    public boolean request();

    public String range() default "";
}
