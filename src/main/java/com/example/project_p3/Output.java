package com.example.project_p3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Output {
    private List<PointOfInterest> pointsOfInterest;
    public Output(List<PointOfInterest> pointsOfInterest) {
        this.pointsOfInterest = pointsOfInterest;
    }
    public Output(){

    }
    public List<String> run(){
        List<PointOfInterest> pointsOfInterest = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        List<String> outputList = new ArrayList<>();

        try (BufferedReader csvReader = new BufferedReader(new FileReader("data/points_of_interest.csv"))) {
            String line;
            csvReader.readLine();
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");
                String cityName = data[0];
                String categoryName = data[1];
                String POIName = data[2];
                String POIDesc = data[3];
                double POIRating = Double.parseDouble(data[4]);
                City city = new City(cityName);
                Category category = new Category(categoryName);
                PointOfInterest poi = new PointOfInterest(POIName, POIDesc, POIRating);
                poi.setCity(city);
                poi.setCategory(category);

                pointsOfInterest.add(poi);
                categories.add(category);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        for (PointOfInterest poi : pointsOfInterest) {
            outputList.add("City: " + poi.getCity() + " Category: " + poi.getCategory() + " Name: " + poi.getName() + " Description: " + poi.getDescr() + " Rating: " + poi.getRating());
        }

        return outputList;
    }
    public List<String> run_rating() {
        List<PointOfInterest> pointsOfInterest = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        List<String> outputList = new ArrayList<>();

        try (BufferedReader csvReader = new BufferedReader(new FileReader("data/points_of_interest.csv"))) {
            String line;
            csvReader.readLine();
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");
                String cityName = data[0];
                String categoryName = data[1];
                String POIName = data[2];
                String POIDesc = data[3];
                double POIRating = Double.parseDouble(data[4]);

                City city = new City(cityName);
                Category category = new Category(categoryName);
                PointOfInterest poi = new PointOfInterest(POIName, POIDesc, POIRating);
                poi.setCity(city);
                poi.setCategory(category);

                pointsOfInterest.add(poi);
                categories.add(category);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        Sort sorter = new Sort(pointsOfInterest, categories);
        List<PointOfInterest> sortedPoints = sorter.compareByRating();

        for (PointOfInterest poi : sortedPoints) {
            outputList.add("City: " + poi.getCity() + " Name: " + poi.getName() + " Description: " + poi.getDescr() + " Rating: " + poi.getRating());
        }

        return outputList;
    }
    public List<String> run_name(){
        List<PointOfInterest> pointsOfInterest = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        List<String> outputList = new ArrayList<>();

        try (BufferedReader csvReader = new BufferedReader(new FileReader("data/points_of_interest.csv"))) {
            String line;
            csvReader.readLine();
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");
                String cityName = data[0];
                String categoryName = data[1];
                String POIName = data[2];
                String POIDesc = data[3];
                double POIRating = Double.parseDouble(data[4]);

                City city = new City(cityName);
                Category category = new Category(categoryName);
                PointOfInterest poi = new PointOfInterest(POIName, POIDesc, POIRating);
                poi.setCity(city);
                poi.setCategory(category);

                pointsOfInterest.add(poi);
                categories.add(category);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        Sort sorter = new Sort(pointsOfInterest, categories);
        List<PointOfInterest> sortedPoints = sorter.compareByName();

        for (PointOfInterest poi : sortedPoints) {
            outputList.add("City: " + poi.getCity() + " Name: " + poi.getName() + " Description: " + poi.getDescr() + " Rating: " + poi.getRating());
        }
        return outputList;
    }
    public List<String> run_city(){
        List<PointOfInterest> pointsOfInterest = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        List<String> outputList = new ArrayList<>();

        try (BufferedReader csvReader = new BufferedReader(new FileReader("data/points_of_interest.csv"))) {
            String line;
            csvReader.readLine();
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");
                String cityName = data[0];
                String categoryName = data[1];
                String POIName = data[2];
                String POIDesc = data[3];
                double POIRating = Double.parseDouble(data[4]);

                City city = new City(cityName);
                Category category = new Category(categoryName);
                PointOfInterest poi = new PointOfInterest(POIName, POIDesc, POIRating);
                poi.setCity(city);
                poi.setCategory(category);

                pointsOfInterest.add(poi);
                categories.add(category);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        Sort sorter = new Sort(pointsOfInterest, categories);
        List<PointOfInterest> sortedPoints = sorter.compareByCity();

        for (PointOfInterest poi : sortedPoints) {
            outputList.add("City: " + poi.getCity() + " Name: " + poi.getName() + " Description: " + poi.getDescr() + " Rating: " + poi.getRating());
        }
        return outputList;
    }
    public List<String> run_category(){
        List<PointOfInterest> pointsOfInterest = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        List<String> outputList = new ArrayList<>();

        try (BufferedReader csvReader = new BufferedReader(new FileReader("data/points_of_interest.csv"))) {
            String line;
            csvReader.readLine();
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");
                String cityName = data[0];
                String categoryName = data[1];
                String POIName = data[2];
                String POIDesc = data[3];
                double POIRating = Double.parseDouble(data[4]);

                City city = new City(cityName);
                Category category = new Category(categoryName);
                PointOfInterest poi = new PointOfInterest(POIName, POIDesc, POIRating);
                poi.setCity(city);
                poi.setCategory(category);

                pointsOfInterest.add(poi);
                categories.add(category);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        Sort sorter = new Sort(pointsOfInterest, categories);
        List<PointOfInterest> sortedPoints = sorter.compareByCategory();

        for (PointOfInterest poi : sortedPoints) {
            outputList.add("City: " + poi.getCity() + " Category: " + poi.getCategory() + " Name: " + poi.getName() + " Description: " + poi.getDescr() + " Rating: " + poi.getRating());
        }
        return outputList;
    }
    public List<String> search_city() {
        List<PointOfInterest> pointsOfInterest = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        List<String> outputList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String citySearch = scanner.nextLine();

        try (BufferedReader csvReader = new BufferedReader(new FileReader("data/points_of_interest.csv"))) {
            String line;
            csvReader.readLine();
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");
                String cityName = data[0];
                String categoryName = data[1];
                String POIName = data[2];
                String POIDesc = data[3];
                double POIRating = Double.parseDouble(data[4]);

                City city = new City(cityName);
                Category category = new Category(categoryName);
                PointOfInterest poi = new PointOfInterest(POIName, POIDesc, POIRating);
                poi.setCity(city);
                poi.setCategory(category);

                pointsOfInterest.add(poi);
                categories.add(category);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        for (PointOfInterest poi : pointsOfInterest) {
            if (poi.getCity().getName().toLowerCase().contains(citySearch.toLowerCase())) {
                outputList.add("City: " + poi.getCity() + " Name: " + poi.getName() + " Description: " + poi.getDescr() + " Rating: " + poi.getRating());
            }
        }
        return outputList;
    }
    public List<String> search_name() {
        List<PointOfInterest> pointsOfInterest = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        List<String> outputList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String citySearch = scanner.nextLine();

        try (BufferedReader csvReader = new BufferedReader(new FileReader("data/points_of_interest.csv"))) {
            String line;
            csvReader.readLine();
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");
                String cityName = data[0];
                String categoryName = data[1];
                String POIName = data[2];
                String POIDesc = data[3];
                double POIRating = Double.parseDouble(data[4]);

                City city = new City(cityName);
                Category category = new Category(categoryName);
                PointOfInterest poi = new PointOfInterest(POIName, POIDesc, POIRating);
                poi.setCity(city);
                poi.setCategory(category);

                pointsOfInterest.add(poi);
                categories.add(category);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        for (PointOfInterest poi : pointsOfInterest) {
            if (poi.getName().toLowerCase().contains(citySearch.toLowerCase())) {
                outputList.add("City: " + poi.getCity() + " Name: " + poi.getName() + " Description: " + poi.getDescr() + " Rating: " + poi.getRating());
            }
        }
        return outputList;
    }
    public List<String> search_category() {
        List<PointOfInterest> pointsOfInterest = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        List<String> outputList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String citySearch = scanner.nextLine();

        try (BufferedReader csvReader = new BufferedReader(new FileReader("data/points_of_interest.csv"))) {
            String line;
            csvReader.readLine();
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");
                String cityName = data[0];
                String categoryName = data[1];
                String POIName = data[2];
                String POIDesc = data[3];
                double POIRating = Double.parseDouble(data[4]);

                City city = new City(cityName);
                Category category = new Category(categoryName);
                PointOfInterest poi = new PointOfInterest(POIName, POIDesc, POIRating);
                poi.setCity(city);
                poi.setCategory(category);

                pointsOfInterest.add(poi);
                categories.add(category);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        for (PointOfInterest poi : pointsOfInterest) {
            if (poi.getCategory().getCategoryName().toLowerCase().contains(citySearch.toLowerCase())) {
                outputList.add("City: " + poi.getCity() + " Name: " + poi.getName() + " Description: " + poi.getDescr() + " Rating: " + poi.getRating());
            }
        }
        return outputList;
    }
    public List<String> search_rating() {
        List<PointOfInterest> pointsOfInterest = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        List<String> outputList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int rating = Integer.parseInt(scanner.nextLine());

        try (BufferedReader csvReader = new BufferedReader(new FileReader("data/points_of_interest.csv"))) {
            String line;
            csvReader.readLine();
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");
                String cityName = data[0];
                String categoryName = data[1];
                String POIName = data[2];
                String POIDesc = data[3];
                double POIRating = Double.parseDouble(data[4]);

                City city = new City(cityName);
                Category category = new Category(categoryName);
                PointOfInterest poi = new PointOfInterest(POIName, POIDesc, POIRating);
                poi.setCity(city);
                poi.setCategory(category);

                pointsOfInterest.add(poi);
                categories.add(category);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        for (PointOfInterest poi : pointsOfInterest) {
            if (poi.getRating()>rating) {
                outputList.add("City: " + poi.getCity() + " Name: " + poi.getName() + " Description: " + poi.getDescr() + " Rating: " + poi.getRating());
            }
        }
        return outputList;
    }
    public List<String> search() {
        List<PointOfInterest> pointsOfInterest = new ArrayList<>();
        List<Category> categories = new ArrayList<>();
        List<String> outputList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String Search = scanner.nextLine();

        try (BufferedReader csvReader = new BufferedReader(new FileReader("data/points_of_interest.csv"))) {
            String line;
            csvReader.readLine();
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");
                String cityName = data[0];
                String categoryName = data[1];
                String POIName = data[2];
                String POIDesc = data[3];
                double POIRating = Double.parseDouble(data[4]);

                City city = new City(cityName);
                Category category = new Category(categoryName);
                PointOfInterest poi = new PointOfInterest(POIName, POIDesc, POIRating);
                poi.setCity(city);
                poi.setCategory(category);

                pointsOfInterest.add(poi);
                categories.add(category);
            }
        } catch (IOException  e) {
            e.printStackTrace();
        }
        for (PointOfInterest poi : pointsOfInterest) {
            if (poi.getCategory().getCategoryName().toLowerCase().contains(Search.toLowerCase())||poi.getCity().getName().toLowerCase().contains(Search.toLowerCase())||poi.getName().toLowerCase().contains(Search.toLowerCase())||poi.getDescr().toLowerCase().contains(Search.toLowerCase())) {
                outputList.add("City: " + poi.getCity() + " Name: " + poi.getName() + " Description: " + poi.getDescr() + " Rating: " + poi.getRating());
            }
        }
        return outputList;
    }
}

