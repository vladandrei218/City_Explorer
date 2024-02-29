package com.example.project_p3;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private List<PointOfInterest> pointsOfInterest;
    private List<Category> categories;
    public City(String name){
        this.name=name;
        this.pointsOfInterest = new ArrayList<>();
    }
    public String getName(){
        return name;
    }
    public void addPointOfInterest(PointOfInterest poi){
        pointsOfInterest.add(poi);
        poi.setCity(this);
    }
    public String toString(){
        return name;
    }
    public List<PointOfInterest> getPointsOfInterest() {
        return pointsOfInterest;
    }
}
