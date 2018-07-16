package movies.model;

import java.awt.print.Book;
import java.util.Date;

public class Booking {
    protected int bookingId;
    protected ShowInfo showInfo;
    protected Users users;
    protected Date bookingtime;
    protected int numberofguests;

    public Booking(int bookingId, ShowInfo showInfo, Users users, Date bookingtime, int numberofguests){
        this.bookingId = bookingId;
        this.showInfo = showInfo;
        this.users = users;
        this.bookingtime = bookingtime;
        this.numberofguests = numberofguests;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public ShowInfo getShowInfo() {
        return showInfo;
    }

    public void setShowInfo(ShowInfo showInfo) {
        this.showInfo = showInfo;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Date getBookingtime() {
        return bookingtime;
    }

    public void setBookingtime(Date bookingtime) {
        this.bookingtime = bookingtime;
    }

    public int getNumberofguests() {
        return numberofguests;
    }

    public void setNumberofguests(int numberofguests) {
        this.numberofguests = numberofguests;
    }
}
