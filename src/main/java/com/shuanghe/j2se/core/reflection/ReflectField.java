package com.shuanghe.j2se.core.reflection;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by yobdc on 2017/01/12.
 */
public class ReflectField {
    public static void main(String[] args) {
        Person person = new Person(1001, "大力", 40);
        Class<?> clz = person.getClass();

        try {
            Field fId = clz.getField("id");
            System.out.println(fId);

            //得到person对象的id属性
            int id = fId.getInt(person);
            System.out.println(id);

            fId.set(person, 20);
            System.out.println("id:" + person.getId());

//            Field fName = clz.getField("name");
//            //java.lang.NoSuchFieldException: name
//            Object name = fName.get(person);

            Field fName = clz.getDeclaredField("name");
            fName.setAccessible(true);
            Object name = fName.get(person);

            System.out.println(name);

            System.out.println("==============");
            /**
             * 获取所有属性
             */
            Field[] fields = clz.getDeclaredFields();
            for (Field f : fields) {
                System.out.println(f);
            }

            System.out.println("==============");
            Field fTAG = clz.getDeclaredField("TAG");
            fTAG.setAccessible(true);
            System.out.println("是否为静态属性：" + Modifier.isStatic(fTAG.getModifiers()));
            System.out.println("获取属性值：" + fTAG.get(null));
            System.out.println("设置属性值");
            fTAG.set(null, "大力出奇迹！");
            System.out.println(Person.getTAG());

            System.out.println("==============");
            clz.getSuperclass();

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
