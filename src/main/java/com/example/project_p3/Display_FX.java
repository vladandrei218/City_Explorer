package com.example.project_p3;

import javafx.application.Application;
import javafx.geometry.Insets;
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

public class Display_FX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public String role;
    public Display_FX(String role) {
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
        grid.setHgap(38);

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
        DisplayPointsOfInterest(vbox);
        vbox.getChildren().add(grid);
        grid.add(backButton, 10, 2);


        Scene scene = new Scene(vbox, 900, 600);
        primaryStage.setTitle("Display points of interest");
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

    private void DisplayPointsOfInterest(VBox vbox) {
        try {
            String query = "SELECT * FROM pointofinterest";
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
