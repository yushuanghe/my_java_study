package com.shuanghe.j2se.core.io.job.job01.view;

import com.shuanghe.j2se.core.io.job.job01.biz.ContactManager;
import com.shuanghe.j2se.core.io.job.job01.entity.Contact;
import com.shuanghe.j2se.core.io.job.job01.entity.Group;

import java.io.IOException;
import java.util.List;

/**
 * Created by yobdc on 2016/12/14.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        ContactManager biz = null;
        try {
            biz = new ContactManager();
//            biz = new ContactManager("dom");
//            biz = new ContactManager("sax");
            Group group1 = new Group(1, "好友");
            Group group2 = new Group(2, "同事");
            Contact contact1 = new Contact(1, "大力", "123456789", group1);
            Contact contact2 = new Contact(2, "二力", "987654321", group2);
            Contact contact3 = new Contact(3, "三力", "88479527", group2);
            biz.addContact(contact1);
            biz.addContact(contact2);
            biz.addContact(contact3);
            List<Contact> contacts = biz.getAllContacts();
            biz.save(contacts);

            test2(biz);
            System.out.println("================");
            test3(biz);

            Runtime.getRuntime().addShutdownHook(new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("钩子！");
                        }
                    }
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test1(ContactManager biz) {
        System.out.println("读数据");
        System.out.println(biz.readAll());
    }

    /**
     * dom解析
     *
     * @param biz
     */
    public static void test2(ContactManager biz) {
        System.out.println("dom解析！");
        List<Contact> contacts1 = biz.getAll();
        for (Contact c : contacts1) {
            System.out.println(c.toString());
        }
    }

    /**
     * sax解析
     *
     * @param biz
     */
    public static void test3(ContactManager biz) {
        System.out.println("sax解析！");
        List<Contact> list = biz.getAll();
        for (Contact c : list) {
            System.out.println(c.toString());
        }
    }
}
