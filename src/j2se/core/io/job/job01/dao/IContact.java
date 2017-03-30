package j2se.core.io.job.job01.dao;

import j2se.core.io.job.job01.entity.Contact;

import java.util.List;

public interface IContact {
    public Contact getContactById(int id);

    public List<Contact> getAllContacts();

    public void addContact(Contact contact);

    public void saveContacts(List<Contact> contacts);

    public String readAll();

    public List<Contact> getAll();
}
