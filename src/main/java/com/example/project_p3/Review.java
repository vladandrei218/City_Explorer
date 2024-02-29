package com.example.project_p3;
public class Review {
    private String Review;
    private String Reviewer;
    private double ReviewRating;
    public Review(String Review, String Reviewer, double ReviewRating) {
        this.Review = Review;
        this.Reviewer = Reviewer;
        this.ReviewRating = ReviewRating;
    }
    public String getReview() {
        return Review;
    }
    public String getReviewer() {
        return Reviewer;
    }
    public double getReviewRating() {
        return ReviewRating;
    }
}

