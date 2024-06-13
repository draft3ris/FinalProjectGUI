package com.example.minigame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MiniGameController {

    @FXML
    Button upDownButton;
    @FXML
    Button rockScissorsPaper;
    @FXML
    Button WASDButton;

    @FXML
    ImageView heart1;
    @FXML
    ImageView heart2;
    @FXML
    ImageView heart3;

    @FXML
    Label lifeLabel;

    @FXML
    Label scoreLabel;

    @FXML
    Label descriptionLabel;

    @FXML
    Button playButton;

    int selectedGame;

    // 가위바위보 5점
    // WASD 7초 넘으면 life-- 무점수, 안틀리고 7초 안에 끊으면 3점, 틀리고 7초 안에 끊으면 2점
    // Updown 7번 이하로 맞추면 3점, 아니면 life--

    @FXML
    void initialize() {
        playButton.setVisible(false);
        descriptionLabel.setVisible(false);
        scoreLabel.setText("SCORE: " + MiniGameApplication.score);

        if (MiniGameApplication.life == 0) {
            scoreLabel.setText("FINAL SCORE: " + MiniGameApplication.score);

            descriptionLabel.setVisible(false);
            lifeLabel.setVisible(false);

            heart1.setVisible(false);
            heart2.setVisible(false);
            heart3.setVisible(false);

            upDownButton.setVisible(false);
            rockScissorsPaper.setVisible(false);
            WASDButton.setVisible(false);
        } else if (MiniGameApplication.life == 1) {
            heart1.setVisible(true);
            heart2.setVisible(false);
            heart3.setVisible(false);
        } else if (MiniGameApplication.life == 2) {
            heart1.setVisible(true);
            heart2.setVisible(true);
            heart3.setVisible(false);
        } else if (MiniGameApplication.life == 3) {
            heart1.setVisible(true);
            heart2.setVisible(true);
            heart3.setVisible(true);
        }
    }

    @FXML
    protected void onPlayButtonClicked() throws IOException {
        Stage newStage;
        Stage stage;
        Parent second;
        Scene scene;

        switch (selectedGame) {
            case 1:
                newStage = new Stage();
                stage = (Stage) upDownButton.getScene().getWindow();

                second = FXMLLoader.load(getClass().getResource("updown.fxml"));
                scene = new Scene(second);

                newStage.setScene(scene);
                newStage.show();

                stage.close();
                stage.hide();
                break;
            case 2:
                newStage = new Stage();
                stage = (Stage) upDownButton.getScene().getWindow();

                second = FXMLLoader.load(getClass().getResource("rockscissorspaper.fxml"));
                scene = new Scene(second);

                newStage.setScene(scene);
                newStage.show();

                stage.close();
                stage.hide();
                break;
            case 3:
                newStage = new Stage();
                stage = (Stage) upDownButton.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(MiniGameController.class.getResource("wasd.fxml"));
                scene = new Scene(loader.load());
                WASDController wasdController = loader.getController();

                scene.setOnKeyPressed(event -> {
                    wasdController.onKeyPressed(event);
                });

                newStage.setScene(scene);
                newStage.show();

                stage.close();
                stage.hide();
        }


    }

    @FXML
    protected void onUpDownButtonClick() {
        selectedGame = 1;
        playButton.setVisible(true);
        descriptionLabel.setText("Updown game is a game where you guess randomly generated numbers from 1 to 100. When the user enters a number, it compares that number with a randomly generated number and returns up, down, or excellent. The game continues until the user guesses a number. If the number is guessed within 7 times, 3 points are awarded, and if the number is guessed more than 7 times, a life is deducted.");
        descriptionLabel.setVisible(true);
    }

    @FXML
    protected void onRockScissorsPaperButtonClick() {
        selectedGame = 2;
        playButton.setVisible(true);
        descriptionLabel.setText("Everyone knows rock, paper, scissors, right?\n" +
                "If you win you get 5 points, if it is a tie game you get 0 points,\n" +
                "and if you lose, your life is reduced.");
        descriptionLabel.setVisible(true);
    }

    @FXML
    protected void onWASDButtonClick() {
        selectedGame = 3;
        playButton.setVisible(true);
        descriptionLabel.setText("WASD is a game where you input keys according to the arrows.\n" +
                "Click w for an arrow pointing up,\n" +
                "click a for an arrow pointing left,\n" +
                "click d for an arrow pointing right,\n" +
                "and click s for an arrow pointing down.\n" +
                "The time limit is 7 seconds and 10 random arrows will appear.\n" +
                "If you exceed 7 seconds, your life will be reduced, and even if you try,\n" +
                "if you succeed within 7 seconds, you will get 2 points,\n" +
                "and if you succeed without failing at once, you will get 3 points.");
        descriptionLabel.setVisible(true);
    }

}