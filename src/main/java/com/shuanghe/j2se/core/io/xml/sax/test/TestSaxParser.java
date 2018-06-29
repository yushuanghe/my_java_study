package com.shuanghe.j2se.core.io.xml.sax.test;

import com.shuanghe.j2se.core.io.xml.sax.bean.Dog;
import com.shuanghe.j2se.core.io.xml.sax.handler.PetSaxParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

/**
 * Created by yobdc on 2016/12/15.
 */
public class TestSaxParser {
    public static void main(String[] args) {
        //单例模式
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            PetSaxParser saxParser = new PetSaxParser();
            //开始解析
            parser.parse("xml/pet.xml", saxParser);

            List<Dog> list = saxParser.getDogs();
            for (Dog d : list) {
                System.out.println(d.toString());
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
