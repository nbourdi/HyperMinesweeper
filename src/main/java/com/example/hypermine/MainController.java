package com.example.hypermine;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainController {

    @FXML
    protected void onExitButtonClick() {
        System.exit(0);
    }

    @FXML
    protected void onStartButtonClick () {

    }
    @FXML
    protected void onRoundsButtonClick () {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("rounds-popup-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Rounds");
            Image icon = new Image("https://static.thenounproject.com/png/1915404-200.png");
            stage.getIcons().add(icon);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.println("Could not open Rounds pop-up window...");
        }
    }
    @FXML
    protected void onSolutionButtonClick () {

    }

    @FXML
    protected void onLoadButtonClick () {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("load-popup-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Load Game Description");
            Image icon = new Image("https://www.freeiconspng.com/uploads/spinner-icon-29.jpg");
            stage.getIcons().add(icon);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.println("Could not open Load pop-up window...");
        }

    }
    @FXML
    protected void onCreateButtonClick () {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("create-popup-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Create Game Description");
            Image icon = new Image("https://www.clipartmax.com/png/full/14-149858_add-clipart-to-photo.png");
            stage.getIcons().add(icon);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.println("Could not open Start pop-up window...");
        }
    }
}