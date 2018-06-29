package com.shuanghe.j2se.core.io.xml.sax.handler;

import com.shuanghe.j2se.core.io.xml.sax.bean.Dog;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * sax方式解析xml
 * Created by yobdc on 2016/12/15.
 */
public class PetSaxParser extends DefaultHandler {
    //最终返回list
    private List<Dog> dogs = null;
    private Dog dog = null;
    private PetEnumState state = PetEnumState.none;

    public List<Dog> getDogs() {
        return this.dogs;
    }

    @Override
    public void startDocument() throws SAXException {
        dogs = new ArrayList<Dog>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //qName：当前读取到的标签名称
        if (qName.equals("dog")) {
            dog = new Dog();

            //开始读取属性id
            //attributes：标签的属性
            dog.setId(Integer.parseInt(attributes.getValue("id")));
            return;
        }

        if (qName.equals("name")) {
            state = PetEnumState.name;
            return;
        }

        if (qName.equals("health")) {
            state = PetEnumState.health;
            return;
        }

        if (qName.equals("love")) {
            state = PetEnumState.love;
            return;
        }

        if (qName.equals("strain")) {
            state = PetEnumState.strain;
            return;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //无值的标签 characters 事件直接跳过
        if (state == PetEnumState.none)
            return;

        String context = new String(ch, start, length);

        switch (state) {
            case name:
                dog.setName(context);
                //赋值完毕，将状态取消
                state = PetEnumState.none;
                break;
            case health:
                dog.setHealth(Integer.parseInt(context));
                state = PetEnumState.none;
                break;
            case love:
                dog.setLove(Integer.parseInt(context));
                state = PetEnumState.none;
                break;
            case strain:
                dog.setStrain(context);
                state = PetEnumState.none;
                break;
            default:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("dog")) {
            dogs.add(dog);
            dog = null;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
