package com.example.project_p3;

import java.util.List;

public class CityTests {

    public static void main(String[] args) {
        testConstructorWithName();
        testAddPointOfInterest();
    }

    public static void testConstructorWithName() {
        String cityName = "TestCity";

        City city = new City(cityName);

        System.out.println("Testing City Constructor:");
        System.out.println("City Name: " + city.getName());
        System.out.println("Points of Interest: " + city.getPointsOfInterest().size());
        System.out.println("Test passed!\n");
    }

    public static void testAddPointOfInterest() {
        City city = new City("TestCity");
        PointOfInterest poi = new PointOfInterest("TestPOI", "TestDesc", 5);

        city.addPointOfInterest(poi);

        System.out.println("Testing AddPointOfInterest:");
        List<PointOfInterest> pointsOfInterest = city.getPointsOfInterest();
        System.out.println("Points of Interest after addition: " + pointsOfInterest.size());
        System.out.println("Test passed!");
    }
}
