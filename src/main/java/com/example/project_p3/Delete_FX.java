package com.example.project_p3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class Delete_FX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public String role;
    public Delete_FX(String role) {
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

        TextField deleteField = new TextField();
        Label deleteLabel = new Label("Point of interest name that you want to delete:");
        Button deleteButton = new Button("Delete");

        Button backButton = new Button("Back");
        backButton.setOnAction(e2 -> {
            primaryStage.close();
            MenuAdmin_FX menu = new MenuAdmin_FX(role);
            menu.start(new Stage());
        });

        deleteButton.setOnAction(e -> {
            DeletePointOfInterest(deleteField.getText());
            primaryStage.close();
            Delete_FX delete = new Delete_FX(role);
            delete.start(new Stage());
        });
        DisplayPointsOfInterest(vbox);
        vbox.getChildren().add(grid);
//        grid.add(deleteLabel, 7, 0);
        grid.add(backButton, 9, 2);
        grid.add(deleteButton, 8, 2);
        grid.add(deleteField, 8, 1);

        GridPane.setMargin(backButton, new Insets(-80));


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

    private void DeletePointOfInterest(String POIName){

        if(POIName.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Field cannot be empty!");
            alert.showAndWait();
            return;
        }
        if (!PointOfInterestExists(POIName)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Point of interest not found in the database!");
            alert.showAndWait();
            return;
        }
        try{
            String query = "DELETE FROM pointofinterest WHERE POIName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, POIName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean PointOfInterestExists(String POIName) {
        try {
            String query = "SELECT COUNT(*) FROM pointofinterest WHERE POIName = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, POIName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
