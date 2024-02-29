package com.example.project_p3;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {
    private final List<PointOfInterest> pointsOfInterestList;
    private final List<Category> categoriesList;

    public Sort(List<PointOfInterest> pointsOfInterest, List<Category> categories) {
        this.pointsOfInterestList = new ArrayList<>(pointsOfInterest);
        this.categoriesList = new ArrayList<>(categories);
    }
    public List<PointOfInterest> compareByRating() {
        List<PointOfInterest> sortedList = new ArrayList<>(pointsOfInterestList);
        sortedList.sort(PointOfInterest::compareTo);
        return sortedList;
    }
    public List<PointOfInterest> compareByCity() {
        List<PointOfInterest> sortedList = new ArrayList<>(pointsOfInterestList);
        sortedList.sort(Comparator.comparing(poi -> poi.getCity().getName()));
        return sortedList;
    }
    public List<PointOfInterest> compareByName() {
        List<PointOfInterest> sortedList = new ArrayList<>(pointsOfInterestList);
        sortedList.sort(Comparator.comparing(PointOfInterest::getName));
        return sortedList;
    }
    public List<PointOfInterest> compareByCategory(){
        List<PointOfInterest> sortedList = new ArrayList<>(pointsOfInterestList);
        sortedList.sort(Comparator.comparing(poi -> poi.getCategory().getCategoryName()));
        return sortedList;
    }
}

