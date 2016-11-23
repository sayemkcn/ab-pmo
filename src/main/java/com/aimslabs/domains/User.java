package com.aimslabs.domains;

import com.aimslabs.domains.pojo.Address;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by sayemkcn on 11/9/16.
 */
@Entity(name = "a_user")
public class User extends BaseEntity {
    private String name;
    private String email;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<String> roles;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
