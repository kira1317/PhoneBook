package phone.book.service;

import phone.book.model.Contact;
import phone.book.model.Phone;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * Created by Администратор on 20.08.15.
 */
public class Export {

    private final static String CSV_SEPARATOR = ";";
    private final StringBuilder contactToString = new StringBuilder();

    public boolean exportPB(String adress, PhoneBookServiceImpl phoneBook) throws IOException {

       Collection<Contact> contacts=phoneBook.getAllContacts();


        for (Contact contact:contacts){


            List<Phone> phones=new ArrayList<>();
            phones=contact.getPhones();

          contactToString. append(contact.getId()).append(CSV_SEPARATOR)
                          .append(contact.getAddres()).append(CSV_SEPARATOR)

                          .append(contact.getDateBirthday().get(Calendar.YEAR)).append("-")
                          .append(contact.getDateBirthday().get(Calendar.MONTH)).append("-")
                          .append(contact.getDateBirthday().get(Calendar.DAY_OF_MONTH)).append(CSV_SEPARATOR)

                         .append(contact.getEmail()).append(CSV_SEPARATOR)

                         .append(contact.getPhones()).append(CSV_SEPARATOR)
                         .append(phones.get(0).getType()).append(CSV_SEPARATOR)

                         .append(contact.getPhones()).append(CSV_SEPARATOR)
                         .append(phones.get(1).getType()).append(CSV_SEPARATOR)

                         .append(contact.getPhones()).append(CSV_SEPARATOR)
                         .append(phones.get(1).getType()).append(CSV_SEPARATOR)

                         .append(contact.getFirstName()).append(CSV_SEPARATOR)
                         .append(contact.getlastName()).append("\n");

        }


        File file=new File(adress);
        FileWriter fileWriter=new FileWriter(file);
        fileWriter.write(contactToString.toString());
        fileWriter.flush();
        fileWriter.close();


        return false;
    }



}
