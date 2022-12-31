package com.example.hypermine;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController {
    public Pane GridArea;
    @FXML
    private Label BombCount;
    @FXML
    private Label Countdown;
    @FXML
    private Label MarkedCount;

    @FXML
    protected void onExitButtonClick() {
        System.exit(0);
    }

    @FXML
    protected void onStartButtonClick () {
       // Game game = new Game()
        BombCount.setText("initial");   // static and final, all i need to do is get
        MarkedCount.setText("0");       // this will need to be updated dependent on the game
        Countdown.setText("initial"); // put the ticking timer here
        Tile[][] grid = new Tile[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Tile tile = new Tile(x, y, Math.random() < 0.2, false, 24);

                grid[x][y] = tile;
                GridArea.getChildren().add(tile);
            }
        }
        // Game new_Game = new Game();
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

    public void mouseClicked(MouseEvent mouseEvent) {
    }
}