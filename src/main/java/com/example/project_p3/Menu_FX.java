package com.example.project_p3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

public class Menu_FX extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    public String role;
    public Menu_FX(String role) {
        this.role = role;
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        Button AddButton = new Button("Add a point of interest");
        Button DisplayButton = new Button("Display all points of interest");
        Button DisplaySortedButton = new Button("Display sorted points of interest");
        Button SearchButton = new Button("Search for a point of interest");
        Button LogOutButton = new Button("Log out");

        GridPane.setHalignment(AddButton, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(DisplayButton, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(DisplaySortedButton, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(SearchButton, javafx.geometry.HPos.CENTER);
        GridPane.setHalignment(LogOutButton, javafx.geometry.HPos.CENTER);

        AddButton.setOnAction(e -> {
            primaryStage.close();
            Input_FX input = new Input_FX(role);
            input.start(new Stage());
        });

        DisplayButton.setOnAction(e -> {
            primaryStage.close();
            Display_FX display = new Display_FX(role);
            display.start(new Stage());
        });

        DisplaySortedButton.setOnAction(e -> {
            primaryStage.close();
            DisplaySorted_FX displaySorted = new DisplaySorted_FX(role);
            displaySorted.start(new Stage());
        });

        SearchButton.setOnAction(e -> {
            primaryStage.close();
            Search_FX search = new Search_FX(role);
            search.start(new Stage());
        });

        LogOutButton.setOnAction(e -> {
            primaryStage.close();
            Log login = new Log();
            login.start(new Stage());
        });


        grid.add(AddButton, 0, 1);
        grid.add(DisplayButton, 0, 2);
        grid.add(DisplaySortedButton, 0, 3);
        grid.add(SearchButton, 0, 4);
        grid.add(LogOutButton, 0, 5);

        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setTitle("Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
