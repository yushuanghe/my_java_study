package com.shuanghe.j2se.core.annotation.job;


import java.lang.annotation.*;

/**
 * Created by yobdc on 2017/01/17.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TestAnno {
    public int id();

    public String description() default "no description";
}
