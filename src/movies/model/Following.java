package movies.model;

public class Following {
    protected  int follwingid;
    protected String followingname;
    protected String users;

    public Following(int followingid, Users users, Users following) {
        this.follwingid = follwingid;
        this.users = users.getUserName();
        this.followingname = following.getUserName();
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

    public String getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users.getUserName();
    }



}
