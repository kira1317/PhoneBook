package phone.book.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.List;

/**
 * Created by Администратор on 20.07.15.
 */
public class Contact implements Serializable {

    private final int id;

    private String firstName;
    private String lastName;
    private List<Phone> phones;
    private String email;
    private Calendar dateBirthday;
    private String addres;

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public Contact(int id, String addres, Calendar dateBirthday, String email,List<Phone> phones,
                   String firstName, String lastName) {

        this.id=id;

        this.addres = addres;
        this.dateBirthday = dateBirthday;
        this.email = email;
        this.phones = phones;
        this.firstName = firstName;
        this.lastName = lastName;

    }



    @Override
    public String toString() {

        StringBuilder sb= new StringBuilder();

        sb.append(id).append(". ").append(getFirstName()).append(" ")
                .append(getlastName()).append("\n")
                .append("Data birthday :").append(DATE_FORMAT.format(getDateBirthday().getTime())).append("\n")
                .append("E-mail :").append(getEmail()).append("\n")
                .append("Adress :").append(getAddres()).append("\n")
                .append("Phones :\n");

        for (Phone phone: getPhones()){
            sb.append(phone.getType()).append(" phone: ")
                    .append(phone.getPhone()).append("\n");
        }

        return sb.toString();

    }


    public String getFirstName() {
        return firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public String getEmail() {
        return email;
    }

    public Calendar getDateBirthday() {
        return dateBirthday;
    }

    public String getAddres() {
        return addres;
    }

    public int getId() {return id;}






}
