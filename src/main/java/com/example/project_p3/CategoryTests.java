package com.example.project_p3;

public class CategoryTests {
    public static void main(String[] args) {
        testCategory();
    }
    public static void testCategory() {
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
