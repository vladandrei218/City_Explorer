package com.example.project_p3;

import java.util.ArrayList;
import java.util.List;

public class Category implements Searchable, Comparable<Category>{
    private String categoryName;
    private String description;
    public Category(String categoryName, String description){
        this.categoryName=categoryName;
        this.description=description;
    }

    public Category(String categoryName1) {
        this.categoryName = categoryName1;
    }

    public String getCategoryName() {
        return categoryName;
    }
    public String getDescription() {
        return description;
    }
    @Override
    public String toString() {
        return categoryName;
    }
    @Override
    public List<PointOfInterest> search(String keyword) {
        List<PointOfInterest> searchResults = new ArrayList<>();
        if (categoryName != null && categoryName.contains(keyword)) {
            // Add relevant points of interest to searchResults
        }
        return searchResults;
    }

    @Override
    public int compareTo(Category c) {
        return this.categoryName.compareTo(c.categoryName);
    }

}


