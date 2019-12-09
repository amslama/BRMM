package com.example.brmm;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.ArrayList;


//Class to build trees from Database
public class TreeBuilder {
    //list of relationships
    private ArrayList<Relationship> relationshipList;

    public TreeBuilder() {
        relationshipList = new ArrayList<>();
    }

    //returns list of relationships
    public ArrayList<Relationship> returnRelationships(){
        return relationshipList;
    }


    //Builds a tree of categories from simple relationships
    public ArrayList<Category> buildTree(ArrayList<String> commaString) {
        buildRelationShips(commaString);
        ArrayList<Relationship> list = relationshipList;
        ArrayList<Category> catList = new ArrayList<>();


        Iterator<Relationship> relationIterator = list.iterator();
        Category topCat = new Category(null);
        while (relationIterator.hasNext()){
            Relationship relationship = relationIterator.next();
            if (relationship.getSuperCategory().equals("null")) {
                topCat.setName(relationship.getCategory());
                relationIterator.remove();
                catList.add(topCat);
            }


        }


        Category newCat;
        for (int i = 0; i < catList.size(); i++) {
            relationIterator = list.iterator();
            final Category cat = catList.get(i);
            while (relationIterator.hasNext()){
                Relationship relationship = relationIterator.next();
                if (relationship.getSuperCategory().equals(cat.getName())) {
                    newCat = new Category(cat);
                    newCat.setName(relationship.getCategory());
                    catList.add(newCat);
                    relationIterator.remove();
                }

            }


        }
        return catList;
    }

    //builds relationships so class can build a tree
    public void buildRelationShips(ArrayList<String> commaString) {

        Relationship relationship;
        for (String relation: commaString) {
            relationship = new Relationship();
            String[] values = relation.split(",");

            relationship.setCategory(values[0]);
            relationship.setSuperCategory(values[1]);
            relationshipList.add(relationship);


        }

    }


    //Helper class to store relationships
    private class Relationship {
        private String category;
        private String superCategory;

        public void setCategory(String category) {
            this.category = category;
        }

        public void setSuperCategory(String superCategory) {
            this.superCategory = superCategory;
        }

        public String getCategory() {
            return category;
        }

        public String getSuperCategory() {
            return superCategory;
        }

        public String toString() {
            return category + "   "+ superCategory+"\n";
        }
    }
}


