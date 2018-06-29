package com.shuanghe.j2se.core.io.job.job01.daoImpl;

import com.shuanghe.j2se.core.io.job.job01.entity.Contact;
import com.shuanghe.j2se.core.io.job.job01.entity.Group;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yobdc on 2016/12/16.
 */
public class SaxParserHandler extends DefaultHandler {
    private List<Contact> contacts = null;
    private ContactsEnumState contactsState = ContactsEnumState.none;
    private ContactEnumState state = ContactEnumState.none;
    private GroupEnumState groupState = GroupEnumState.none;

    private Contact contact = null;
    private Group group = null;

    public List<Contact> getStudents() {
        return this.contacts;
    }

    @Override
    public void startDocument() throws SAXException {
        contacts = new ArrayList<Contact>();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if (qName.equals("contact")) {
            contact = new Contact();
            contact.setId(Integer.parseInt(attributes.getValue("id")));
            contactsState = ContactsEnumState.contact;
            return;
        }

        if (qName.equals("name")) {
            if (state == ContactEnumState.group)
                groupState = GroupEnumState.name;
            else
                state = ContactEnumState.realName;
            return;
        }

        if (qName.equals("mobile")) {
            state = ContactEnumState.mobile;
            return;
        }

        if (qName.equals("group")) {
            state = ContactEnumState.group;
            group = new Group();
            return;
        }

        if (qName.equals("id")) {
            groupState = GroupEnumState.id;
            return;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (contactsState == ContactsEnumState.none)
            return;

        String context = new String(ch, start, length);

        switch (state) {
            case realName:
                if (contactsState == ContactsEnumState.contact && groupState == GroupEnumState.none) {
                    contact.setRealName(context);
                    state = ContactEnumState.none;
                }
                break;
            case mobile:
                if (contactsState == ContactsEnumState.contact && groupState == GroupEnumState.none) {
                    contact.setMobile(context);
                    state = ContactEnumState.none;
                }
                break;
            case group:
                if (contactsState == ContactsEnumState.contact) {
                    switch (groupState) {
                        case id:
                            group.setId(Integer.parseInt(context));
                            groupState = GroupEnumState.none;
                            break;
                        case name:
                            group.setName(context);
                            groupState = GroupEnumState.none;
                            break;
                        default:
                            break;
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("group")) {
            contact.setGroup(group);
            group = null;
            groupState = GroupEnumState.none;
            state = ContactEnumState.none;
            return;
        }

        if (qName.equals("contact")) {
            contacts.add(contact);
            contact = null;
            state = ContactEnumState.none;
        }

        if (qName.equals("contacts")) {
            contactsState = ContactsEnumState.none;
        }
    }
}
