package com.example.project_p3;

public class PointOfInterestTests {

    public static void main(String[] args) {
        testConstructorWithName();
        addCategory();
    }

    public static void testConstructorWithName() {
        String poiName = "TestPOI";
        String cityName = "TestCity";

        PointOfInterest poi = new PointOfInterest(poiName, "TestDesc", 5);
        City city = new City(cityName);
        poi.setCity(city);

        System.out.println("Testing Point of Interest Constructor:");
        System.out.println("Point of Interest Name: " + poi.getName());
        System.out.println("City: " + poi.getCity());
        System.out.println("Test passed!");
    }
    public static void addCategory() {
        String poiName = "TestPOI";
        String cityName = "TestCity";
        String categoryName = "TestCategory";

        PointOfInterest poi = new PointOfInterest(poiName, "TestDesc", 5);
        City city = new City(cityName);
        poi.setCity(city);
        Category category = new Category(categoryName);
        poi.setCategory(category);

        System.out.println("Testing Category Constructor:");
        System.out.println("Point of Interest Name: " + poi.getName());
        System.out.println("City: " + poi.getCity());
        System.out.println("Category: " + poi.getCategory());
        System.out.println("Test passed!");
    }
}