package com.demo2.demo.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 贺威
 * @create 2019-04-12 11:41
 */
public class User {

    private Integer uid;

    private String username;

    private String password;

    private Set<Role> roles=new HashSet<>();

    public User() {
    }

    public User(Integer uid, String username, String password, Set<Role> roles) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
