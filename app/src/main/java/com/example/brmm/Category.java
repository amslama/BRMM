package com.example.brmm;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Category {

    private Category superCategory;
    private ArrayList<Category> subCategories;

    public  Category() {
        superCategory = null;
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

    public void addSubCategory(Category cat) {
        subCategories.add(cat);
    }

    public void removeSubCategory(Category cat) {
        for (Category subCat: subCategories) {
            if (subCat == cat)
                subCategories.remove(cat);
        }
    }
}