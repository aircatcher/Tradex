package com.tradr.tradex.ListView;

import java.util.ArrayList;

/**
 * Created by tb_laota on 9/21/2015.
 */
public class Item
{
    public String itemName, itemCat, itemDesc;
    private ArrayList<String> category;

    public String getTitle() { return itemName; }
    public void setTitle(String title) {
        this.itemName = itemName;
    }

    public String getImage() {
        return itemCat;
    }
    public void setImage(String image) { this.itemCat = itemCat; }

    public String getRate() {
        return itemDesc;
    }
    public void setRate(double rate) {
        this.itemDesc = itemDesc;
    }

    public ArrayList<String> getGenre() {
        return category;
    }
    public void setGenre(ArrayList<String> genre) {
        this.category = category;
    }

    public Item() {}
}
