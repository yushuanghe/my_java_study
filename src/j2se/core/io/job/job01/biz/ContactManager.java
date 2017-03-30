package j2se.core.io.job.job01.biz;

import j2se.core.io.job.job01.dao.IContact;
import j2se.core.io.job.job01.daoImpl.ContactService;
import j2se.core.io.job.job01.entity.Contact;

import java.io.IOException;
import java.util.List;

/**
 * Created by yobdc on 2016/12/14.
 */
public class ContactManager {
    private IContact dao;

    public ContactManager(String str) throws IOException {
        dao = new ContactService(str);
    }

    public ContactManager() throws IOException {
        dao = new ContactService();
    }

    public Contact getContactById(int id) {
        return dao.getContactById(id);
    }

    public List<Contact> getAllContacts() {
        return dao.getAllContacts();
    }

    public void addContact(Contact contact) {
        dao.addContact(contact);
    }

    public void save(List<Contact> contacts) {
        dao.saveContacts(contacts);
    }

    public String readAll() {
        return dao.readAll();
    }

    public List<Contact> getAll() {
        return dao.getAll();
    }
}
