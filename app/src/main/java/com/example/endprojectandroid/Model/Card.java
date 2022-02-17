package com.example.endprojectandroid.Model;

public class Card {

    private String name;
    private String imgLink;
    private String type;

    public Card(String name, String imgLink, String type) {
        this.name = name;
        this.imgLink = imgLink;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
