package com.safemybaby.vtree.safemybaby.model;

/**
 * Created by longdg123 on 10/13/2016.
 */

public class Question {


    /**
     * id : 8
     * name : Trong nhà
     * type : 0
     * img : http://i.imgur.com/ozsHyFb.png
     */

    private int id;
    private String name;
    private String type;
    private String img;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public Question(String img){
            this.img = img;
    }
}
