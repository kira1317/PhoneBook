package phone.book.service;

import phone.book.model.Contact;
import phone.book.model.Phone;
import phone.book.model.TypeTheTelephone;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Администратор on 23.07.15.
 */
public class Menu {

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private PhoneBookService phoneBook=new PhoneBookServiceImpl();
    private final File file=new File("PhoneBook.ser");
    private final String fileEI=("PhoneBook.txt");
    private String login;
    private String password;
    private int id;
    private Scanner scanner=new Scanner(System.in);

    public Menu() throws IOException, ParseException {

        getPhoneBook();
        firstMenu();
    }


    private boolean autintificationMenu() throws IOException, ParseException {

        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter login ");
        this.login= scanner.nextLine();
        System.out.print("Enter password ");
        this.password  = scanner.nextLine();

        Autintification autintification=new Autintification();

            if (autintification.cheakLogin(this.login, this.password)) {
                secondMenu();
                return true;
            }

        System.out.print("ERROR login or password or not contact ");
        System.out.println("Are you wont go on Admin 1 or User 2");
        int menu=scanner.nextInt();
        M1: switch (menu){
            case 1:autintificationMenu();
                break;
            case 2:secondMenu();
                break;
            default:
                System.out.println("EEROR comand, please enter anather namber");
                break M1;
        }


        return false;
    }


