module com.example.minigame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.minigame to javafx.fxml;
    exports com.example.minigame;
}