package movies.model;

public class Movies {
    protected int moviesId;
    protected String moviename;
    protected String overview;
    protected String releasedate;
    protected String country;
    protected String language;
    protected String genre;
    protected int runtime;


    public Movies(int moviesId,String moviename, String overview, String releasedate, String country, String language, String genre, int runtime) {
        this.moviesId=moviesId;
        this.moviename = moviename;
        this.overview = overview;
        this.releasedate =releasedate;
        this.country = country;
        this.language = language;
        this.genre = genre;
        this.runtime = runtime;
    }

    public int getMoviesId() {
        return moviesId;
    }

    public void setMoviesId(int moviesId) {
        this.moviesId = moviesId;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }




}
