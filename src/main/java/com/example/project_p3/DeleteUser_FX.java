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

public class DeleteUser_FX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public String role;
    public DeleteUser_FX(String role) {
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
        Button deleteButton = new Button("Delete");

        Button backButton = new Button("Back");
        backButton.setOnAction(e2 -> {
            primaryStage.close();
            MenuAdmin_FX menu = new MenuAdmin_FX(role);
            menu.start(new Stage());
        });

        deleteButton.setOnAction(e -> {
            deleteUserthread(deleteField.getText());
            primaryStage.close();
            DeleteUser_FX delete = new DeleteUser_FX(role);
            delete.start(new Stage());
        });
        DisplayUsers(vbox);
        vbox.getChildren().add(grid);
        grid.add(backButton, 9, 1);
        grid.add(deleteButton, 8, 1);
        grid.add(deleteField, 8, 0);

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

    private void DisplayUsers(VBox vbox) {
        try {
            String query = "SELECT * FROM user";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");


                Label poiLabel = new Label("Username: " + username + ", Password: " + password + ", Role: " + role);

                vbox.getChildren().add(poiLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void deleteUserthread(String username) {
        Thread thread = new Thread(() -> {
            try {
                DeleteUser(username);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.start();
    }

    private void DeleteUser(String username){
        try{
            if(username.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Field cannot be empty!");
                alert.showAndWait();
                return;
            }
            if (!UserExists(username)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("User does not exist!");
                alert.showAndWait();
                return;
            }
            String query = "DELETE FROM user WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private boolean UserExists(String username) {
        try {
            String query = "SELECT COUNT(*) FROM user WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
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
