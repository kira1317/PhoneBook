package phone.book.service;

import phone.book.model.Contact;
import phone.book.model.Phone;
import phone.book.model.TypeTheTelephone;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Администратор on 20.08.15.
 */
public class Import{


    private  final  static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private final static String CSV_SEPARATOR = ";";

    public PhoneBookService importPB(String fileAdress) throws FileNotFoundException, ParseException {


        FileReader fileReader = new FileReader(fileAdress);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Scanner scanner = new Scanner(bufferedReader);
        boolean skipFirstLine = true;

        PhoneBookService phoneBook = new PhoneBookServiceImpl();
        while (scanner.hasNext()){
            String string = scanner.nextLine();
            if(skipFirstLine){
                skipFirstLine = false;
                continue;
            }
            String[] stringsArray = string.split(CSV_SEPARATOR);


            Phone phoneHome=new Phone(stringsArray[4], TypeTheTelephone.HOME);
            Phone phoneMobile=new Phone(stringsArray[6],TypeTheTelephone.MOBILE);
            Phone phoneWork=new Phone(stringsArray[8],TypeTheTelephone.WORK);
            List<Phone> phones=new ArrayList<>();
            phones.add(phoneHome);
            phones.add(phoneMobile);
            phones.add(phoneWork);


            Calendar calendar=Calendar.getInstance();
            Date date=DATE_FORMAT.parse(stringsArray[2]);
            calendar.setTime(date);


            Contact contact = new Contact((new Integer(stringsArray[0])),stringsArray[1],
                    calendar,stringsArray[3],
                    phones,stringsArray[9],stringsArray[10]);



            phoneBook.createNewContact(contact);
        }
        return phoneBook ;


    }

}
