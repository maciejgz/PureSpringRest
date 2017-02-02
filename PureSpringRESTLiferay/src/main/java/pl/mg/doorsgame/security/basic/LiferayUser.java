package pl.mg.doorsgame.security.basic;

public class LiferayUser {



    private String screenname;
    private String password;
    private String role;

    public LiferayUser(String screenname, String password, String role) {
        super();
        this.screenname = screenname;
        this.password = password;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getScreenname() {
        return screenname;
    }

    public void setScreenname(String screenname) {
        this.screenname = screenname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
