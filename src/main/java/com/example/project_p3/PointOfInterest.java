package com.example.project_p3;

import java.util.ArrayList;
import java.util.List;

public class PointOfInterest implements  Searchable, Comparable<PointOfInterest>, Reviewable {
    private String name,description;
    private double rating;
    private City city;
    private Category category;
    private List<Review> reviews;
    public PointOfInterest(String id, String description, double rating){
        this.name=id;
        this.description=description;
        this.rating=rating;
    }
    public void setCity(City city){
        this.city=city;
    }
    public void setCategory(Category category){
        this.category=category;
    }
    public String getName() {
        return name;
    }
    public String getDescr(){
        return description;
    }
    public City getCity() {
        return city;
    }
    public double getRating() {
        return rating;
    }
    public Category getCategory() {
        return category;
    }
    @Override
    public List<PointOfInterest> search(String keyword) {
        List<PointOfInterest> searchResults = new ArrayList<>();
        if (description != null && description.contains(keyword)) {
            searchResults.add(this);
        }
        if (name != null && name.contains(keyword)) {
            searchResults.add(this);
        }
        return searchResults;
    }
    @Override
    public int compareTo(PointOfInterest p) {
        return Double.compare(this.rating, p.rating);
    }
    @Override
    public void addReview(Review review) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }
    @Override
    public void printReviews() {
        if (reviews == null || reviews.isEmpty()) {
            System.out.println("No reviews available.");
        } else {
            for (Review review : reviews) {
                System.out.println(review.getReviewer() + " Rating: " + review.getReviewRating() + " Review: " + review.getReview());
            }
        }
    }
}

