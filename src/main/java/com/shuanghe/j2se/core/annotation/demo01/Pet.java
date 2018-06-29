package com.shuanghe.j2se.core.annotation.demo01;

/**
 * Created by yobdc on 2017/01/17.
 */
public class Pet {
    @FieldName("物种")
    public String type;
    @FieldName("昵称")
    public String name;
    @FieldName("公母")
    public boolean sex;
    @FieldName("年龄")
    public int age;

    @Override
    public String toString() {
        return ToStringUtil.annotationToString(this);
    }
}
