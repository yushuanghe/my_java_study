package com.shuanghe.j2se.core.io.job.job01.daoImpl;

import com.shuanghe.j2se.core.io.job.job01.dao.IXmlParser;
import com.shuanghe.j2se.core.io.job.job01.entity.Contact;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

/**
 * Created by yobdc on 2016/12/16.
 */
public class SaxParserMain implements IXmlParser {
    public List<Contact> parse(String filename) {
        List<Contact> list = null;

        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = parserFactory.newSAXParser();
            SaxParserHandler handler = new SaxParserHandler();
            saxParser.parse(filename, handler);
            list = handler.getStudents();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
