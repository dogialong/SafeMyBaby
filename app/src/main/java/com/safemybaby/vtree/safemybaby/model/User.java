package com.safemybaby.vtree.safemybaby.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

/**
 * Created by longdg123 on 11/6/2016.
 */

@Table(name = "tbUser",id="id_user_table")
public class User extends Model implements Serializable {
    @Column(name="id_user")private String id_user;
    @Column(name="name")private String name;
    @Column(name="diem") private String diem;

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getDiem() {
        return diem;
    }

    public void setDiem(String diem) {
        this.diem = diem;
    }
}

