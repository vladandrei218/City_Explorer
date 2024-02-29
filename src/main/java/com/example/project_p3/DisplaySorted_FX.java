package com.example.project_p3;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplaySorted_FX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public String role;
    public DisplaySorted_FX(String role) {
        this.role = role;
    }

    private Connection connection;

    @Override
    public void start(Stage primaryStage) {
        initializeDatabase();
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(20));
        vbox.setSpacing(10);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setVgap(10);
        grid.setHgap(10);

        Button sortByName = new Button("Sort by name");
        sortByName.setOnAction(e -> {
            primaryStage.close();
            Stage stage = new Stage();
            VBox vbox2 = new VBox();
            vbox2.setPadding(new Insets(10));
            vbox2.setSpacing(10);
            GridPane grid2 = new GridPane();
            grid2.setPadding(new Insets(20));
            grid2.setVgap(10);
            grid2.setHgap(40);
            Button backButton2 = new Button("Back");

            fetchAndDisplayPointsOfInterestSortedByName(vbox2);
            vbox2.getChildren().add(grid2);
            grid2.add(backButton2, 10, 5);

            backButton2.setOnAction(e2 -> {
                stage.close();
                DisplaySorted_FX menu = new DisplaySorted_FX(role);
                menu.start(new Stage());
            });

            Scene scene = new Scene(vbox2, 900, 620);
            stage.setTitle("Points of interest sorted by name");
            stage.setScene(scene);

            stage.show();
        });
        Button sortByRating = new Button("Sort by rating");
        sortByRating.setOnAction(e -> {
            primaryStage.close();
            Stage stage = new Stage();
            VBox vbox2 = new VBox();
            vbox2.setPadding(new Insets(10));
            vbox2.setSpacing(10);
            GridPane grid2 = new GridPane();
            grid2.setPadding(new Insets(20));
            grid2.setVgap(10);
            grid2.setHgap(40);
            Button backButton2 = new Button("Back");

            fetchAndDisplayPointsOfInterestSortedByRating(vbox2);
            vbox2.getChildren().add(grid2);
            grid2.add(backButton2, 10, 5);

            backButton2.setOnAction(e2 -> {
                stage.close();
                DisplaySorted_FX menu = new DisplaySorted_FX(role);
                menu.start(new Stage());
            });

            Scene scene = new Scene(vbox2, 900, 620);
            stage.setTitle("Points of interest sorted by rating");
            stage.setScene(scene);

            stage.show();
        });
        Button sortByCategory = new Button("Sort by category");
        sortByCategory.setOnAction(e -> {
            primaryStage.close();
            Stage stage = new Stage();
            VBox vbox2 = new VBox();
            vbox2.setPadding(new Insets(10));
            vbox2.setSpacing(10);
            GridPane grid2 = new GridPane();
            grid2.setPadding(new Insets(20));
            grid2.setVgap(10);
            grid2.setHgap(40);
            Button backButton2 = new Button("Back");

            fetchAndDisplayPointsOfInterestSortedByCategory(vbox2);
            vbox2.getChildren().add(grid2);
            grid2.add(backButton2, 10, 5);

            backButton2.setOnAction(e2 -> {
                stage.close();
                DisplaySorted_FX menu = new DisplaySorted_FX(role);
                menu.start(new Stage());
            });

            Scene scene = new Scene(vbox2, 900, 620);
            stage.setTitle("Points of interest sorted by category");
            stage.setScene(scene);

            stage.show();
        });
        Button sortByCity = new Button("Sort by city");
        sortByCity.setOnAction(e -> {
            primaryStage.close();
            Stage stage = new Stage();
            VBox vbox2 = new VBox();
            vbox2.setPadding(new Insets(10));
            vbox2.setSpacing(10);
            GridPane grid2 = new GridPane();
            grid2.setPadding(new Insets(20));
            grid2.setVgap(10);
            grid2.setHgap(40);
            Button backButton2 = new Button("Back");

            fetchAndDisplayPointsOfInterestSortedByCity(vbox2);
            vbox2.getChildren().add(grid2);
            grid2.add(backButton2, 10, 5);

            backButton2.setOnAction(e2 -> {
                stage.close();
                DisplaySorted_FX menu = new DisplaySorted_FX(role);
                menu.start(new Stage());
            });

            Scene scene = new Scene(vbox2, 900, 620);
            stage.setTitle("Points of interest sorted by city");
            stage.setScene(scene);

            stage.show();
        });
        Button backButton = new Button("Back");
        backButton.setOnAction(e2 -> {
            if(role.equals("Admin")) {
                primaryStage.close();
                MenuAdmin_FX menuAdmin = new MenuAdmin_FX(role);
                menuAdmin.start(new Stage());
            }
            else if(role.equals("User")) {
                primaryStage.close();
                Menu_FX menu = new Menu_FX(role);
                menu.start(new Stage());
            }
        });

        grid.add(sortByName, 0, 0);
        grid.add(sortByRating, 0, 1);
        grid.add(sortByCategory, 0, 2);
        grid.add(sortByCity, 0, 3);
        grid.add(backButton, 0, 4);

        GridPane.setHalignment(sortByName, HPos.CENTER);
        GridPane.setHalignment(sortByRating, HPos.CENTER);
        GridPane.setHalignment(sortByCategory, HPos.CENTER);
        GridPane.setHalignment(sortByCity, HPos.CENTER);
        GridPane.setHalignment(backButton, HPos.CENTER);
        GridPane.setValignment(sortByName, VPos.CENTER);
        GridPane.setValignment(sortByRating, VPos.CENTER);
        GridPane.setValignment(sortByCategory, VPos.CENTER);
        GridPane.setValignment(sortByCity, VPos.CENTER);
        GridPane.setValignment(backButton, VPos.CENTER);



        Scene scene = new Scene(grid, 170, 230);
        primaryStage.setTitle("Display sorted points of interest");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/pointsofinterestdb";
            String dbUser = "root";
            String dbPassword = "admin";

            connection = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }


    private void fetchAndDisplayPointsOfInterestSortedByName(VBox vbox) {
        try {
            String query = "SELECT * FROM pointofinterest ORDER BY POIName";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String cityName = resultSet.getString("cityName");
                String categoryName = resultSet.getString("categoryName");
                String poiName = resultSet.getString("POIName");
                String poiDesc = resultSet.getString("POIDesc");
                double poiRating = resultSet.getDouble("POIRating");


                Label poiLabel = new Label("City: " + cityName + ", Category: " + categoryName +
                        ", Name: " + poiName + ", Description: " + poiDesc + ", Rating: " + poiRating);

                vbox.getChildren().add(poiLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void fetchAndDisplayPointsOfInterestSortedByCategory(VBox vbox) {
        try {
            String query = "SELECT * FROM pointofinterest ORDER BY categoryName";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String cityName = resultSet.getString("cityName");
                String categoryName = resultSet.getString("categoryName");
                String poiName = resultSet.getString("POIName");
                String poiDesc = resultSet.getString("POIDesc");
                double poiRating = resultSet.getDouble("POIRating");


                Label poiLabel = new Label("City: " + cityName + ", Category: " + categoryName +
                        ", Name: " + poiName + ", Description: " + poiDesc + ", Rating: " + poiRating);

                vbox.getChildren().add(poiLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void fetchAndDisplayPointsOfInterestSortedByCity(VBox vbox) {
        try {
            String query = "SELECT * FROM pointofinterest ORDER BY cityName";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String cityName = resultSet.getString("cityName");
                String categoryName = resultSet.getString("categoryName");
                String poiName = resultSet.getString("POIName");
                String poiDesc = resultSet.getString("POIDesc");
                double poiRating = resultSet.getDouble("POIRating");


                Label poiLabel = new Label("City: " + cityName + ", Category: " + categoryName +
                        ", Name: " + poiName + ", Description: " + poiDesc + ", Rating: " + poiRating);

                vbox.getChildren().add(poiLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void fetchAndDisplayPointsOfInterestSortedByRating(VBox vbox) {
        try {
            String query = "SELECT * FROM pointofinterest ORDER BY POIRating";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String cityName = resultSet.getString("cityName");
                String categoryName = resultSet.getString("categoryName");
                String poiName = resultSet.getString("POIName");
                String poiDesc = resultSet.getString("POIDesc");
                double poiRating = resultSet.getDouble("POIRating");


                Label poiLabel = new Label("City: " + cityName + ", Category: " + categoryName +
                        ", Name: " + poiName + ", Description: " + poiDesc + ", Rating: " + poiRating);

                vbox.getChildren().add(poiLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
