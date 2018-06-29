package com.shuanghe.j2se.core.io.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * dom方式解析xml
 * Created by yobdc on 2016/12/15.
 */
public class PetDomParser {
    public static List<Dog> parse(String filename) {
        List<Dog> dogs = new ArrayList<>();
        DocumentBuilderFactory builderFactory = null;
        DocumentBuilder documentBuilder = null;
        Document doc = null;
        try {
            //单例模式
            builderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = builderFactory.newDocumentBuilder();
            doc = documentBuilder.parse(new File(filename));

            //得到根节点
            Element root = doc.getDocumentElement();

            NodeList doglist = root.getElementsByTagName("dog");
            for (int i = 0; i < doglist.getLength(); i++) {
                //遍历doglist，得到子节点
                Node node = doglist.item(i);

                Dog dog = new Dog();
                //获取属性
                int id = Integer.parseInt(node.getAttributes()
                        .getNamedItem("id").getTextContent());
                dog.setId(id);
                //获取子标签
                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node childNode = childNodes.item(j);
                    if (childNode.getNodeName().equals("name"))
                        dog.setName(childNode.getTextContent());
                    else if (childNode.getNodeName().equals("health"))
                        dog.setHealth(Integer.parseInt(childNode.getTextContent()));
                    else if (childNode.getNodeName().equals("love"))
                        dog.setLove(Integer.parseInt(childNode.getTextContent()));
                    else if (childNode.getNodeName().equals("strain"))
                        dog.setStrain(childNode.getTextContent());
                    else if (childNode.getNodeName().equals("master")) {
                        Master master = new Master();
                        NodeList childMasters = childNode.getChildNodes();
                        for (int k = 0; k < childMasters.getLength(); k++) {
                            Node masterChild = childMasters.item(k);
                            if (masterChild.getNodeName().equals("id"))
                                master.setId(Integer.parseInt(masterChild.getTextContent()));
                            else if (masterChild.getNodeName().equals("name"))
                                master.setName(masterChild.getTextContent());
                            else if (masterChild.getNodeName().equals("phone"))
                                master.setPhone(masterChild.getTextContent());
                        }
                        dog.setMaster(master);
                    }
                }
                dogs.add(dog);
            }
            return dogs;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        String filename = "xml/pet1.xml";
        List<Dog> dogs = parse(filename);
        for (Dog d : dogs) {
            System.out.println(d.toString());
        }
    }
}
