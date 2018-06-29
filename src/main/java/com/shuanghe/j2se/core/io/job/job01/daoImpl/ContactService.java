package com.shuanghe.j2se.core.io.job.job01.daoImpl;

import com.shuanghe.j2se.core.io.job.job01.dao.IContact;
import com.shuanghe.j2se.core.io.job.job01.dao.IXmlParser;
import com.shuanghe.j2se.core.io.job.job01.entity.Contact;
import com.shuanghe.j2se.core.io.job.job01.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yobdc on 2016/12/14.
 */
public class ContactService implements IContact {

    private FileUtil fileUtil = null;
    private static final String FILENAME = "xml/contact.xml";
    private List<Contact> contacts = null;

    private IXmlParser xmlParser = null;

    static {
        System.out.println("执行静态块！");
        File file = new File(FILENAME);
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
    }

    public ContactService(String str) throws IOException {
        System.out.println("执行构造方法！");
        fileUtil = new FileUtil(FILENAME);
        fileUtil.writeLine("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
        fileUtil.writeLine("<contacts>");
        contacts = new ArrayList<Contact>();

        if (str.equals("dom"))
            xmlParser = new DomParser();
        else if (str.equals("sax"))
            xmlParser = new SaxParserMain();
    }

    public ContactService() throws IOException {
        System.out.println("执行构造方法！");
        fileUtil = new FileUtil(FILENAME);
        fileUtil.writeLine("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
        fileUtil.writeLine("<contacts>");
        contacts = new ArrayList<Contact>();

        xmlParser = new DomParser();
    }

    public void setXmlParser(IXmlParser parser) {
        this.xmlParser = parser;
    }

    @Override
    public Contact getContactById(int id) {
        return null;
    }

    @Override
    public List<Contact> getAllContacts() {
        return this.contacts;
    }

    @Override
    public void addContact(Contact contact) {
        //每次写一行比较慢，使用StringBuilder将内容全部准备好再写入

//        fileUtil.writeLine("<contacts>");
//        fileUtil.writeLine(String.format("<contact id=\"%s\">", contact.getId()));

        //重写对象的toString方法
//        fileUtil.writeLine(contact.toString());

        contacts.add(contact);

    }

    @Override
    public void saveContacts(List<Contact> contacts) {
        for (Contact contact : contacts) {
            fileUtil.writeLine(contact.toString());
        }
        fileUtil.writeLine("</contacts>");
    }

    @Override
    public String readAll() {
        return fileUtil.readAll();
    }

    @Override
    public List<Contact> getAll() {
        return xmlParser.parse(FILENAME);
    }
}
