package fanwei.pojo;

/**
 * 登录用户
 * Created by Administrator on 2017/5/3.
 */
public class User {
    private String name;
    private String passwrod;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswrod() {
        return passwrod;
    }

    public void setPasswrod(String passwrod) {
        this.passwrod = passwrod;
    }

    public User(String name, String passwrod) {
        this.name = name;
        this.passwrod = passwrod;
    }
}
