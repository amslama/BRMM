package com.example.brmm;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Category implements Serializable {
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


    //returns all supercategories belonging to a base category
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

    public String getRelationshipPair() {
        return name + "," + superCategory;
    }

    //TODO Move this out of Category
    //Algorithm for putting all relationships in an arrayList
    public ArrayList<String> getRelationshipList(ArrayList<Category> catList) {
        ArrayList<String> relationshipList = new ArrayList<>();
        for (Category cat : catList) {
            relationshipList.add(cat.getRelationshipPair());

        }
        return  relationshipList;
    }




}