package com.example.dadajonjurakuziev.visituzbekistanfinal;

public class Review {
    private String title, review, profilePhotoUrl, profileName;
    private int rating;

    public Review() {
    }

    public Review(String profileName, String profilePhotoUrl, int rating, String title, String review) {
        this.title = title;
        this.review = review;
        this.profilePhotoUrl = profilePhotoUrl;
        this.profileName = profileName;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getReview() {
        return review;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public String getProfileName() {
        return profileName;
    }

    public int getRating() {
        return rating;
    }
}
