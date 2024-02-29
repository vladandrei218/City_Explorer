module com.example.project_p3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.project_p3 to javafx.fxml;
    exports com.example.project_p3;
}