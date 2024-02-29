package com.example.project_p3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Log extends Application {

    private Connection connection;

    @Override
    public void start(Stage primaryStage) {
        initializeDatabase();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (authenticateUser(username, password)) {
                if (isAdmin(username, password)) {
                    primaryStage.close();
                    MenuAdmin_FX adminMenu = new MenuAdmin_FX("Admin");
                    adminMenu.start(new Stage());
                } else {
                    primaryStage.close();
                    Menu_FX menu = new Menu_FX("User");
                    menu.start(new Stage());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Username or password is incorrect!");
                alert.showAndWait();
            }
        });

        registerButton.setOnAction(e -> {
            primaryStage.close();
            Register register = new Register();
            register.start(new Stage());
        });

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(registerButton, 2, 2);

        GridPane.setMargin(registerButton, new Insets(-80));

        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setTitle("Login");
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

    private boolean authenticateUser(String username, String password) {
        try {
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isAdmin(String username, String password) {
        try {
            String query = "SELECT role FROM user WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String role = resultSet.getString("role");
                return role.equals("Admin");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
