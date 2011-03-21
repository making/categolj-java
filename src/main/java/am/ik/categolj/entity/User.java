package am.ik.categolj.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
    public Long id;
    public String name;
    public String password;

    public User() {
    }

    public User(Long id, String name, String password) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password
                + "]";
    }

}
