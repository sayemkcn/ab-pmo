package com.aimslabs.domains;

import com.aimslabs.domains.pojo.Address;
import com.aimslabs.domains.pojo.AutismCenter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sayemkcn on 11/9/16.
 */
@Entity
public class User extends BaseEntity {
    private String name;
    private String email;
    private String password;
    private String role;
    private String phoneNumber;
    @Embedded
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Child> childList;
    @OneToOne
    private AutismCenter autismCenter;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", childList=" + childList +
                '}';
    }
}
