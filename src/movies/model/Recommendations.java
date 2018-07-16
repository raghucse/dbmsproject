package movies.model;


import movies.model.Users;

/**
 * @author nikithanagaraj
 */
public class Recommendations {
    protected int recommendationid;
    protected Users users;
    protected Movies movies;

    public Recommendations(int recommendationid, Users users,  Movies movies) {
        this.recommendationid = recommendationid;
        this.users = users;
        this.movies = movies;
    }

    public Recommendations(Users users, Movies movies) {
        this.users = users;
        this.movies = movies;
    }

    public int getRecommendationid() {
        return recommendationid;
    }

    public void setRecommendationid(int recommendationid) {
        this.recommendationid = recommendationid;
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

    public void setMovies(Movies restaurants) {
        this.movies = movies;
    }
}
