package phone.book.model;

import java.io.Serializable;


/**
 * Created by Администратор on 20.07.15.
 */
public class Phone  implements Serializable {

    private String phone;
    private TypeTheTelephone type;
    private final char[] nambers={'0','1','2','3','4','5','6','7','8','9'};


    public String getPhone() {
        return phone;
    }
    public String getType() {
        return type.getType();
    }

    public Phone(String phone,TypeTheTelephone type) {

        this.phone = correctPhone(phone);
        this.type=type;

    }


    private String correctPhone(String s){

        char namberPhone[]=s.toCharArray();

        StringBuilder sb=new StringBuilder();



        for (int i=0;i<s.length();i++){

            for (int j=0;j<this.nambers.length;j++){

                if( namberPhone[i]==this.nambers[j]){

                    sb.append(namberPhone[i]);
                    continue ;

                }
            }
        }

        return sb.toString();
    };

}
