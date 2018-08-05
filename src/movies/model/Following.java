package movies.model;

public class Following {
    protected  int follwingid;
    protected String followingname;
    protected String user;

    public Following(String users, String following) {
        this.user = users;
        this.followingname = following;
    }

    public String getFollowingname() {
        return followingname;
    }

    public void setFollowingname(Users followingname) {
        this.followingname = followingname.getUserName();
    }

    public int getFollwingid() {
        return follwingid;
    }

    public void setFollwingid(int follwingid) {
        this.follwingid = follwingid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }



}
