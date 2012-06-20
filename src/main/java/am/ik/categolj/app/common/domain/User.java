package am.ik.categolj.app.common.domain;

import java.io.Serializable;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@SuppressWarnings("serial")
@Entity(value = "user", noClassnameStored = true)
public class User implements Serializable {
    @Id
    private String name;
    private String password;
    private String role;

    public User() {
    }

    public User(String name, String password, String role) {
        super();
        this.name = name;
        this.password = password;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", password=****, role="
                + role + "]";
    }

}
