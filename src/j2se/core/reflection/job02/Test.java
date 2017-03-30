package j2se.core.reflection.job02;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yobdc on 2017/01/18.
 */
public class Test {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("id", "1001");
        map.put("name", "张三");

        Student student = (Student) Tool.setValue(map, Student.class);
        System.out.println(student);

        map.clear();

        map.put("teacherId", 001);
        map.put("teacherName", "王老师");
        map.put("duty", "班主任");

        Teacher teacher = (Teacher) Tool.setValue(map, Teacher.class);
        System.out.println(teacher);
    }
}
