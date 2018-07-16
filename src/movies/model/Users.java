package movies.model;

import javax.print.DocFlavor;
import java.util.Date;


/**
 * @author nikithanagaraj
 */
public class Users {
    protected String UserName;
    protected String FristName;
    protected String LastName;
    protected String Password;
    protected String Email;
    protected String Phone;

    public Users(String userName, String firstName, String lastName, String password, String email, String phone) {
        this.UserName = userName;
        this.FristName = firstName;
        this.LastName = lastName;
        this.Password = password;
        this.Email = email;
        this.Phone = phone;
    }
    public Users(String userName) {
        this.UserName = userName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFirstName() {
        return FristName;
    }

    public void setFristName(String fristName) {
        FristName = fristName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

}
