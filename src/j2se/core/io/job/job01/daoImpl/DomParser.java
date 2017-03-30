package j2se.core.io.job.job01.daoImpl;

import j2se.core.io.job.job01.dao.IXmlParser;
import j2se.core.io.job.job01.entity.Contact;
import j2se.core.io.job.job01.entity.Group;
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
 * Created by yobdc on 2016/12/16.
 */
public class DomParser implements IXmlParser {
    public List<Contact> parse(String filename) {
        List<Contact> contacts = new ArrayList<>();

        DocumentBuilderFactory builderFactory = null;
        DocumentBuilder documentBuilder = null;
        Document document = null;
        try {
            builderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = builderFactory.newDocumentBuilder();
            document = documentBuilder.parse(new File(filename));

            Element root = document.getDocumentElement();
            NodeList contactList = root.getElementsByTagName("contact");
            for (int i = 0; i < contactList.getLength(); i++) {
                Node contactNode = contactList.item(i);
                Contact contact = new Contact();

                contact.setId(Integer.parseInt(contactNode.getAttributes().getNamedItem("id").getTextContent()));

                NodeList childContacts = contactNode.getChildNodes();
                for (int j = 0; j < childContacts.getLength(); j++) {
                    Node childContact = childContacts.item(j);
                    if (childContact.getNodeName().equals("name"))
                        contact.setRealName(childContact.getTextContent());
                    else if (childContact.getNodeName().equals("mobile"))
                        contact.setMobile(childContact.getTextContent());
                    else if (childContact.getNodeName().equals("group")) {
                        Group group = new Group();
                        NodeList childGroups = childContact.getChildNodes();
                        for (int k = 0; k < childGroups.getLength(); k++) {
                            Node childGroup = childGroups.item(k);
                            if (childGroup.getNodeName().equals("id"))
                                group.setId(Integer.parseInt(childGroup
                                        .getTextContent()));
                            else if (childGroup.getNodeName().equals("name"))
                                group.setName(childGroup.getTextContent());
                        }
                        contact.setGroup(group);
                    }
                }
                contacts.add(contact);
            }
            return contacts;
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
