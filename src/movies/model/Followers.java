package movies.model;

import movies.model.Users;

public class Followers {
    protected int followerid;
    protected String followername;
    protected String username;

    public Followers(String user,String follower) {
        this.username = user;
        this.followername = follower;
    }

    public String getFollowername() {
        return followername;
    }

    public void setFollowername(String followername) {
        this.followername = followername;
    }

    public int getFollowerid() {
        return followerid;
    }

    public void setFollowerid(int followerid) {
        this.followerid = followerid;
    }

    public String getUsers() {
        return this.username;
    }

    public void setUsers(String user) {
        this.username = user;
    }


}
