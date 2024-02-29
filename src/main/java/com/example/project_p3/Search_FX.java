package com.example.project_p3;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Search_FX extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    public String role;
    public Search_FX(String role) {
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
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(0);

        Button search = new Button("Search");
        TextField searchField = new TextField();
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

        search.setOnAction(e -> {
            String searchInput = searchField.getText();
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

            grid2.add(backButton2, 10, 5);


            searchPointsOfInterest(vbox2, searchInput);

            backButton2.setOnAction(e2 -> {
                stage.close();
                Search_FX menu = new Search_FX(role);
                menu.start(new Stage());
            });
            vbox2.getChildren().add(grid2);
            Scene scene = new Scene(vbox2, 900, 620);
            stage.setTitle("Search results for" + searchInput);
            stage.setScene(scene);
            stage.show();
        });

          GridPane.setHalignment(searchField, javafx.geometry.HPos.CENTER);
          GridPane.setValignment(searchField, javafx.geometry.VPos.CENTER);


        grid.add(searchField, 0, 1);
        grid.add(search, 0, 2);
        grid.add(backButton, 1, 2);
        GridPane.setMargin(backButton, new Insets(-50));

        vbox.getChildren().add(grid);

        Scene scene = new Scene(vbox, 250, 150);
        primaryStage.setTitle("Search for a point of interest");
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

    private void searchPointsOfInterest(VBox vbox, String search) {
        vbox.getChildren().clear();
        try {
            String query = "SELECT * FROM pointofinterest WHERE cityName LIKE ? OR categoryName LIKE ? OR POIName LIKE ? OR POIDesc LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (int i = 1; i <= 4; i++) {
                preparedStatement.setString(i, "%" + search + "%");
            }
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
