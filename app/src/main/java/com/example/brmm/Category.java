package com.example.brmm;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Category {
    private Category superCategory;
    private String name;

    @Override
    public String toString() {
        return name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public  Category() {
        superCategory = null;
    }
    public Category(Category superCat) {
        superCategory = superCat;
    }


    public Category getSuperCategory() {
        return superCategory;
    }

    public void setSuperCategory(Category superCategory) {
        this.superCategory = superCategory;
    }



    public ArrayList<Category> getSuperCategories() {
        ArrayList<Category> superCats = new ArrayList<>();
        Category cat = this;
        while (cat.superCategory!= null) {
            superCats.add(cat);
            cat = cat.superCategory;
        }
        superCats.add(cat);
        return superCats;
    }



}