package movies.model;

import movies.model.Users;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author nikithanagaraj
 */
public class CreditCards {
    protected long cardnumber;
    protected Date date;
    protected Users users;

    public CreditCards(long cardnumber, Date date, Users users) {
        this.users = users;
        this.cardnumber = cardnumber;
        this.date = date;
    }

    public long getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(long cardnumber) {
        this.cardnumber = cardnumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

}
