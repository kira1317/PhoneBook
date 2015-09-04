package phone.book.service;

import phone.book.model.Contact;
import phone.book.model.Phone;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Администратор on 20.07.15.
 */
public class PhoneBookServiceImpl implements PhoneBookService, Serializable {

    private Map<Integer, Contact> phoneBook = new HashMap<>();


    public int getSize(){

        return phoneBook.size();
    }

    @Override
    public boolean createNewContact(Contact contact) {


        if (searchContact(contact.getId()) == null) {

              phoneBook.put(contact.getId(),contact);
              return true;

        }

        return false;
    }

    @Override
    public boolean editContact(Contact contact) {



        if (searchContact(contact.getId()) != null) {


                    phoneBook.put(contact.getId(), contact);

                    return true;


        }

        System.out.print("Not edit contact or not this contact");


        return false;

    }
    @Override
       public Contact searchContact(int id) {


        if (phoneBook.get(id) != null) {

                    return phoneBook.get(id);


        }
           return null;

    }



    @Override
    public Contact deleteContact(int id) {

        Contact contact=searchContact(id);
        if (contact != null) {

                    phoneBook.remove(contact.getId());

                    return contact;



        }

        System.out.print("Not delete contact");


        return null;

    }

    @Override
    public Collection<Contact> getAllContacts() {

        return phoneBook.values();
    }

    @Override
    public Collection<Contact> searchContactsByFirstOrLastName(String s) {

        Collection<Contact> result = new ArrayList<Contact>();

        for (Contact contact : phoneBook.values()) {

                if (contact.getlastName().equals(s) ||
                        contact.getFirstName().equals(s)) {
                    result.add(contact);



            }
        }
        return result;

    }

    @Override
    public Collection<Contact> searchContactsByanyPartOfName(String s) {


        Collection<Contact> result = new ArrayList<Contact>();

        for (Contact contact : phoneBook.values()) {



                if (contact.getFirstName().toLowerCase().contains(s.toLowerCase())) {
                    result.add(contact);


                }

        }

        return result;


}

    @Override
    public Collection<Contact> searchContactsByPhoneNumber(String s) {

        Collection<Contact> result=new ArrayList<Contact>();
        for (Contact contact : phoneBook.values()){

                    List<Phone> phones=contact.getPhones();
                        for(Phone phone: phones){
                            if (phone.getPhone().equals(s)){
                                   result.add(contact);

                            }
                }

        }
        return result;

    }

    @Override
    public Collection<Contact> searchContactsByAge(int ags) {

       Collection<Contact> result=new ArrayList<Contact>();
        for (Contact contact : phoneBook.values()){


                int yearContact =contact.getDateBirthday().get(Calendar.YEAR);

                int yearNow=Calendar.getInstance().get(Calendar.YEAR);
                int tempAgs=yearNow-yearContact;
                  if (tempAgs==ags ){
                         result.add(contact);

                 }

        }
        return result;

    }

    @Override
    public Collection<Contact> searchContactsByAge(int from, int to) {


        Collection<Contact> result = new ArrayList<Contact>();
        for (Contact contact : phoneBook.values()) {

            int yearContact = contact.getDateBirthday().get(Calendar.YEAR);
            int yearNow = Calendar.getInstance().get(Calendar.YEAR);
            int tempAgs = yearNow - yearContact;

            if (from < tempAgs && to > tempAgs) {

                result.add(contact);

            }

        }
        return result;
    }





}
