package com.example.project_p3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class Input_FX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public String role;
    public Input_FX(String role) {
        this.role = role;
    }

    private Connection connection;

    @Override
    public void start(Stage primaryStage) {
        initializeDatabase();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label cityName = new Label("City:");
        TextField cityField = new TextField();
        Label categoryLabel = new Label("Category:");
        TextField categoryField = new TextField();
        Label POIName = new Label("Point of interest name:");
        TextField POIField = new TextField();
        Label POIDesc = new Label("Description:");
        TextField descField = new TextField();
        Label POIRating = new Label("Rating:");
        TextField ratingField = new TextField();
        Button createButton = new Button("Create");
        Button backButton = new Button("Back");

        grid.add(cityName, 0, 0);
        grid.add(cityField, 1, 0);
        grid.add(categoryLabel, 0, 1);
        grid.add(categoryField, 1, 1);
        grid.add(POIName, 0, 2);
        grid.add(POIField, 1, 2);
        grid.add(POIDesc, 0, 3);
        grid.add(descField, 1, 3);
        grid.add(POIRating, 0, 4);
        grid.add(ratingField, 1, 4);


        grid.add(createButton, 0, 6);
        grid.add(backButton, 1, 6);


        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);

        grid.getColumnConstraints().addAll(col1, col2);

        GridPane.setHalignment(createButton, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(backButton, javafx.geometry.HPos.CENTER);

        createButton.setOnAction(e -> {
            String city = cityField.getText();
            String category = categoryField.getText();
            String POI = POIField.getText();
            String desc = descField.getText();
            String ratingString = ratingField.getText();
            if (ratingField.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Fields cannot be empty!");
                alert.showAndWait();
                return;
            }
            if (city.equals("") || category.equals("") || POI.equals("") || desc.equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Fields cannot be empty!");
                alert.showAndWait();
                return;
            }
            double rating;
            try {
                rating = Double.parseDouble(ratingString);
                if (rating < 1 || rating > 10) {
                    throw new IllegalArgumentException();
                }
                insertPoint(city, category, POI, desc, rating);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Point of interest added successfully!");
                alert.showAndWait();
                primaryStage.close();
                Menu_FX menu = new Menu_FX(role);
                menu.start(new Stage());
            } catch (IllegalArgumentException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Rating must be a number between 1 and 10!");
                alert.showAndWait();
            }
        });


        backButton.setOnAction(e -> {
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

        Scene scene = new Scene(grid, 350, 250);
        primaryStage.setTitle("Add a point of interest");
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
    private String insertPoint(String cityName, String categoryName, String POIName, String POIDesc, double POIRating) {
        try {
            String query = "INSERT INTO pointofinterest (cityName, categoryName, POIName, POIDesc, POIRating) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cityName);
            preparedStatement.setString(2, categoryName);
            preparedStatement.setString(3, POIName);
            preparedStatement.setString(4, POIDesc);
            preparedStatement.setDouble(5, POIRating);

            preparedStatement.executeUpdate();
            return "Point of interest added successfully!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error adding point of interest!";
        }
    }

}
