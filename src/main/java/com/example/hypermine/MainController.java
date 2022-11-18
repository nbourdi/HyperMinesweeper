package com.example.hypermine;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onExitButtonClick() {
        System.exit(0);
    }

    @FXML
    protected void onStartButtonClick () {

    }
    @FXML
    protected void onRoundsButtonClick () {

    }
    @FXML
    protected void onSolutionButtonClick () {

    }

    @FXML
    protected void onLoadButtonClick () {

    }
    @FXML
    protected void onCreateButtonClick () {

    }
}