package phone.book.service;
import java.io.Serializable;

/**
 * Created by Администратор on 01.09.15.
 */
public class Autintification {

    private final String login="admin";
    private final String password="admin";


   public boolean cheakLogin(String login, String password){

            if(this.login.equals(login) && this.password.equals(password)){

                return true;

        }



        return false;
    }


}
