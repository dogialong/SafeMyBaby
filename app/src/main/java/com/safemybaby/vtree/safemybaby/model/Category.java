package com.safemybaby.vtree.safemybaby.model;

/**
 * Created by longdg123 on 10/13/2016.
 */

public class Category {

    /**
     * id : 1
     * name : Trong nhà1
     * img : http://imgur.com/fQXMwY3.png
     */

    private int id;
    private String name;
    private String img;

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public Category(String name, String img) {
        this.img = img;
        this.name = name;
    }
}
