package com.example.minigame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class RockScissorsPaperController {

    File winImageFile = new File("win.jpeg");
    File loseImageFile = new File("lose.jpeg");
    File tieImageFile = new File("tie.jpeg");

    int computerChoice; // 0: rock, 1: scissors, 2: paper

    @FXML
    ImageView rockImageView;

    @FXML
    ImageView scissorsImageView;

    @FXML
    ImageView paperImageView;

    @FXML
    ImageView resultImageView;

    @FXML
    SplitPane choiceScreen;

    @FXML
    Button rockButton;
    @FXML
    Button scissorsButton;
    @FXML
    Button paperButton;

    @FXML
    Button homeButton;

    @FXML
    void initialize(){
        Random random = new Random();
        computerChoice = random.nextInt(3);
        homeButton.setVisible(false);
    }

    @FXML
    protected void onHomeButtonClicked() throws IOException {
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
    protected void rockButtonClicked() {
        System.out.println("rock clicked");
        if (computerChoice == 0) { // tie
            System.out.println("tie");
            resultImageView.setImage(new Image(tieImageFile.toURI().toString()));
            resultImageView.setFitWidth(300);
            resultImageView.setFitHeight(300);
        } else if (computerChoice == 1) { // win
            System.out.println("win");
            resultImageView.setImage(new Image(winImageFile.toURI().toString()));
            MiniGameApplication.score += 5;
            resultImageView.setFitWidth(500);
            resultImageView.setFitHeight(500);
        } else { // lose
            System.out.println("lose");
            resultImageView.setImage(new Image(loseImageFile.toURI().toString()));
            MiniGameApplication.life--;
            resultImageView.setFitWidth(500);
            resultImageView.setFitHeight(500);
        }

        choiceScreen.setVisible(false);

        resultImageView.setX(200);
        resultImageView.setY(200);


        homeButton.setVisible(true);
    }

    @FXML
    void scissorsButtonClicked() {
        System.out.println("s clicked");
        if (computerChoice == 0) { // lose
            resultImageView.setImage(new Image(loseImageFile.toURI().toString()));
            resultImageView.setFitWidth(500);
            resultImageView.setFitHeight(500);
            MiniGameApplication.life--;
        } else if (computerChoice == 1) { // tie
            resultImageView.setImage(new Image(tieImageFile.toURI().toString()));
            resultImageView.setFitWidth(300);
            resultImageView.setFitHeight(300);
        } else { // win
            resultImageView.setImage(new Image(winImageFile.toURI().toString()));
            MiniGameApplication.score += 5;
            resultImageView.setFitWidth(500);
            resultImageView.setFitHeight(500);
        }

        choiceScreen.setVisible(false);

        resultImageView.setX(200);
        resultImageView.setY(200);

        homeButton.setVisible(true);
    }

    @FXML
    void paperButtonClicked() {
        System.out.println("b clicked");
        if (computerChoice == 0) { // win
            resultImageView.setImage(new Image(winImageFile.toURI().toString()));
            MiniGameApplication.score += 5;
            resultImageView.setFitWidth(500);
            resultImageView.setFitHeight(500);
        } else if (computerChoice == 1) { // lose
            resultImageView.setImage(new Image(loseImageFile.toURI().toString()));
            MiniGameApplication.life--;
            resultImageView.setFitWidth(500);
            resultImageView.setFitHeight(500);
        } else { // tie
            resultImageView.setImage(new Image(tieImageFile.toURI().toString()));
            resultImageView.setFitWidth(300);
            resultImageView.setFitHeight(300);
        }

        choiceScreen.setVisible(false);

        resultImageView.setX(200);
        resultImageView.setY(200);


        homeButton.setVisible(true);
    }
}
