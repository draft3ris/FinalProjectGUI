package com.example.minigame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class WASDController {

    File wImageFile = new File("w.jpeg");
    File aImageFile = new File("a.jpeg");
    File sImageFile = new File("s.jpeg");
    File dImageFile = new File("d.jpeg");
    File resultImageFile = new File("result.jpeg");

    @FXML
    ImageView wasdScreen;

    @FXML
    TextField inputField;

    @FXML
    Label message;

    @FXML
    Button homeButton;

    int wasd; // 1 w 2 a 3 s 4 d

    int play = 10;
    int wrong = 0;

    Random random = new Random();

    long startTime;
    long playTime;

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
    void initialize(){
        message.setVisible(false);
        setRandomWasdImage();
        startTime = System.currentTimeMillis();
    }

    private void setRandomWasdImage() {
        wasd = random.nextInt(4) + 1;

        if (wasd == 1) {
            wasdScreen.setImage(new Image(wImageFile.toURI().toString()));
        } else if (wasd == 2) {
            wasdScreen.setImage(new Image(aImageFile.toURI().toString()));
        } else if (wasd == 3) {
            wasdScreen.setImage(new Image(sImageFile.toURI().toString()));
        } else if (wasd == 4) {
            wasdScreen.setImage(new Image(dImageFile.toURI().toString()));
        }
    }

    public void onKeyPressed(KeyEvent event) {
        if (play > 0) {
            message.setVisible(true);

            Scene scene = (Scene) (event.getSource());
            TextField textField = (TextField) scene.lookup("#inputField");
            String input = textField.getText();

            if (("w".equals(input) && wasd == 1) ||
                    ("a".equals(input) && wasd == 2) ||
                    ("s".equals(input) && wasd == 3) ||
                    ("d".equals(input) && wasd == 4)) {
                play--;
                if (play == 0) {
                    textField.setVisible(false);
                    playTime = System.currentTimeMillis() - startTime;
                    message.setText(("플레이 타임: " + playTime / 1000.0) + "초!\n틀린 횟수: " + wrong + (wrong == 0 ? "\n완벽합니다!" : "\n정확도 100에 도전해보세요!"));
                    wasdScreen.setImage(new Image(resultImageFile.toURI().toString()));

                    if (playTime / 1000.0 > 7) {
                        MiniGameApplication.life--;
                    } else {
                        if (wrong == 0) {
                            MiniGameApplication.score += 3;
                        } else {
                            MiniGameApplication.score += 2;
                        }
                    }
                    return;
                }
                setRandomWasdImage();
                message.setText("GOOD! 남은 횟수: " + play);
            } else {
                message.setText("WRONG! 남은 횟수: " + play);
                wrong++;
            }

            textField.setText("");
        }
    }

}
