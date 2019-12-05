package com.example.brmm;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Category {
    private String name;
    private Category superCategory;

    public Category() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getSuperCategory() {
        return superCategory;
    }

    public void setSuperCategory(Category superCategory) {
        this.superCategory = superCategory;
    }




    public Category(Category superCategory, String name) {
        this.superCategory = superCategory;
        this.name = name;

    }

    public ArrayList<Category> getSuperCategories() {
        ArrayList<Category> superCats = new ArrayList<>();
        Category cat = this;
        while (cat.superCategory!= null) {
            superCats.add(cat);
            cat = cat.superCategory;
        }
        return superCats;
    }
}