    private boolean setPhoneBook() {

        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        try {
            fos = new FileOutputStream(file.getName());
            out = new ObjectOutputStream(fos);
            out.writeObject(this.phoneBook);
            out.close();
            return true;
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

        return false;

    }

    private boolean getPhoneBook() {



        FileInputStream fis = null;
        ObjectInputStream in = null;

        try {

            if(!file.createNewFile()) {

                fis = new FileInputStream(file.getName());
                in = new ObjectInputStream(fis);
                this.phoneBook =(PhoneBookServiceImpl)in.readObject();
                in.close();
                return true;

            }

        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException e) {

            e.printStackTrace();
        }


       return false;
    }

    public  void   firstMenu() throws IOException, ParseException {

        System.out.println("Phone book");
        System.out.println("1. Admin");
        System.out.println("2. User");
        System.out.print("Enter 1 or 2 ");

        int menu=scanner.nextInt();
        getPhoneBook();
        switch (menu){
            case 1:
                 if(autintificationMenu()){
                     secondMenu();
                 }
                break;
            case 2:

                    secondMenu(true);
                break;

            default:
                System.out.println("EEROR comand, please enter anather namber");
                break;


        };

    }


    public  void   secondMenu() throws IOException, ParseException {

        System.out.println("Phone Book");
        System.out.println("1. Create new contact");
        System.out.println("2. Edit contact");//admin
        System.out.println("3. Delete contact");//admin
        System.out.println("4. Get all contacts");
        System.out.println("5. Search");
        System.out.println("6. Export");//admin
        System.out.println("7. Import");//admin
        System.out.println("8. Exit");

        System.out.print("Plese enter the nomber to 1-8 ");


        int scan=scanner.nextInt();

        M1: switch (scan){

            case 1:
                   menuCreateNewContact();
                   break;
            case 2:
                   menuEditContact(this.id);
                   secondMenu();
                   break;
            case 3:this.phoneBook.deleteContact(this.id);
                   setPhoneBook();
                   System.out.println("Contact is delete");
                   secondMenu();
                   break;
            case 4:;

                   for(Contact contact:phoneBook.getAllContacts()){
                    System.out.println(contact.toString());
                   }
                   secondMenu();
                   break;
            case 5:menuSearch();
                   secondMenu();
                   break;

            case 6:
                  Export exportPhoneBook=new Export();
                  exportPhoneBook.exportPB(fileEI, (PhoneBookServiceImpl) this.phoneBook);
                  secondMenu();
                  break;

            case 7:

                Import importPhoneBook=new Import();

                this.phoneBook= (PhoneBookServiceImpl) importPhoneBook.importPB("PhoneBookImport.txt");

                secondMenu();
                break;
            case 8:
                break;


            default:System.out.println("ERROR comand, please enter 1-8");
                break M1;

        }
    }


    public  void   secondMenu(boolean i) throws IOException, ParseException {

        System.out.println("Phone Book");
        System.out.println("1. Create new contact");
        System.out.println("2. Get all contacts");
        System.out.println("3. Search");
        System.out.println("4. Exit");

        System.out.print("Plese enter the nomber to 1-4 ");


        int scan=scanner.nextInt();

        M1: switch (scan){

            case 1:
                menuCreateNewContact();
                break;

            case 2:;

                for(Contact contact:phoneBook.getAllContacts()){
                    System.out.println(contact.toString());
                }
                secondMenu(true);
                break;

            case 3:menuSearch();
                secondMenu(true);
                break;


            case 4:
                break;


            default:System.out.println("ERROR comand, please enter 1-4");
                break M1;

        }

    }

    private void menuSearch(){

        Collection<Contact> contacts=new ArrayList<Contact>();
        System.out.println("1. Search contacts by first or last name");
        System.out.println("2. Search contacts by any part of name");
        System.out.println("3. Search contacts by phone number");
        System.out.println("4. Search contacts by age");
        System.out.println("5. Search contacts by age range");

        int scan=scanner.nextInt();
        Scanner scannerMenu=new Scanner(System.in);
        M1: switch (scan){
            case 1:
                System.out.println("Please enter first or last name");
                contacts=this.phoneBook.searchContactsByFirstOrLastName(scannerMenu.nextLine());
                break;
            case 2:
                System.out.println("Please enter party name");
                contacts=this.phoneBook.searchContactsByanyPartOfName(scannerMenu.nextLine());
                break;
            case 3:
                System.out.println("Please enter telefon");
                contacts=this.phoneBook.searchContactsByPhoneNumber(scannerMenu.nextLine());
                break;
            case 4:
                System.out.println("Please enter age");
                contacts=this.phoneBook.searchContactsByAge(scannerMenu.nextInt());
                break;
            case 5:
                System.out.println("Please enter age range");

                contacts=this.phoneBook.searchContactsByAge(scannerMenu.nextInt(),scannerMenu.nextInt());

            default:
                System.out.println("EEROR comand, please enter 1-5");
                break M1;

        }

        for (Contact contact:contacts){

            System.out.println(contact.toString());

        }

    };

    public void menuCreateNewContact() throws IOException, ParseException {

        Scanner newContact=new Scanner(System.in);

        System.out.println("First name ");
        String firstName=newContact.nextLine();

        System.out.println("Last name ");
        String lastName=newContact.nextLine();

        System.out.println("Phones ");
        List<Phone> phones=new ArrayList<Phone>();

        System.out.println("Type phone mobile please enter phone:");
        phones.add(new Phone(newContact.nextLine(), TypeTheTelephone.MOBILE));
        System.out.println("Type phone work please enter phone:");
        phones.add(new Phone(newContact.nextLine(), TypeTheTelephone.WORK));
        System.out.println("Type phone home please enter phone:");
        phones.add(new Phone(newContact.nextLine(), TypeTheTelephone.HOME));

        System.out.println("E-mail ");
        String email=newContact.nextLine();

        System.out.println("Date birthday ");
        System.out.println("Enter year ");
        int year=newContact.nextInt();
        System.out.println("Enter month ");
        int month=newContact.nextInt();
        System.out.println("Enter day ");
        int day=newContact.nextInt();
        Calendar calendar=Calendar.getInstance();
        calendar.set(year, month, day);

        Scanner s=new Scanner(System.in);
        System.out.println("Adress ");
        String addres=s.nextLine();

        Contact contact=new Contact(this.id,addres,calendar,email,phones,firstName,lastName);

        this.phoneBook.createNewContact(contact);
        setPhoneBook();
        secondMenu(true);


    };


    private void menuEditContact(int id){

        Contact contact=this.phoneBook.searchContact(id);

        int edit=contact.getId();
        String firstName;
        String lastName;
        String addres;
        Calendar calendar=Calendar.getInstance();
        String email;
        List<Phone> phones=new ArrayList<Phone>();

        System.out.println("First name " + contact.getFirstName() + " if you wont edit enter 1");
           if(scanner.nextInt()==1) {
               Scanner scanner1=new Scanner(System.in);
               firstName = scanner1.nextLine();
            }
           else
            {
              firstName=contact.getFirstName();
            }

        System.out.println("Last name " + contact.getlastName() + " if you wont edit enter 1");
            if(scanner.nextInt()==1) {
                Scanner scanner1=new Scanner(System.in);
                lastName = scanner1.nextLine();
             }
            else
            {
            lastName=contact.getlastName();
            }

        System.out.println("Phone if you wont edit enter 1 or 2");
        if(scanner.nextInt()==1) {

            phones = contact.getPhones();
            int i=0;
            for (Phone phone: phones) {
                System.out.println("If you wont edit phone"+phone.getType()+" "+phone.getPhone()+ "enter 1");
                if (scanner.nextInt()==1){
                    Scanner scanner1=new Scanner(System.in);
                    String phoneNew=scanner1.nextLine();
                    Phone phoneNewAndType=new Phone(phoneNew,TypeTheTelephone.values()[i++]);
                    phones.remove(phone);
                    phones.add(phoneNewAndType);
                }
            }
        }
        else
        {
            phones = contact.getPhones();
        }

        System.out.println("e-mail " + contact.getEmail() + " if you wont edit enter 1");
        if(scanner.nextInt()==1) {
            Scanner scanner1=new Scanner(System.in);
            email = scanner1.nextLine();
        }
        else
        {
            email=contact.getEmail();
        }


        System.out.println("Date birthdat " + DATE_FORMAT.format(contact.getDateBirthday().getTime()) + " if you wont edit enter 1");
        if(scanner.nextInt()==1) {
            Scanner scanner1=new Scanner(System.in);
            System.out.println("Enter year ");
            int year=scanner1.nextInt();
            System.out.println("Enter month ");
            int month=scanner1.nextInt();
            System.out.println("Enter day ");
            int day=scanner1.nextInt();

            calendar.set(year, month, day);
        }
        else
        {
            calendar=contact.getDateBirthday();
        }

        System.out.println("Adress " + contact.getAddres() + " if you wont edit enter 1");
        if(scanner.nextInt()==1) {
            Scanner scanner1=new Scanner(System.in);
            addres = scanner1.nextLine();
        }
        else
        {
            addres=contact.getAddres();
        }

        Contact contactEdit=new Contact(contact.getId(),addres,calendar,email,phones,firstName,lastName);

        this.phoneBook.editContact(contactEdit);
        setPhoneBook();



    };



}
