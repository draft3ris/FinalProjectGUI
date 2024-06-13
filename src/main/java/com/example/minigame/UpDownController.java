package com.example.minigame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class UpDownController {

    File upImageFile = new File("up.jpeg");
    File downImageFile = new File("down.jpeg");
    File guessImageFile = new File("guess.jpeg");
    File resultImageFile = new File("result.jpeg");

    Image upImage = new Image(upImageFile.toURI().toString());
    Image downImage = new Image(downImageFile.toURI().toString());
    Image resultImage = new Image(resultImageFile.toURI().toString());
    Image gameoverImage = new Image(new File("gameover.jpeg").toURI().toString());

    @FXML
    ImageView upDownScreen;

    @FXML
    TextField inputField;

    @FXML
    Button submitButton;

    @FXML
    Button homeButton;

    int number;
    int count = 7;

    @FXML
    void onHomeButtonClicked() throws IOException {
        Stage mainStage = new Stage();
        Stage stage = (Stage) homeButton.getScene().getWindow();

        Parent second = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(second);

        mainStage.setScene(scene);
        mainStage.show();

        stage.close();
        stage.hide();
    }

    @FXML
    protected void onSubmitButtonClick() {
        String input = inputField.getText();
        int guessNumber = Integer.parseInt(input);

        System.out.println(upImage.isError());
        System.out.println(downImage.isError());
        System.out.println(resultImage.isError());

        if (number == guessNumber) {
            upDownScreen.setImage(resultImage);
            inputField.setVisible(false);
            submitButton.setVisible(false);
            homeButton.setVisible(true);
            MiniGameApplication.score += 3;
            return;
        } else if (number > guessNumber) {
            upDownScreen.setImage(upImage);
        } else {
            upDownScreen.setImage(downImage);
        }

        count--;
        if (count == 0) {
            inputField.setVisible(false);
            submitButton.setVisible(false);
            upDownScreen.setImage(gameoverImage);
            homeButton.setVisible(true);
            MiniGameApplication.life--;
        }
    }

    @FXML
    void initialize(){
        Image guessImage = new Image(guessImageFile.toURI().toString());

        upDownScreen.setImage(guessImage);

        Random random = new Random();
        number = random.nextInt(100) + 1;

        homeButton.setVisible(false);
    }
}
