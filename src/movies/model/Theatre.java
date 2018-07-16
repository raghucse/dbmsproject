package movies.model;

public class Theatre {
    public int getTheatreid() {
        return theatreid;
    }

    public void setTheatreid(int theatreid) {
        this.theatreid = theatreid;
    }

    public String getTheatrename() {
        return theatrename;
    }

    public void setTheatrename(String theatrename) {
        this.theatrename = theatrename;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public TheatreType getTheatreType() {
        return theatreType;
    }

    public void setTheatreType(TheatreType theatreType) {
        this.theatreType = theatreType;
    }

    protected int theatreid;
    protected String theatrename;
    protected String location;
    protected TheatreType theatreType;

    public enum TheatreType {
       IMAX, STANDARD, RPX;
    }

    public Theatre(int theatreid, String theatrename, String location,TheatreType theatreType){
        this.theatreid = theatreid;
        this.location = location;
        this.theatrename = theatrename;
        this.theatreType = theatreType;
    }
    public Theatre(int theatreid){
        this.theatreid = theatreid;
    }
}
