package com.example.brmm;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Category {
    private String name;
    private Category superCategory;
    private ArrayList<Category> subCategory;

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

    public ArrayList<Category> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Category subCategory) {
        this.subCategory.add(subCategory);
    }

    public Category(Category superCategory, String name) {
        this.superCategory = superCategory;
        this.name = name;
        subCategory = null;
    }

    public void addCategory(Category superCategory, String name) {
        Category newCat = new Category(superCategory, name);
        newCat.superCategory.subCategory.add(newCat);
    }

    public ArrayList<Category> getAllSubCategories(Category aCategory) {

        ArrayList<Category> catList = new ArrayList<Category>();
        if (aCategory.subCategory == null)
            catList.add(aCategory);
        else {
            for (Category categories : subCategory)
                catList.addAll(getAllSubCategories(categories));

        }
        return catList;
    }
}
