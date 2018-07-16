package movies.model;

import movies.model.Users;

import java.util.Date;

/**
 * @author nikithanagaraj
 */
public class Reviews {
    protected int reviewsid;
    protected Date created;
    protected String content;
    protected double rating;
    protected Users users;
    protected Movies movies;


    public Reviews(int reviewsid, Date created, String content, double rating, Users users,
                   Movies movies) {
        this.reviewsid = reviewsid;
        this.created = created;
        this.content = content;
        this.rating = rating;
        this.users = users;
        this.movies =  movies;

    }

    public Reviews(Date created, String content, double rating, Users users,
                   Movies movies) {
        this.created = created;
        this.content = content;
        this.rating = rating;
        this.users = users;
        this.movies = movies;

    }

    public int getReviewsid() {
        return reviewsid;
    }

    public void setReviewsid(int reviewsid) {
        this.reviewsid = reviewsid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }


}
