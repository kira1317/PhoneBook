package phone.book.service;

import phone.book.model.Contact;


import java.util.Collection;

/**
 * Created by Администратор on 20.07.15.
 */
public interface PhoneBookService  {

    boolean createNewContact(Contact contact);
    boolean editContact(Contact contact);
    Contact deleteContact(int id);
    Collection<Contact> getAllContacts();

    Collection<Contact> searchContactsByFirstOrLastName(String s);
    Collection<Contact> searchContactsByanyPartOfName(String s);
    Collection<Contact> searchContactsByPhoneNumber(String s);

    Collection<Contact> searchContactsByAge(int ags);
    Collection<Contact> searchContactsByAge(int from,int to);

   Contact searchContact(int id);


}
