package movies.model;

import java.util.Date;

public class ShowInfo {
    protected int showinfoid;
    protected Theatre theatre;
    protected Movies movies;
    protected int price;
    protected Date showtime;

    public ShowInfo(int showinfoid, Theatre theatre, Movies movies, int price, Date showtime){
        this.showinfoid = showinfoid;
        this.theatre = theatre;
        this.movies = movies;
        this.price = price;
        this.showtime = showtime;
    }

    public int getShowinfoid() {
        return showinfoid;
    }

    public void setShowinfoid(int showinfoid) {
        this.showinfoid = showinfoid;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getShowtime() {
        return showtime;
    }

    public void setShowtime(Date showtime) {
        this.showtime = showtime;
    }
}
