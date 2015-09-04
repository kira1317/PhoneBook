package phone.book.model;

import java.io.Serializable;

/**
 * Created by Администратор on 20.07.15.
 */
public enum TypeTheTelephone implements Serializable {

   HOME("home"), MOBILE("mobile"), WORK("work");

    private final String type;

    TypeTheTelephone(String type) {

        this.type=type;

    }
    public String getType(){

        return this.type;
    };
}
